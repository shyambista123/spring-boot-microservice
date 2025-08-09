package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> findAll() {
        List<Product> products = productService.findAll();
        return products.stream().map(ProductMapper::mapToDto).toList();
    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/products/{id}")
    public  ProductDto updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
