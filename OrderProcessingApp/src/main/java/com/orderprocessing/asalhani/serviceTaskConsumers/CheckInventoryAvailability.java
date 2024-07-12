package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.asalhani.dto.OrderDetails;
import com.orderprocessing.asalhani.interfaces.InventoryService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("CheckInventoryAvailabilityTaskConsumer")
public class CheckInventoryAvailability  implements JavaDelegate {

    @Autowired
    private InventoryService inventoryService;

    private final static Logger LOGGER = Logger.getLogger("CheckInventoryAvailability");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        var orderDetailsVar = execution.getVariableTyped("OrderDetails", false);
        ObjectMapper mapper = new ObjectMapper();
        OrderDetails orderDetails = mapper.readValue(orderDetailsVar.getValue().toString(), OrderDetails.class);

        var inventoryResult = inventoryService.CheckInventory(orderDetails.getProducts());

        execution.setVariable("IsOrderFulfilled", inventoryResult);

        LOGGER.info("IsOrderFulfilled = " + inventoryResult);
    }
}
