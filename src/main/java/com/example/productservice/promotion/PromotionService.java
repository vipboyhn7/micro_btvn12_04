package com.example.productservice.promotion;

import com.example.productservice.config.RedisPubSubConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private final StringRedisTemplate redisTemplate;

    public PromotionService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publishPromotionUpdate(Long productId) {
        redisTemplate.convertAndSend(
                RedisPubSubConfig.PROMOTION_UPDATES_CHANNEL,
                productId.toString());
    }
}
