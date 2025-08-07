package com.example.productservice.service;

import com.example.productservice.entity.Product;
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
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(value = "product", key = "#id")
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found"));
    }

    @CachePut(value = "product", key = "#id")
    @CacheEvict(value = "product", allEntries = true)
    public Product updateProduct(Product product, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @CacheEvict(value = "product", key = "#id", allEntries = true)
    public void deleteProduct(Long id) {
        Product productDel = productRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        productRepository.delete(productDel);
    }
}