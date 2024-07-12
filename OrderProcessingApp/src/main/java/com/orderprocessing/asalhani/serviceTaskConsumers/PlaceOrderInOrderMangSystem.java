package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.asalhani.dto.OrderDetails;
import com.orderprocessing.asalhani.dto.OrderManagmentResponseDto;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("PlaceOrderInOrderMangSystem")
public class PlaceOrderInOrderMangSystem implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("PlaceOrderInOrderMangSystem");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        var orderDetailsVar = execution.getVariableTyped("OrderDetails", false);
        ObjectMapper mapper = new ObjectMapper();
        OrderDetails orderDetails = mapper.readValue(orderDetailsVar.getValue().toString(), OrderDetails.class);

        // TODO: Call external (mocked) API

        // TODO: Handle BPMN error as rqeuested

        // TODO: Set "IsOrderFulfilled" in a dynamic way (if order number is even = true, odd = false)

        // create response object
        var response = new OrderManagmentResponseDto();
        response.setOrderId(orderDetails.getOrderId());
        response.setSuccess(true);
        response.setMessage("Order successfully added.");

        // set var JSON value (Serialization)
//        ObjectValue typedCustomerValue = Variables.objectValue(response).serializationDataFormat("application/json").create();
//        execution.setVariable("OrderMangSystemResponse", typedCustomerValue);

        var orderTotalVar = execution.getVariable("OrderTotal");
        var orderTotal = Double.parseDouble(orderTotalVar.toString());
        if(orderTotal < 1)
            throw new BpmnError("OrderTotalError", "Order total should be greater than 1 - General BPMN Error from " + CheckInventoryAvailability.class);
    }
}
