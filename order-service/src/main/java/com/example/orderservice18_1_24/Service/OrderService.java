package com.example.orderservice18_1_24.Service;

import com.example.orderservice18_1_24.Dtos.OrderLineItemsDto;
import com.example.orderservice18_1_24.Dtos.OrderRequest;
import com.example.orderservice18_1_24.Model.Order;
import com.example.orderservice18_1_24.Model.OrderLineItems;
import com.example.orderservice18_1_24.OrderService18124Application;
import com.example.orderservice18_1_24.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsListDto()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        // Call inventory service and place order if product is in stock
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
