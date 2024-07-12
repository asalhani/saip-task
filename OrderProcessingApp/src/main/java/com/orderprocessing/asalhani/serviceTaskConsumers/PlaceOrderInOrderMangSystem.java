package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.orderprocessing.asalhani.dto.processVars.OrderDetails;
import com.orderprocessing.asalhani.dto.processVars.OrderManagmentResponseDto;
import com.orderprocessing.asalhani.shared.*;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("PlaceOrderInOrderMangSystem")
public class PlaceOrderInOrderMangSystem implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("PlaceOrderInOrderMangSystem");

    private Integer MIN_ORDER_TOTAL = 1;
    private Integer MAX_ORDER_TOTAL = 100;
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        OrderDetails orderDetails = ProcessConfiguration.Util.DeserializeOrderDetailsJson(execution);

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

        var orderTotalVar = execution.getVariable(ProcessConfiguration.ProcessVars.ORDER_TOTAL);
        var orderTotal = Double.parseDouble(orderTotalVar.toString());

        if(orderTotal < MIN_ORDER_TOTAL)
            throw new BpmnError(ProcessConfiguration.BpmnErrorCodes.ORDER_TOTAL_ERROR,
                    "Order total should be greater than SAR " + MIN_ORDER_TOTAL);

        if(orderTotal > MAX_ORDER_TOTAL)
            throw new BpmnError(ProcessConfiguration.BpmnErrorCodes.GENERAL_ERROR,
                    "System is unauthorized to process order total > SAR " + MAX_ORDER_TOTAL);
    }
}
