package com.example.productservice.mappers;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;

public class ProductMapper {
    public static Product mapToEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return product;
    }

    public static ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
