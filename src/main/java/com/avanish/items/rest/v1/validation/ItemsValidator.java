package com.avanish.items.rest.v1.validation;

import com.avanish.items.rest.v1.exception.InvalidDataException;
import org.springframework.context.annotation.Configuration;

/**
 * Validator class for basic validations
 */
@Configuration
public class ItemsValidator {

    /**
     * validates if seller name is not empty or not null
     *
     * @param seller seller name
     */
    public boolean validateSeller(String seller) {

        if (seller == null || seller.isEmpty()) {
            throw new InvalidDataException("Invalid Data: Seller cannot be null or empty.");
        }

        return true;

    }

    /**
     * validates if seller name is not empty or not null
     *
     * @param category item category
     */
    public boolean validateCategory(int category) {

        if (category <= 0) {
            throw new InvalidDataException("Invalid Data: Category should be positive numeric integer");
        }

        return true;

    }
}
