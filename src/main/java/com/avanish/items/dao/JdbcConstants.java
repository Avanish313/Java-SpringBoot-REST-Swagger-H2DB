package com.avanish.items.dao;

/**
 * class to declare jdbc queries constants
 */
public class JdbcConstants {

    public final static String ITEM_ELIGIBLE_QUERY = "SELECT P.SELLER, A.CATEGORY FROM PREMIUM_SELLERS P, APPROVED_CATEGORIES A WHERE P.SELLER = ? AND A.CATEGORY = ?";
    public final static String ADD_SELLER_QUERY = "INSERT INTO PREMIUM_SELLERS (SELLER) VALUES (?)";
    public final static String ADD_CATEGORY_QUERY = "INSERT INTO APPROVED_CATEGORIES (CATEGORY) VALUES (?)";
    public final static String DELETE_SELLER_QUERY = "DELETE FROM PREMIUM_SELLERS WHERE SELLER = ?";
    public final static String DELETE_CATEGORY_QUERY = "DELETE FROM APPROVED_CATEGORIES WHERE CATEGORY = ?";

}
