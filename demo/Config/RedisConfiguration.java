package com.example.demo.Config;

import com.example.demo.Entities.hotelData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration

public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, hotelData> redisTemplate(){
        final RedisTemplate<String , hotelData> redisTemplate = new RedisTemplate<String,hotelData>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer( new GenericToStringSerializer<hotelData>(hotelData.class));
        return redisTemplate;
    }
}
