package com.colonb.websocket.config;

import com.colonb.websocket.dto.ChatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, ChatDTO> redisTemplate;

    public void publish(ChannelTopic topic, ChatDTO message) {
        System.out.println("redis publish");
        System.out.println("message : "+message.toString());
        System.out.println("topic : "+topic.getTopic());
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}