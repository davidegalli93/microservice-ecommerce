package com.ecomm.productservice.service;

import com.ecomm.productservice.model.dto.CategoryDTO;

import java.util.Set;


public interface CategoryService {

    public Set<CategoryDTO> getAllCategory();
    public boolean saveCategory (CategoryDTO categoryDTO);
    public boolean deleteCategory (Long id);

}
