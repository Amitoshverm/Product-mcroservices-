package com.example.inventoryservice19_1_24;

import com.example.inventoryservice19_1_24.Model.Inventory;
import com.example.inventoryservice19_1_24.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class InventoryService19124Application {

    public static void main(String[] args) {
        SpringApplication.run(InventoryService19124Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = new Inventory();

            inventory.setSkuCode("iphone_15");
            inventory.setQuantity(100);

            Inventory inventory1 = new Inventory();

            inventory1.setSkuCode("iphone_14");
            inventory1.setQuantity(1);
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
        };
    }

}
