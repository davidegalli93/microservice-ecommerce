package com.ecomm.orderservice.model.dto;

public class InventoryResponse {
    //Utilizzato in questo service come risposta al servizio dell'inventory
    private String skyCode;
    private boolean isInStock;

    public InventoryResponse() {
    }

    public InventoryResponse(String skyCode, boolean isInStock) {
        this.skyCode = skyCode;
        this.isInStock = isInStock;
    }

    public String getSkyCode() {
        return skyCode;
    }

    public void setSkyCode(String skyCode) {
        this.skyCode = skyCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
