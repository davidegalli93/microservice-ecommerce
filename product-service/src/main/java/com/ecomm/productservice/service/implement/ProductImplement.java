package com.ecomm.productservice.service.implement;

import com.ecomm.productservice.model.dto.ProductDTO;
import com.ecomm.productservice.model.entity.CategoryEntity;
import com.ecomm.productservice.model.entity.ProductEntity;
import com.ecomm.productservice.repository.CategoryRepository;
import com.ecomm.productservice.repository.ProductRepository;
import com.ecomm.productservice.service.ProductService;
import com.ecomm.productservice.utils.converter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConverter  productConverter;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public ProductDTO getById(Long id) {
        if(productRepository.findById(id).isPresent()){
            ProductEntity product = productRepository.findById(id).get();
            return productConverter.toDTO(product);
        } else throw new NullPointerException("Prodotto non esistente!");
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> listProducts = new ArrayList<>();
        if(!productRepository.findAll().isEmpty()){
            List<ProductEntity> list = productRepository.findAll();
            for (ProductEntity product : list){
                listProducts.add(productConverter.toDTO(product));
            }
        } else throw new NullPointerException("Nessun prodotto esistente");
        return listProducts;
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) {
        ProductEntity product = productRepository.findByName((productDTO.getProductName()));
        CategoryEntity category = categoryRepository.findByName(productDTO.getProductCategory());
        if (product == null){
            ProductEntity product1 = new ProductEntity(productDTO.getProductName(), productDTO.getProductDescription(), productDTO.getProductPrice(), category);
            productRepository.save(product1);

            String inventory = productDTO.getProductName() + "," + productDTO.getProductQuantity();
            //Salvo la quantità in magazzino tramite inventory-service
            Boolean changeQuantity = webClientBuilder.build().post()
                    .uri("http://inventory-service/inventory", uriBuilder -> uriBuilder.queryParam("inventory", inventory).build())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (changeQuantity){
                return true;
            } else throw new RuntimeException("Errore connessione inventory");
        } else throw new IllegalArgumentException("Prodotto già presente con nome :  " + productDTO.getProductName());}

    @Override
    public boolean updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity product = productRepository.findById(id).get();
        CategoryEntity category = categoryRepository.findByName(productDTO.getProductCategory());
        product.setCategory(category);
        product.setDescription(productDTO.getProductDescription());
        product.setName(productDTO.getProductName());
        product.setPrice(productDTO.getProductPrice());

        //Salvo la quantità in magazzino tramite inventory-service
        String quantity = productDTO.getProductName() + "," + productDTO.getProductQuantity();
        Boolean changeQuantity = webClientBuilder.build().put()
                .uri("http://inventory-service/inventory/changequantity", uriBuilder -> uriBuilder.queryParam("quantity", quantity).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (changeQuantity){
            productRepository.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return true;
        } else throw new NullPointerException("Prodotto non trovato");
    }
}
