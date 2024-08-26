package com.ecomm.inventoryservice.repository;

import com.ecomm.inventoryservice.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional <InventoryEntity> findBySkuCode(String skuCode);

    List<InventoryEntity> findBySkuCodeIn(List<String> skuCode);
}
