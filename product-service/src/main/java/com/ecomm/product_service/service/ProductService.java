package com.ecomm.product_service.service;

import com.ecomm.product_service.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO getById (Long id);
    public List<ProductDTO> getAllProduct();
    public boolean saveProduct (ProductDTO productDTO);
    public boolean updateProduct (Long id, ProductDTO productDTO);
    public boolean deleteProduct (Long id);
}
