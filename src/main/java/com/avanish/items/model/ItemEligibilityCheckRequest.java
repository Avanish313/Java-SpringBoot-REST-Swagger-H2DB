package com.avanish.items.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "itemEligibilityCheckRequest")
public class ItemEligibilityCheckRequest {

    /**
     * Title of the item
     */
    @ApiModelProperty(value = "Title of the item")
    private String title;

    /**
     * Seller of the item
     */
    @ApiModelProperty(required = true, value = "Seller of the item")
    private String seller;

    /**
     * Item category
     */
    @ApiModelProperty(required = true, value = "Item category")
    private int category;

    /**
     * Item price
     */
    @ApiModelProperty(required = true, value = "Item price")
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Convert {@Link ItemEligibilityCheckRequest} object to printable format
     *
     * @return object value in printable format
     */
    @Override
    public String toString() {
        return "ItemEligibilityCheckRequest{" +
                "title='" + title + '\'' +
                ", seller='" + seller + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

}
