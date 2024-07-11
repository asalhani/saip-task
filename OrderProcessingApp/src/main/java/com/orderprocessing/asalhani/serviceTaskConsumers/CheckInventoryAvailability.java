package com.orderprocessing.asalhani.serviceTaskConsumers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("CheckInventoryAvailabilityTaskConsumer")
public class CheckInventoryAvailability  implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("CheckInventoryAvailability");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        var orderDetails = execution.getVariable("OrderDetails");

        // TODO: Call external (mocked) API

        // TODO: Handle BPMN error as rqeuested

        // TODO: Set "IsOrderFulfilled" in a dynamic way (if order number is even = true, odd = false)
        execution.setVariable("IsOrderFulfilled", true);

        LOGGER.info("IsOrderFulfilled = " + true);
    }
}
