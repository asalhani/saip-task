package com.orderprocessing.asalhani.interfaces;

import com.orderprocessing.asalhani.dto.processVars.Product;

import java.util.List;

public interface InventoryService {
    public Boolean CheckInventory(List<Product> products);
}
