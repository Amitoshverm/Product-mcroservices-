package com.amitosh.productservice17_1_24.Service;

import com.amitosh.productservice17_1_24.Dtos.ProductDto;
import com.amitosh.productservice17_1_24.Model.Product;
import com.amitosh.productservice17_1_24.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServices{

    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product ConvertDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public ProductDto ConvertProductToDto(Product product) {
        ProductDto productdto = new ProductDto();
        productdto.setName(product.getName());
        productdto.setDescription(product.getDescription());
        productdto.setPrice(product.getPrice());
        return productdto;
    }
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ConvertDtoToProduct(productDto);
        return ConvertProductToDto(this.productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream()
                .map(this::ConvertProductToDto)
                .collect(Collectors.toList());
    }
}
