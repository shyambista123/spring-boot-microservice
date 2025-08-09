package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.mappers.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Cacheable(value = "product")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @CacheEvict(value = "product", allEntries = true)
    public ProductDto addProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToEntity(productDto);
        productRepository.save(product);
        return ProductMapper.mapToDto(product);
    }

    @Cacheable(value = "product", key = "#id")
    public ProductDto findById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found."));
        return ProductMapper.mapToDto(product);
    }

    @CachePut(value = "product", key = "#id")
    @CacheEvict(value = "product", allEntries = true)
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found."));

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        productRepository.save(existingProduct);
        return ProductMapper.mapToDto(existingProduct);
    }

    @CacheEvict(value = "product", key = "#id", allEntries = true)
    public void deleteProduct(Long id) {
        Product productDel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
        productRepository.delete(productDel);
    }
}