package com.ecomm.productservice.utils.converter;

import com.ecomm.productservice.model.dto.ProductDTO;
import com.ecomm.productservice.model.entity.CategoryEntity;
import com.ecomm.productservice.model.entity.ProductEntity;
import com.ecomm.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    CategoryRepository categoryRepository;


    public ProductEntity toEntity (ProductDTO productDTO){
        ProductEntity product = new ProductEntity();
        CategoryEntity category = categoryRepository.findByName(productDTO.getProductCategory());
        product.setDescription(productDTO.getProductDescription());
        product.setName(productDTO.getProductName());
        product.setPrice(productDTO.getProductPrice());
        product.setCategory(category);
        if (productDTO.getProductId() != null) {
            product.setId(productDTO.getProductId());
        }
        return product;
    }

    public ProductDTO toDTO (ProductEntity product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCategory(product.getCategory().toString());
        productDTO.setProductDescription(product.getDescription());
        productDTO.setProductId(product.getId());
        productDTO.setProductName(product.getName());
        productDTO.setProductPrice(product.getPrice());
        return productDTO;
    }
}
