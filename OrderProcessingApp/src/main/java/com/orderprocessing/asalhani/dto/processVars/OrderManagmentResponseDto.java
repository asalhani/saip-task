package com.orderprocessing.asalhani.dto.processVars;

import org.camunda.feel.syntaxtree.In;

public class OrderManagmentResponseDto {
    private Integer orderId;
    private Boolean isSuccess;
    private String message;

    public OrderManagmentResponseDto() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
