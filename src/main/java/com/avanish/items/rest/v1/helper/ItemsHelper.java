package com.avanish.items.rest.v1.helper;

import com.avanish.items.config.PropertyConfig;
import com.avanish.items.dao.JdbcRepository;
import com.avanish.items.model.*;
import com.avanish.items.model.Error;
import com.avanish.items.rest.v1.exception.InvalidDataException;
import com.avanish.items.rest.v1.validation.ItemsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Helper class to call dao layer
 */
@Configuration
public class ItemsHelper {

    private PropertyConfig propertyConfig;
    private JdbcRepository jdbcRepository;
    private ItemsValidator itemsValidator;

    private Logger LOGGER = LoggerFactory.getLogger(ItemsHelper.class);

    /**
     * Constructor assigning dependent services.
     *
     * @param jdbcRepository jdbcRepository
     */
    @Autowired
    public ItemsHelper(JdbcRepository jdbcRepository, PropertyConfig propertyConfig, ItemsValidator itemsValidator) {
        this.jdbcRepository = jdbcRepository;
        this.propertyConfig = propertyConfig;
        this.itemsValidator = itemsValidator;
    }

    /**
     * This method will call the dao layer to check if item is eligible
     * under the new shipping program.
     *
     * @param itemEligibilityCheckRequest request body
     * @return itemEligibilityCheckResponse as a JSON
     */
    public ItemEligibilityCheckResponse isItemEligible(ItemEligibilityCheckRequest itemEligibilityCheckRequest) {

        ItemEligibilityCheckResponse itemEligibilityCheckResponse = new ItemEligibilityCheckResponse();
        String seller = itemEligibilityCheckRequest.getSeller();
        int category = itemEligibilityCheckRequest.getCategory();
        double price = itemEligibilityCheckRequest.getPrice();

        try {
            if (price < propertyConfig.getPrice()) {
                throw new InvalidDataException("Minimum qualified price for this program is: $" + propertyConfig.getPrice());
            }
            itemsValidator.validateSeller(seller);
            itemsValidator.validateCategory(category);

            if (jdbcRepository.isItemEligible(seller, category)) {
                itemEligibilityCheckResponse.setEligible(Boolean.TRUE);
            }

        } catch (Exception e) {
            LOGGER.error("ItemsHelper :: isItemEligible :: " + e);
            setError(itemEligibilityCheckResponse, e);
        }

        return itemEligibilityCheckResponse;
    }

    /**
     * This method will call the dao layer to add a new seller
     * to the new shipping program.
     * <p>
     * if seller already exists, throw 400
     * any other exception, throw 500
     *
     * @param addSellerRequest request body
     * @return addSellerResponse as a JSON
     */
    public AddSellerResponse addSeller(SellerRequest addSellerRequest) {

        AddSellerResponse addSellerResponse = new AddSellerResponse();

        try {
            itemsValidator.validateSeller(addSellerRequest.getSeller());
            boolean res = jdbcRepository.addSeller(addSellerRequest);
            if (res) {
                addSellerResponse.setMessage("Successfully added seller " + addSellerRequest.getSeller());
            }
        } catch (Exception e) {
            LOGGER.error("ItemsHelper :: addSeller :: " + e.getMessage());
            setError(addSellerResponse, e);
        }

        return addSellerResponse;

    }

    /**
     * This method will call the dao layer to add a new category
     * to the new shipping program.
     * <p>
     * if category already exists, will send error message
     * any other exception, throws 500 with exception details
     *
     * @param addCategoryRequest request body
     * @return addCategoryResponse as a JSON
     */
    public AddCategoryResponse addCategory(CategoryRequest addCategoryRequest) {

        AddCategoryResponse addCategoryResponse = new AddCategoryResponse();

        try {
            itemsValidator.validateCategory(addCategoryRequest.getCategory());
            boolean res = jdbcRepository.addCategory(addCategoryRequest);
            if (res) {
                addCategoryResponse.setMessage("Successfully added category " + addCategoryRequest.getCategory());
            }
        } catch (Exception e) {
            LOGGER.error("ItemsHelper :: addCategory :: " + e.getMessage());
            setError(addCategoryResponse, e);
        }

        return addCategoryResponse;

    }

    /**
     * This method will call the dao layer to delete an existing seller
     * from the new shipping program.
     * <p>
     * if seller does not exist, will send an error message
     * any other exception, throw 500 with exception details
     *
     * @param deleteSellerRequest request body
     * @return deleteSellerResponse as a JSON
     */
    public DeleteSellerResponse deleteSeller(SellerRequest deleteSellerRequest) {

        DeleteSellerResponse deleteSellerResponse = new DeleteSellerResponse();
        try {
            itemsValidator.validateSeller(deleteSellerRequest.getSeller());
            boolean res = jdbcRepository.deleteSeller(deleteSellerRequest);
            if (res) {
                deleteSellerResponse.setMessage("Successfully deleted seller " + deleteSellerRequest.getSeller());
            } else {
                throw new InvalidDataException("Seller " + deleteSellerRequest.getSeller() + " does not exist");
            }

        } catch (Exception e) {
            LOGGER.error("ItemsHelper :: deleteSeller :: " + e.getMessage());
            setError(deleteSellerResponse, e);
        }
        return deleteSellerResponse;

    }

    /**
     * This method will call the dao layer to delete an existing category
     * from the new shipping program.
     * <p>
     * if category does not exist, will send an error message
     * any other exception, throw 500 with exception details
     *
     * @param deleteCategoryRequest request body
     * @return deleteCategoryResponse as a JSON
     */
    public DeleteCategoryResponse deleteCategory(CategoryRequest deleteCategoryRequest) {

        DeleteCategoryResponse deleteCategoryResponse = new DeleteCategoryResponse();
        try {
            itemsValidator.validateCategory(deleteCategoryRequest.getCategory());
            boolean res = jdbcRepository.deleteCategory(deleteCategoryRequest);
            if (res) {
                deleteCategoryResponse.setMessage("Successfully deleted category " + deleteCategoryRequest.getCategory());
            } else {
                throw new InvalidDataException("Category " + deleteCategoryRequest.getCategory() + " does not exist");
            }

        } catch (Exception e) {
            LOGGER.error("ItemsHelper :: deleteCategory :: " + e.getMessage());
            setError(deleteCategoryResponse, e);
        }
        return deleteCategoryResponse;

    }

    /**
     * This method will set the server error 500
     * for all exceptions
     *
     * @param obj Class object
     * @param e   Exception object
     * @param <T> Generic type
     */
    private <T> void setError(final T obj, final T e) {

        if (obj == null || e == null) return;

        Error error = new Error();
        if (e instanceof InvalidDataException) {
            error.setMessage(((InvalidDataException) e).getMessage());
        } else if (e instanceof Exception) {
            error.setMessage(e.toString());
        }

        if (obj instanceof ItemEligibilityCheckResponse) {
            ((ItemEligibilityCheckResponse) obj).setError(error);
        } else if (obj instanceof AddSellerResponse) {
            ((AddSellerResponse) obj).setError(error);
        } else if (obj instanceof DeleteSellerResponse) {
            ((DeleteSellerResponse) obj).setError(error);
        } else if (obj instanceof AddCategoryResponse) {
            ((AddCategoryResponse) obj).setError(error);
        } else if (obj instanceof DeleteCategoryResponse) {
            ((DeleteCategoryResponse) obj).setError(error);
        }

    }

}

