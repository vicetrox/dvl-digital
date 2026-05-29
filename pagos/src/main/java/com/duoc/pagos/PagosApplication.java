package com.duoc.pagos; // Asegúrate de que este sea tu paquete

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PagosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagosApplication.class, args);
    }

   
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}