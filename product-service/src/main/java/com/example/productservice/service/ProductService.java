package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    ProductDto addProduct(ProductDto productDto);

    ProductDto findById(Long id);

    ProductDto updateProduct(ProductDto productDto, Long id);

    void deleteProduct(Long id);
}