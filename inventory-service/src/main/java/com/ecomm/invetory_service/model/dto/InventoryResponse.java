package com.ecomm.invetory_service.model.dto;

public class InventoryResponse {
    //Usato per sapere se ogni articolo è in stock o no
    private String skuCode;
    private boolean isInStock;

    public InventoryResponse() {
    }

    public InventoryResponse(String skyCode, boolean isInStock) {
        this.skuCode = skyCode;
        this.isInStock = isInStock;
    }

    public String getSkyCode() {
        return skuCode;
    }

    public void setSkyCode(String skyCode) {
        this.skuCode = skyCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
