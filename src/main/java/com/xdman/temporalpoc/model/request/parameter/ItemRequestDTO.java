package com.xdman.temporalpoc.model.request.parameter;

public record ItemRequestDTO(
        String name,
        Integer quantity,
        Double unitPrice
) {
}
