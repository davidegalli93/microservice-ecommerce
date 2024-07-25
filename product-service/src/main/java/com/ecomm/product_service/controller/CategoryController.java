package com.ecomm.product_service.controller;

import com.ecomm.product_service.model.dto.CategoryDTO;
import com.ecomm.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Set<CategoryDTO> getAll(){
        return categoryService.getAllCategory();
    }

    @PostMapping
    public boolean create (@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
