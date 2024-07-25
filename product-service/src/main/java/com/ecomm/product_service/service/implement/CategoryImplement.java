package com.ecomm.product_service.service.implement;

import com.ecomm.product_service.model.dto.CategoryDTO;
import com.ecomm.product_service.model.entity.CategoryEntity;
import com.ecomm.product_service.repository.CategoryRepository;
import com.ecomm.product_service.service.CategoryService;
import com.ecomm.product_service.utils.converter.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CategoryImplement implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Override
    public Set<CategoryDTO> getAllCategory() {
        Set<CategoryDTO> listCategory = new HashSet<>();
        if (!categoryRepository.findAll().isEmpty()) {
            List<CategoryEntity> list = categoryRepository.findAll();
            for (CategoryEntity category : list) {
                listCategory.add(categoryConverter.toDto(category));
            }
        } else throw new NullPointerException("Lista categorie vuota");
        return listCategory;
    }

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryRepository.findByName(categoryDTO.getCategoryName());
        if (category == null) {
            category = new CategoryEntity(categoryDTO.getCategoryName());
            categoryRepository.save(category);
            return true;
        } else throw new IllegalArgumentException("Categoria già esistente");
    }

    @Override
    public boolean deleteCategory(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        } else throw new NullPointerException("Categoria inesistrente!");
    }
}
