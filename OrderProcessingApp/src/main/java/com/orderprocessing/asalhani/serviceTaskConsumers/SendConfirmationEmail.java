package com.orderprocessing.asalhani.serviceTaskConsumers;

import com.orderprocessing.asalhani.dto.processVars.OrderDetails;
import com.orderprocessing.asalhani.shared.ProcessConfiguration;
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
        OrderDetails orderDetails = ProcessConfiguration.Util.DeserializeOrderDetailsJson(execution);

        var emailVar = orderDetails.getEmail();
        if(emailVar == null){
            throw new BpmnError(ProcessConfiguration.BpmnErrorCodes.INVALID_EMAIL, "Receiver Email is empty");
        }

        LOGGER.info("Confirmation email sent...");
    }
}
