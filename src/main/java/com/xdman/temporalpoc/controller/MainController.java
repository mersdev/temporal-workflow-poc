package com.xdman.temporalpoc.controller;

import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.response.OrderResponse;
import com.xdman.temporalpoc.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final OrderService orderService;

    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/start")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){
        OrderResponse response = orderService.startOrderWorkFlow(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/accept")
    public String acceptOrder(){
        orderService.sendOrderAccepted();
        return "Order Accepted.";
    }
    @GetMapping("/pick")
    public String pickOrder(){
        orderService.sendOrderPickedUp();
        return "Order Picked Up.";
    }
    @GetMapping("/delivery")
    public String deliveryOrder(){
        orderService.sendOrderReceived();
        return "Order Delivered.";
    }
    @GetMapping("/order")
    public ResponseEntity<OrderResponse> getOrder(){
        OrderResponse response = orderService.getOrderResponse();
        return ResponseEntity.ok(response);
    }
}
