package com.avanish.items.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "categoryRequest")
public class CategoryRequest {

    @ApiModelProperty(required = true, value = "category")
    private int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "category='" + category + '\'' +
                '}';
    }
}


