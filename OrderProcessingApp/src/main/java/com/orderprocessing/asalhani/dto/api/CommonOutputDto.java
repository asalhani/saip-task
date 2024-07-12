package com.orderprocessing.asalhani.dto.api;

public class CommonOutputDto {
    private Boolean isSuccess;

    public CommonOutputDto(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
