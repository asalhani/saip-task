package com.orderprocessing.asalhani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product  implements Serializable {

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("Price")
    private Double price;

    public Product() {
    }

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
