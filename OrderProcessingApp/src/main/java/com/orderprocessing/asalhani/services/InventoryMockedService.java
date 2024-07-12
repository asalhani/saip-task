package com.orderprocessing.asalhani.services;


import com.orderprocessing.asalhani.dto.processVars.Product;
import com.orderprocessing.asalhani.interfaces.InventoryService;
import com.orderprocessing.asalhani.serviceTaskConsumers.CheckInventoryAvailability;
import com.orderprocessing.asalhani.shared.ProcessConfiguration;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMockedService implements InventoryService {

    // inject repository interface @Autowired

    public InventoryMockedService() {
    }

    @Override
    public Boolean CheckInventory(List<Product> products) {
        if(products == null || products.size() == 0){
            throw new BpmnError(ProcessConfiguration.BpmnErrorCodes.PRODUCTS_COUNT_INVALID,
                    "Order should have at least 1 product");
        }

        if(products.size() > 1)
            return true;

        return false;
    }
}
