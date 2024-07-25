package com.ecomm.product_service.service;

import com.ecomm.product_service.model.dto.CategoryDTO;

import java.util.Set;


public interface CategoryService {

    public Set<CategoryDTO> getAllCategory();
    public boolean saveCategory (CategoryDTO categoryDTO);
    public boolean deleteCategory (Long id);

}
