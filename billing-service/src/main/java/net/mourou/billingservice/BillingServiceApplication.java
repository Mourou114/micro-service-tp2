package net.mourou.billingservice;

import net.mourou.billingservice.entities.Bill;
import net.mourou.billingservice.entities.ProductItem;
import net.mourou.billingservice.feign.CustomerRestClient;
import net.mourou.billingservice.feign.ProductRestClient;
import net.mourou.billingservice.model.Customer;
import net.mourou.billingservice.model.Product;
import net.mourou.billingservice.repository.BillRepository;
import net.mourou.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient
                                        ){

       return args -> {
           Collection<Customer> customers = customerRestClient.getALLCustomers().getContent();
           Collection<Product> products = productRestClient.getALLProducts().getContent();


           customers.forEach(customer -> {
               Bill bill = Bill.builder()
                       .billingDate(new Date())
                       .customerId(customer.getId())
                       .build();
               billRepository.save(bill);
               products.forEach(product ->{
                   ProductItem productItem = ProductItem.builder()
                           .bill(bill)
                           .productId(product.getId())
                           .quantity(1+new Random().nextInt(10))
                           .unitPrice(product.getPrice())
                           .build();
                   productItemRepository.save(productItem);
               });
           });
       };


    }

}
