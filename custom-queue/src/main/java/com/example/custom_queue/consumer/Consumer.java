package com.example.custom_queue.consumer;

import com.example.custom_queue.queue.CustomQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final CustomQueue<String> queue;

    public Consumer(CustomQueue<String> queue) {
        this.queue = queue;
    }

    // Consumes a message every 7 seconds
    @Scheduled(fixedRate = 7000)
    public void consume() throws InterruptedException{
        if(!queue.isEmpty()){
            String message = queue.dequeue();
            System.out.println("Consumed: " + message);
        }else{
            System.out.println("Queue is empty, no message to consume.");
        }
    }
}
