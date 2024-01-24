package com.amitosh.productservice17_1_24.Controller;

import com.amitosh.productservice17_1_24.Dtos.ProductDto;
import com.amitosh.productservice17_1_24.Service.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductServices productService;
    public ProductController(ProductServices productService) {
        this.productService = productService;
    }
    @PostMapping

    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
