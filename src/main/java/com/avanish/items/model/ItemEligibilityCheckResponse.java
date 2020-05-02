package com.avanish.items.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "itemEligibilityCheckResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemEligibilityCheckResponse {

    @ApiModelProperty(value = "eligible")
    private boolean eligible;

    @ApiModelProperty(value = "error")
    private Error error;

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return "ItemEligibilityCheckResponse{" +
                "eligible=" + eligible +
                ", error=" + error +
                '}';
    }
}
