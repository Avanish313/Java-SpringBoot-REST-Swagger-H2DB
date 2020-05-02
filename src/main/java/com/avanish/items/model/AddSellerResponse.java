package com.avanish.items.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "addSellerResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSellerResponse {

    @ApiModelProperty(value = "success message")
    private String message;

    @ApiModelProperty(value = "error")
    private Error error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AddSellerResponse{" +
                "message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
