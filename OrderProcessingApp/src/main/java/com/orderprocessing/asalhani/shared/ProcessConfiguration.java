package com.orderprocessing.asalhani.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.asalhani.dto.processVars.OrderDetails;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class ProcessConfiguration {
    public static class ProcessVars{
        public static String ORDER_DETAILS = "OrderDetails";
        public static String IS_ORDER_FULFILLED = "IsOrderFulfilled";
        public static String ORDER_TOTAL = "OrderTotal";
    }

    public static class BpmnErrorCodes{
        public static String  ORDER_TOTAL_ERROR = "OrderTotalError";
        public static String  INVALID_EMAIL = "InvalidEmail";
    }

    public static class Util {
        public static OrderDetails DeserializeOrderDetailsJson(DelegateExecution execution) throws JsonProcessingException {
            var orderDetailsVar = execution.getVariableTyped(ProcessConfiguration.ProcessVars.ORDER_DETAILS, false);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(orderDetailsVar.getValue().toString(), OrderDetails.class);
        }
    }

}
