package dev.michael;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.michael.demo.model.Store;

@Configuration
public static class AppConfig {

    @Bean
    public Item item1() {
        return new ItemImpl1();
    }

    @Bean
    public Store store() {
        return new Store(item1());
    }
}