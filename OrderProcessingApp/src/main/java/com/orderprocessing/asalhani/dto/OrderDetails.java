package com.orderprocessing.asalhani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class OrderDetails implements Serializable {
    @JsonProperty("OrderId")
    private Integer orderId;

    @JsonProperty("CustomerName")
    private String customerName;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Products")
    private List<Product> products;

    public OrderDetails() {
    }

    public OrderDetails(Integer orderId, String customerName, String email, List<Product> products) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = products;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
