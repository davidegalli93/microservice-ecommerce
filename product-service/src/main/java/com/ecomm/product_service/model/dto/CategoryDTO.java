package com.ecomm.product_service.model.dto;

public class CategoryDTO {

    private Long categoryID;
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
