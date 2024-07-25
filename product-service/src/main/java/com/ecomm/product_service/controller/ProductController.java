package com.ecomm.product_service.controller;

import com.ecomm.product_service.model.dto.ProductDTO;
import com.ecomm.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll (){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ProductDTO getById (@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public boolean create (@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/{id}")
    public boolean update (@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
