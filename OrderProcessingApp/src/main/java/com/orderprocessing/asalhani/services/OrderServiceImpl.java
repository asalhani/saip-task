package com.orderprocessing.asalhani.services;


import com.oracle.truffle.js.builtins.JavaBuiltins;
import com.orderprocessing.asalhani.interfaces.OrderService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orderprocessing.asalhani.shared.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger LOGGER = Logger.getLogger("OrderServiceImpl");


    public OrderServiceImpl() {
    }

    @Autowired
    private RuntimeService runtimeService;

    public Boolean UpdateOrderStatus(Integer orderId, String orderStatus){
        // TODO: using repository interface, get order by id

        // TODO: validate that request is exist, status can be changed, user have access to it, ....

        // TODO: update request status in db

        // Convert orderId to String
        String orderIdStr = orderId.toString();

        // Check if the first character is '9'
        if (orderIdStr.charAt(0) == '9') {
            return false;
        }
        return true;
    }

    @Override
    public Boolean OrderDelivred(Integer orderId) {
        try{
            // correlate the message
            MessageCorrelationResult result = runtimeService.createMessageCorrelation(ProcessConfiguration.BpmnMessage.ORDER_DELIVRED)
                    .setVariable(ProcessConfiguration.ProcessVars.ORDER_ID, orderId)
                    .correlateWithResult();

            return true;
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Message can't be correlated. No process matched with correlation input. ");
            return false;
        }

    }
}
