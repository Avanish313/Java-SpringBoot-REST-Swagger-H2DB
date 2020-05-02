package com.avanish.items.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "sellerRequest")
public class SellerRequest {

    @ApiModelProperty(required = true, value = "seller")
    private String seller;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "SellerRequest{" +
                "seller='" + seller + '\'' +
                '}';
    }
}


