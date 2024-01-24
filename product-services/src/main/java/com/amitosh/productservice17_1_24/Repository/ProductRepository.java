package com.amitosh.productservice17_1_24.Repository;

import com.amitosh.productservice17_1_24.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
