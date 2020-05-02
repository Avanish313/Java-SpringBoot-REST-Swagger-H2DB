package com.avanish.items.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "error")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Error {

    @ApiModelProperty(value = "error message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                ", message='" + message + '\'' +
                '}';
    }
}
