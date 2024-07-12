package com.orderprocessing.asalhani.dto.api;

public class UpdateOrderStatusInputDto {
    private Integer orderId;
    private String status;

    public UpdateOrderStatusInputDto() {
    }

    public UpdateOrderStatusInputDto(Integer orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
