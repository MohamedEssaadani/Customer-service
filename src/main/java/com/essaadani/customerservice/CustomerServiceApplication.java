package com.essaadani.customerservice;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.services.CustomerService;
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
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("customer1", "ES-SAADANI Mohamed", "essaadani@mail.com"));
            customerService.save(new CustomerRequestDTO("customer2", "Customer Customer", "customer@mail.com"));
        };
    }
}
