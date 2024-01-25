package com.example.orderservice18_1_24.Service;

import com.example.orderservice18_1_24.Dtos.InventoryResponse;
import com.example.orderservice18_1_24.Dtos.OrderLineItemsDto;
import com.example.orderservice18_1_24.Dtos.OrderRequest;
import com.example.orderservice18_1_24.Model.Order;
import com.example.orderservice18_1_24.Model.OrderLineItems;
import com.example.orderservice18_1_24.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private WebClient webClient;
    public OrderService(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;

        this.webClient = webClient;
    }
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsListDto()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode).toList();

        // Call inventory service and place order if product is in stock
       InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
       if (allProductsInStock) {
           orderRepository.save(order);
       } else  {
           throw new IllegalArgumentException("Product is not in stock, please try again later");
       }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
