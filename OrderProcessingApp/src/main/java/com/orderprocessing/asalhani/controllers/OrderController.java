package com.orderprocessing.asalhani.controllers;

import com.orderprocessing.asalhani.dto.api.CommonOutputDto;
import com.orderprocessing.asalhani.dto.api.UpdateOrderStatusInputDto;
import com.orderprocessing.asalhani.interfaces.OrderService;
import com.orderprocessing.asalhani.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @PostMapping(path = "/update-order-status", produces = "application/json")
    public ResponseEntity<CommonOutputDto> UpdateOrderStatus(@RequestBody UpdateOrderStatusInputDto input){
        var result = orderService.UpdateOrderStatus(input.getOrderId(), input.getStatus());
        if(result){
            return new ResponseEntity<>(new CommonOutputDto(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CommonOutputDto(false), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/order-delivred/{orderId}")
    @ResponseBody
    public ResponseEntity<CommonOutputDto> OrderedDelivred(@PathVariable("orderId") Integer orderId){
        var result = orderService.OrderDelivred(orderId);
        if(result){
            return new ResponseEntity<>(new CommonOutputDto(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CommonOutputDto(false), HttpStatus.BAD_REQUEST);

    }

}
