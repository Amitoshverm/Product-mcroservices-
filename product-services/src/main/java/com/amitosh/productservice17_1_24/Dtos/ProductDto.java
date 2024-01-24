package com.amitosh.productservice17_1_24.Dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String name;
    private String description;
    private double price;
}
