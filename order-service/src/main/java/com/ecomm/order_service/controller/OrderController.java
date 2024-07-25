package com.ecomm.order_service.controller;

import com.ecomm.order_service.model.dto.OrderDTO;
import com.ecomm.order_service.model.dto.OrderRequest;
import com.ecomm.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean create (@RequestBody OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable Long id){
        return orderService.delete(id);
    }
}
