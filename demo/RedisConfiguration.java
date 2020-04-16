package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@SuppressWarnings("ALL")
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, HotelData> redisTemplate(){
        final RedisTemplate<String , HotelData> redisTemplate = new RedisTemplate<String, HotelData>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer( new GenericToStringSerializer<HotelData>(HotelData.class));
        return redisTemplate;
    }

}
