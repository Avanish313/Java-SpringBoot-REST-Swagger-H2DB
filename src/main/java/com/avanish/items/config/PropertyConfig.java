package com.avanish.items.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.avanish")
public class PropertyConfig {

    @Value("${minimum.price}")
    private double price;

    public double getPrice() {
        return price;
    }

}
