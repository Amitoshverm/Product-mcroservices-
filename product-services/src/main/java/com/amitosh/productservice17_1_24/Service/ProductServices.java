package com.amitosh.productservice17_1_24.Service;

import com.amitosh.productservice17_1_24.Dtos.ProductDto;

import java.util.List;

public interface ProductServices {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
}
