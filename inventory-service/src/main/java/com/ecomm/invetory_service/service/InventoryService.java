package com.ecomm.invetory_service.service;

import com.ecomm.invetory_service.model.dto.InventoryResponse;
import com.ecomm.invetory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventoryEntity -> new InventoryResponse(
                        inventoryEntity.getSkuCode(),
                        inventoryEntity.getQuantity() > 0
                ))
                .toList();
    }

}
