package com.orderprocessing.asalhani.interfaces;

public interface OrderService {
    public Boolean UpdateOrderStatus(Integer orderId, String orderStatus);

    public Boolean OrderDelivred(Integer orderId);
}
