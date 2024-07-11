package com.orderprocessing.asalhani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class OrderDetails implements Serializable {
    @JsonProperty("OrderId")
    private Integer orderId;

    @JsonProperty("CustomerName")
    private String customerName;

    @JsonProperty("Products")
    private List<Product> products;

    public OrderDetails() {
    }

    public OrderDetails(Integer orderId, String customerName, List<Product> products) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = products;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
