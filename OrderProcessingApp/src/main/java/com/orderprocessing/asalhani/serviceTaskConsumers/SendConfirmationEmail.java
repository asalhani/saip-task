package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.asalhani.dto.OrderDetails;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("SendConfirmationEmail")
public class SendConfirmationEmail implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("SendConfirmationEmail");

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // JSON Var deserialization
        var orderDetailsVar = execution.getVariableTyped("OrderDetails", false);
        ObjectMapper mapper = new ObjectMapper();
        OrderDetails orderDetails = mapper.readValue(orderDetailsVar.getValue().toString(), OrderDetails.class);


        var emailVar = orderDetails.getEmail();
        if(emailVar == null){
            throw new BpmnError("InvalidEmail", "Receiver Email is empty");
        }
        LOGGER.info("Confirmation email sent...");
    }
}
