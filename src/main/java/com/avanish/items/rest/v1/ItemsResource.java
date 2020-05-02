package com.avanish.items.rest.v1;

import com.avanish.items.model.*;
import com.avanish.items.model.Error;
import com.avanish.items.rest.v1.helper.ItemsHelper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This REST resource class expose Items API to consumers.
 */
@RestController
@RequestMapping(value = "/api/v1/items")
@Api(tags = "The new shipping program API")
public class ItemsResource {

    private Logger LOGGER = LoggerFactory.getLogger(ItemsResource.class);
    private ItemsHelper itemsHelper;

    /**
     * Constructor assigning dependent services.
     *
     * @param itemsHelper helper class
     */
    @Autowired
    public ItemsResource(ItemsHelper itemsHelper) {
        this.itemsHelper = itemsHelper;
    }

    /**
     * This service will indicate that the item is eligible under new shipping program.
     *
     * @param itemEligibilityCheckRequest request body
     * @return itemEligibilityCheckResponse as a JSON
     */
    @ApiOperation(
            value = "This service will indicate whether the item is eligible under the new shipping program or not",
            notes = "The normal use of this service is to indicate whether the item is eligible under the new shipping program or not." +
                    "User needs to pass item eligibility check request like item title, seller of the item, item category and item price"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "item eligible - Success", response = ItemEligibilityCheckResponse.class),
                    @ApiResponse(code = 400, message = "item eligible - Bad Request", response = Error.class),
                    @ApiResponse(code = 500, message = "item eligible - Server Error", response = Error.class)
            })
    @PostMapping("/item/eligible")
    public ItemEligibilityCheckResponse itemEligible(
            @ApiParam(name = "itemEligibilityCheckRequest", value = "Request Body", required = true) @RequestBody final ItemEligibilityCheckRequest itemEligibilityCheckRequest) {

        LOGGER.info("checkItemEligibility() :: " + itemEligibilityCheckRequest.toString());

        ItemEligibilityCheckResponse itemEligibilityCheckResponse = itemsHelper.isItemEligible(itemEligibilityCheckRequest);

        LOGGER.info("checkItemEligibility() :: " + itemEligibilityCheckResponse.toString());

        return itemEligibilityCheckResponse;
    }

    /**
     * This service will add a new seller to the program.
     *
     * @param addSellerRequest request body
     * @return addSellerResponse as a JSON
     */
    @ApiOperation(
            value = "This service will add a new seller to the list of premium sellers if not already exists",
            notes = "The normal use of this service is to add a seller to the list of premium sellers who can " +
                    "use the new shipping program. User needs to send the seller name to call this service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "add seller - Success", response = AddSellerResponse.class),
                    @ApiResponse(code = 400, message = "add seller - Bad Request", response = Error.class),
                    @ApiResponse(code = 500, message = "add seller - Server Error", response = Error.class)
            })
    @PostMapping("/seller")
    public AddSellerResponse addSeller(
            @ApiParam(name = "addSellerRequest", value = "Request Body", required = true) @RequestBody final SellerRequest addSellerRequest) {

        LOGGER.info("addSeller() :: Add" + addSellerRequest.toString());

        AtomicReference<AddSellerResponse> addSellerResponse = new AtomicReference<>(itemsHelper.addSeller(addSellerRequest));

        LOGGER.info("addSeller() :: " + addSellerResponse.toString());

        return addSellerResponse.get();
    }

    /**
     * This service will delete a seller from the program.
     *
     * @param deleteSellerRequest request body
     * @return DeleteSellerResponse as a JSON
     */
    @ApiOperation(
            value = "This service will delete a seller from the list of premium sellers if already exists",
            notes = "The normal use of this service is to delete a seller from the list of premium sellers who no longer can " +
                    "use the new shipping program. User needs to send the seller name to call this service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "delete seller - Success", response = DeleteSellerResponse.class),
                    @ApiResponse(code = 400, message = "delete seller - Bad Request", response = Error.class),
                    @ApiResponse(code = 500, message = "delete seller - Server Error", response = Error.class)
            })
    @DeleteMapping("/seller")
    public DeleteSellerResponse deleteSeller(
            @ApiParam(name = "deleteSellerRequest", value = "Request Body", required = true) @RequestBody final SellerRequest deleteSellerRequest) {

        LOGGER.info("deleteSeller() :: Delete" + deleteSellerRequest.toString());

        AtomicReference<DeleteSellerResponse> deleteSellerResponse = new AtomicReference<>(itemsHelper.deleteSeller(deleteSellerRequest));

        LOGGER.info("deleteSeller() :: " + deleteSellerResponse.toString());

        return deleteSellerResponse.get();
    }

    /**
     * This service will add a new category to the program.
     *
     * @param addCategoryRequest request body
     * @return addCategoryResponse as a JSON
     */
    @ApiOperation(
            value = "This service will add a new category to the list of premium categories if not already exists",
            notes = "The normal use of this service is to add a category to the list of premium categories who can " +
                    "use the new shipping program. User needs to send the category id to call this service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "add category - Success", response = AddCategoryResponse.class),
                    @ApiResponse(code = 400, message = "add category - Bad Request", response = Error.class),
                    @ApiResponse(code = 500, message = "add category - Server Error", response = Error.class)
            })
    @PostMapping("/category")
    public AddCategoryResponse addCategory(
            @ApiParam(name = "addCategoryRequest", value = "Request Body", required = true) @RequestBody final CategoryRequest addCategoryRequest) {

        LOGGER.info("addCategory() :: Add" + addCategoryRequest.toString());

        AtomicReference<AddCategoryResponse> addCategoryResponse = new AtomicReference<>(itemsHelper.addCategory(addCategoryRequest));

        LOGGER.info("addSeller() :: " + addCategoryResponse.toString());

        return addCategoryResponse.get();
    }

    /**
     * This service will delete a category from the program.
     *
     * @param deleteCategoryRequest request body
     * @return DeleteCategoryResponse as a JSON
     */
    @ApiOperation(
            value = "This service will delete a category from the list of premium categories if already exists",
            notes = "The normal use of this service is to delete a category from the list of premium categories who no longer can " +
                    "use the new shipping program. User needs to send the category id to call this service"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "delete category - Success", response = DeleteSellerResponse.class),
                    @ApiResponse(code = 400, message = "delete category - Bad Request", response = Error.class),
                    @ApiResponse(code = 500, message = "delete category - Server Error", response = Error.class)
            })
    @DeleteMapping("/category")
    public DeleteCategoryResponse deleteCategory(
            @ApiParam(name = "deleteCategoryRequest", value = "Request Body", required = true) @RequestBody final CategoryRequest deleteCategoryRequest) {

        LOGGER.info("deleteCategory() :: Delete" + deleteCategoryRequest.toString());

        AtomicReference<DeleteCategoryResponse> deleteCategoryResponse = new AtomicReference<>(itemsHelper.deleteCategory(deleteCategoryRequest));

        LOGGER.info("deleteCategory() :: " + deleteCategoryResponse.toString());

        return deleteCategoryResponse.get();
    }
}

