package com.avanish.items;

import com.avanish.items.config.PropertyConfig;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Class to bootstrap and run Spring Boot application
 */
@SpringBootApplication(scanBasePackages = {"com.avanish.items"})
@EnableConfigurationProperties(PropertyConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

