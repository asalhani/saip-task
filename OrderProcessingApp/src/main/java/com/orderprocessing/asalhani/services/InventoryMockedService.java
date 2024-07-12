package com.orderprocessing.asalhani.services;


import com.orderprocessing.asalhani.dto.Product;
import com.orderprocessing.asalhani.interfaces.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMockedService implements InventoryService {

    // inject repository interface @Autowired

    public InventoryMockedService() {
    }

    @Override
    public Boolean CheckInventory(List<Product> products) {
        if(products == null)
            return false;

        if(products.size() > 1)
            return true;

        return false;
    }
}
