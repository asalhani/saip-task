package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.orderprocessing.asalhani.dto.processVars.OrderDetails;
import com.orderprocessing.asalhani.interfaces.InventoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import  com.orderprocessing.asalhani.shared.*;

import java.util.logging.Logger;

@Component("CheckInventoryAvailabilityTaskConsumer")
public class CheckInventoryAvailability  implements JavaDelegate {

    @Autowired
    private InventoryService inventoryService;

    private final static Logger LOGGER = Logger.getLogger("CheckInventoryAvailability");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        OrderDetails orderDetails = ProcessConfiguration.Util.DeserializeOrderDetailsJson(execution);

        var inventoryResult = inventoryService.CheckInventory(orderDetails.getProducts());

        execution.setVariable(ProcessConfiguration.ProcessVars.IS_ORDER_FULFILLED, inventoryResult);
    }
}
