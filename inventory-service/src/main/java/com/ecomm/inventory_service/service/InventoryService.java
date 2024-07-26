package com.ecomm.inventory_service.service;

import com.ecomm.inventory_service.model.dto.InventoryDTO;
import com.ecomm.inventory_service.model.entity.InventoryEntity;
import com.ecomm.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuQuantity) {
        Map<String, Integer> productMap = regexToMap(skuQuantity);
        return inventoryRepository.findBySkuCodeIn(productMap.keySet().stream().toList()).stream()
                .allMatch(inventoryEntity -> {
                    Integer quantityInventory = productMap.get(inventoryEntity.getSkuCode());
                    return inventoryEntity.getQuantity() >= quantityInventory;
                });
    }

    public boolean changeQuantityByOrder(String skuQuantity) {
        Map<String, Integer> productMap = regexToMap(skuQuantity);
        try {
            inventoryRepository.findBySkuCodeIn(productMap.keySet().stream().toList())
                    .forEach(inventoryEntity -> {
                        Integer quantityChange = productMap.get(inventoryEntity.getSkuCode());
                        if (quantityChange != null) {
                            inventoryEntity.setQuantity(inventoryEntity.getQuantity() - quantityChange);
                            inventoryRepository.save(inventoryEntity);
                        }
                    });
            return true;
        } catch (ArithmeticException e) {
            System.out.println("Quantità ordinata non presente in magazzino");
            return false;
        }
    }

    public boolean create (String inventory) {
        String[] parts = inventory.split(",");
        String firstPart = parts[0];
        Integer secondPart = Integer.parseInt(parts[1]);

        InventoryEntity newInventory = new InventoryEntity(firstPart,secondPart);
        inventoryRepository.save(newInventory);
        return true;
    }

    //Metodo per stringhe lunghe per URL che modifica in MAP
    public Map<String, Integer> regexToMap(String allProduct) {
        Map<String, Integer> productMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+):(\\w+)");
        Matcher matcher = pattern.matcher(allProduct);
        while (matcher.find()) {
            productMap.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
        return productMap;
    }

    public boolean updateQuantity(String quantity) {
        String[] parts = quantity.split(",");
        String firstPart = parts[0];
        Integer secondPart = Integer.parseInt(parts[1]);

        Optional<InventoryEntity> inventory = inventoryRepository.findBySkuCode(firstPart);
        if(inventory.isPresent()){
            inventory.get().setQuantity(secondPart);
            inventoryRepository.save(inventory.get());
            return true;
        } else throw new NullPointerException("Posizione in inventario non trovata");
    }
}
