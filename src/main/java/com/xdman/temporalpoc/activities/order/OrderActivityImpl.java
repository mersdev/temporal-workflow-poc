package com.xdman.temporalpoc.activities.order;

import com.xdman.temporalpoc.domain.Items;
import com.xdman.temporalpoc.domain.Order;
import com.xdman.temporalpoc.domain.OrderStatus;
import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.request.parameter.ItemRequestDTO;
import com.xdman.temporalpoc.model.response.OrderResponse;
import com.xdman.temporalpoc.model.response.parameter.ItemResponseDTO;
import com.xdman.temporalpoc.repository.OrderRepository;
import io.temporal.spring.boot.ActivityImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
public class OrderActivityImpl implements OrderActivity {
    private OrderResponse response;
    private OrderRepository orderRepository;

    public OrderActivityImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        List<Items> items = new ArrayList<>();
        for (ItemRequestDTO item: request.purchaseItems()) {
            items.add(new Items(item.name(), item.quantity(), item.unitPrice()));
        }
        Order order = new Order(items, request.email(), request.platForm(), OrderStatus.ACCEPTED);
        Order orderResponse = orderRepository.save(order);
        List<ItemResponseDTO> itemListResponse = new ArrayList<>();
        for (Items item: orderResponse.getItemList()) {
            itemListResponse.add(
                    new ItemResponseDTO(
                            item.getId(),
                            item.getItemName(),
                            item.getQuantity(),
                            item.getUnitPrice(),
                            item.getUnitPrice() * item.getQuantity()
                    ));
        }
        response = new OrderResponse(orderResponse.getId());
//        response = new OrderResponse( orderResponse.getId(),
//                itemListResponse,
//                orderResponse.getEmail(),
//                orderResponse.getPlatForm(),
//                orderResponse.getCreatedAt(),
//                orderResponse.getStatus());
        return response;
    }

    @Override
    public OrderResponse setOrderAccepted() {
        response.setStatus(OrderStatus.ACCEPTED);
        return response;
    }

    @Override
    public OrderResponse setOrderPickedUp() {
        response.setStatus(OrderStatus.PICKED_UP);
        return response;
    }

    @Override
    public OrderResponse setOrderDelivered() {
        response.setStatus(OrderStatus.DELIVERED);
        return response;
    }

}
