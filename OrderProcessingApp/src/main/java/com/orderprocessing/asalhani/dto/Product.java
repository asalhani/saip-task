package com.orderprocessing.asalhani.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product  implements Serializable {

    @JsonProperty("ProductName")
    private String productName;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
