package com.avanish.items.dao;

import com.avanish.items.model.CategoryRequest;
import com.avanish.items.model.SellerRequest;
import com.avanish.items.rest.v1.exception.InvalidDataException;
import com.avanish.items.rest.v1.helper.ItemsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * DAO class to talk to h2 DB
 */
@Repository
public class JdbcRepository {

    private JdbcTemplate jdbcTemplate;
    private Logger LOGGER = LoggerFactory.getLogger(ItemsHelper.class);


    /**
     * Constructor assigning dependent services.
     *
     * @param jdbcTemplate jdbcTemplate
     */
    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method returns true if SELLER is present in PREMIUM_SELLERS TABLE and
     * CATEGORY is present in APPROVED_CATEGORIES table
     *
     * @param seller   seller name
     * @param category category id
     * @return boolean
     */
    public boolean isItemEligible(String seller, int category) {

        List<Map<String, Object>> res;
        try {
            res = jdbcTemplate.queryForList(JdbcConstants.ITEM_ELIGIBLE_QUERY, seller, category);
        } catch (Exception e) {
            LOGGER.error("JdbcRepository :: isItemEligible() :: " + e);
            throw e;
        }
        return res.size() > 0;

    }

    /**
     * This method adds seller if not already present
     *
     * @param sellerRequest seller info
     * @return boolean
     */
    public boolean addSeller(SellerRequest sellerRequest) {

        int res;
        try {
            res = jdbcTemplate.update(JdbcConstants.ADD_SELLER_QUERY,
                    sellerRequest.getSeller());
        } catch (DuplicateKeyException e) {
            LOGGER.error("JdbcRepository :: addSeller() :: Seller " + sellerRequest.getSeller() + " Already Exists");
            throw new InvalidDataException("Seller " + sellerRequest.getSeller() + " already exists");
        } catch (Exception e) {
            LOGGER.error("JdbcRepository :: addSeller() :: " + e);
            throw e;
        }
        return res > 0;

    }

    /**
     * This method adds category if not already present
     *
     * @param addCategoryRequest category id
     * @return boolean
     */
    public boolean addCategory(CategoryRequest addCategoryRequest) {

        int res;
        try {
            res = jdbcTemplate.update(JdbcConstants.ADD_CATEGORY_QUERY,
                    addCategoryRequest.getCategory());
        } catch (DuplicateKeyException e) {
            LOGGER.error("JdbcRepository :: addCategory() :: Category " + addCategoryRequest.getCategory() + " Already Exists");
            throw new InvalidDataException("Category " + addCategoryRequest.getCategory() + " already exists");
        } catch (Exception e) {
            LOGGER.error("JdbcRepository :: addCategory() :: " + e);
            throw e;
        }
        return res > 0;

    }

    /**
     * This method deletes a seller if present in DB
     *
     * @param sellerRequest seller info
     * @return boolean
     */
    public boolean deleteSeller(SellerRequest sellerRequest) {

        int res;
        try {
            res = jdbcTemplate.update(JdbcConstants.DELETE_SELLER_QUERY, sellerRequest.getSeller());
        } catch (Exception e) {
            LOGGER.error("JdbcRepository :: deleteSeller() :: " + e);
            throw e;
        }
        return res > 0;

    }

    /**
     * This method deletes a category if present in DB
     *
     * @param deleteCategoryRequest seller info
     * @return boolean
     */
    public boolean deleteCategory(CategoryRequest deleteCategoryRequest) {

        int res;
        try {
            res = jdbcTemplate.update(JdbcConstants.DELETE_CATEGORY_QUERY, deleteCategoryRequest.getCategory());
        } catch (Exception e) {
            LOGGER.error("JdbcRepository :: deleteCategory() :: " + e);
            throw e;
        }
        return res > 0;

    }
}

