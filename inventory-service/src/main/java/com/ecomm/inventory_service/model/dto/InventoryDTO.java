package com.ecomm.inventory_service.model.dto;

public class InventoryDTO {
    //Usato per sapere se ogni articolo è in stock o no
    private String skuCode;
    private Integer quantity;

    public InventoryDTO(String skuCode, Integer quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public InventoryDTO() {
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
