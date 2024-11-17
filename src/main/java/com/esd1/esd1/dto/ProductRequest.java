package com.esd1.esd1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotNull(message = "Product name should be present")
        @NotEmpty(message = "Product name should be present")
        @NotBlank(message = "Product name should be present")
        @JsonProperty("product_name")
        String productName,

        @NotNull(message = "Product price should be present")
        @JsonProperty("product_price")
        float productPrice


) {
}
