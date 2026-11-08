package com.example.productservice.config;

import java.nio.charset.StandardCharsets;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCacheInvalidationSubscriber implements MessageListener {
    private final CacheManager cacheManager;

    public ProductCacheInvalidationSubscriber(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String productId = new String(message.getBody(), StandardCharsets.UTF_8);
        if (!productId.matches("\\d+")) {
            return;
        }

        var productCache = cacheManager.getCache("products");
        if (productCache != null) {
            productCache.evict(Long.valueOf(productId));
        }
    }
}
