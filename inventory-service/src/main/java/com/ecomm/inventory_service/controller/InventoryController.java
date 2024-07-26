package com.ecomm.inventory_service.controller;

import com.ecomm.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuQuantity){
        return inventoryService.isInStock(skuQuantity);
    }

    @PostMapping
    public boolean create (@RequestParam String inventory){
        return inventoryService.create(inventory);
    }

    @PutMapping("/changeorder")
    public boolean changeQuantityByOrder(@RequestParam String skuQuantity){
        return inventoryService.changeQuantityByOrder(skuQuantity);
    }

    @PutMapping("/changequantity")
    public boolean changeQuantity(@RequestParam String quantity){
        return inventoryService.updateQuantity(quantity);
    }
}
