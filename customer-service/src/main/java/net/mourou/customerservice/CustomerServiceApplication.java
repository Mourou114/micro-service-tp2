package net.mourou.customerservice;

import net.mourou.customerservice.entities.Customer;
import net.mourou.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return arg -> {
            Customer customer = Customer.builder()
                    .name("youness")
                    .email("mourou114@gmail.com")
                    .build();
            customerRepository.save(customer);
            customerRepository.save(Customer.builder()
                    .name("karim")
                    .email("karim4@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("salma")
                    .email("alma@gmail.com")
                    .build());
            customerRepository.findAll().forEach(c->{
                System.out.println(c.getName());
            });
        };
    }

}
