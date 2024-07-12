package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.asalhani.dto.OrderDetails;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("UpdateOrderStatusInCrm")
public class UpdateOrderStatusInCrm implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("CheckInventoryAvailability");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        var orderDetailsVar = execution.getVariableTyped("OrderDetails", false);
        ObjectMapper mapper = new ObjectMapper();
        OrderDetails orderDetails = mapper.readValue(orderDetailsVar.getValue().toString(), OrderDetails.class);

        // TODO: Call external (mocked) API

        // TODO: Handle BPMN error as rqeuested

        // TODO: Set "OrderStatusVar" in a dynamic way (if order number is even = true, odd = false)
        execution.setVariable("OrderStatus", "Shipped");
    }
}
