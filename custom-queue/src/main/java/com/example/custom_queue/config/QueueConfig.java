package com.example.custom_queue.config;

import com.example.custom_queue.queue.CustomQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public CustomQueue<String> customQueue() {
        return new CustomQueue<>();
    }
}
