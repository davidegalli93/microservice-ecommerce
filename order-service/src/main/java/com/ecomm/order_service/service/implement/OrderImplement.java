package com.ecomm.order_service.service.implement;

import com.ecomm.order_service.model.dto.InventoryResponse;
import com.ecomm.order_service.model.dto.OrderDTO;
import com.ecomm.order_service.model.dto.OrderLineItemDTO;
import com.ecomm.order_service.model.dto.OrderRequest;
import com.ecomm.order_service.model.entity.OrderEntity;
import com.ecomm.order_service.model.entity.OrderLineItem;
import com.ecomm.order_service.repository.OrderRepository;
import com.ecomm.order_service.service.OrderService;
import com.ecomm.order_service.utils.converter.OrderConverter;
import com.ecomm.order_service.utils.converter.OrderLineItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderImplement implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderLineItemConverter orderLineItemConverter;

    @Autowired
    WebClient webClient;


    @Override
    public OrderDTO findByID(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            OrderEntity order = orderRepository.findById(id).get();
            return orderConverter.toDto(order);
        } else throw new NullPointerException("Ordine non esistente!");
    }

    @Override
    public List<OrderDTO> findByUser(Long id) {
        return List.of();
    }

    @Override
    public boolean create(OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        order.setDate(LocalDate.now());
        //_______________________________
        order.setId_user(null);
        //_______________________________

        Double total = orderRequest.getOrderLineItems()
                .stream()
                .mapToDouble(OrderLineItemDTO::getPrice)
                .sum();
        order.setTotal(total);

        List<OrderLineItem> items = orderRequest.getOrderLineItems()
                .stream()
                .map(orderLineItemDTO -> orderLineItemConverter.toEntity(orderLineItemDTO))
                .toList();
        order.setOrderLineItemList(items);

        //Prendo la lista degli skucode per poi verificarli uno ad uno
        List <String> skuCodes = orderRequest.getOrderLineItems().stream().map(OrderLineItemDTO::getSkuCode).toList();

        //Chiamo inventory service per sapere se i prodotti sono a magazzino, esegue chiamata asincrona
        InventoryResponse [] inventoryResponsesArray = webClient.get()
                //definiscono la uri del servizio e gli passo come parametro la lista degli skucode
                .uri("http://localhost:8080/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        boolean allProductIsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

        if(allProductIsInStock){
            orderRepository.save(order);
            return true;
        } else throw new IllegalArgumentException("Prodotto/i non in stock, riprovare");
    }

    @Override
    public boolean delete(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return true;
        } else throw new NullPointerException("Ordine non esistente");
    }
}
