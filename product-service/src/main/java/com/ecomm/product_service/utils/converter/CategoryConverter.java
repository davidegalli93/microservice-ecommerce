package com.ecomm.product_service.utils.converter;

import com.ecomm.product_service.model.dto.CategoryDTO;
import com.ecomm.product_service.model.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getCategoryName());
        if(categoryDTO.getCategoryID() != null){
            category.setId(categoryDTO.getCategoryID());
        }
        return category;
    }

    public CategoryDTO toDto(CategoryEntity category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getName());
        categoryDTO.setCategoryID(category.getId());
        return categoryDTO;
    }
}
