package com.orderprocessing.asalhani.services;


import com.orderprocessing.asalhani.interfaces.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    // inject repository interface @Autowired


    public OrderServiceImpl() {
    }

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
}
