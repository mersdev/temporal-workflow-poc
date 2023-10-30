package com.xdman.temporalpoc.model.response.parameter;

public record ItemResponseDTO(
        Long id,
        String name,
        Integer quantity,
        Double unitPrice,
        Double totalPrice

) {
}
