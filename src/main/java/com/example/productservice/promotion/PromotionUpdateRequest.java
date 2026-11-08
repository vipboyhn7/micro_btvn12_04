package com.example.productservice.promotion;

import jakarta.validation.constraints.NotNull;

public record PromotionUpdateRequest(
        @NotNull
        Long productId

) {
}
