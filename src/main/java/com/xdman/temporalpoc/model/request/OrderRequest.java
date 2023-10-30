package com.xdman.temporalpoc.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xdman.temporalpoc.model.request.parameter.ItemRequestDTO;

import java.util.List;

public record OrderRequest(
        @JsonProperty(value = "items")
        List<ItemRequestDTO> purchaseItems,
        String email,
        String platForm
) {
}
