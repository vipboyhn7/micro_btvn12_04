package com.example.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisPubSubConfig {
    public static final String PROMOTION_UPDATES_CHANNEL = "promotion-updates";

    @Bean
    ChannelTopic promotionUpdatesTopic() {
        return new ChannelTopic(PROMOTION_UPDATES_CHANNEL);
    }

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            ProductCacheInvalidationSubscriber subscriber,
            ChannelTopic promotionUpdatesTopic) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriber, promotionUpdatesTopic);
        return container;
    }
}
