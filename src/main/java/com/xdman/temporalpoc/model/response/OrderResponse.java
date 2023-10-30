package com.xdman.temporalpoc.model.response;

import com.xdman.temporalpoc.domain.OrderStatus;
import com.xdman.temporalpoc.model.response.parameter.ItemResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private List<ItemResponseDTO> items;
    private String email;
    private String platForm;
    private LocalDateTime updatedAt;
    private OrderStatus status;

    public OrderResponse() {}
    public OrderResponse(Long id, List<ItemResponseDTO> items, String email, String platForm, LocalDateTime updatedAt, OrderStatus status) {
        this.id = id;
        this.items = items;
        this.email = email;
        this.platForm = platForm;
        this.updatedAt = updatedAt;
        this.status = status;
    }


    public OrderResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}