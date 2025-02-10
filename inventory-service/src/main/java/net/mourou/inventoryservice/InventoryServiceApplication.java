package net.mourou.inventoryservice;

import net.mourou.inventoryservice.entities.Product;
import net.mourou.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return arg -> {
            Product product = Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("watch")
                    .price(3000)
                    .quantity(10)
                    .build();
            productRepository.save(product);
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("computer")
                    .price(5000)
                    .quantity(2)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("phone")
                    .price(4000)
                    .quantity(20)
                    .build());
            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });
        };
    }

}
