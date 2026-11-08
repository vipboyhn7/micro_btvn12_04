package com.example.productservice.promotion;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/products/{productId}/publish")
    public ResponseEntity<Void> publishPromotionUpdate(@PathVariable Long productId) {
        promotionService.publishPromotionUpdate(productId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publishPromotionUpdate(
            @Valid @RequestBody PromotionUpdateRequest request) {
        promotionService.publishPromotionUpdate(request.productId());
        return ResponseEntity.accepted().build();
    }
}
