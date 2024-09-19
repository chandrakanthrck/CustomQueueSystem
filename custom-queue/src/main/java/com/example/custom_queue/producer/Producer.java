package com.example.custom_queue.producer;

import com.example.custom_queue.queue.CustomQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final CustomQueue<String> queue;
    public Producer(CustomQueue<String> queue){
        this.queue = queue;
    }

    //Produces a message every 5 seconds.
    @Scheduled(fixedRate = 5000)
    public void produce() throws InterruptedException{
        String message = "Message-" + System.currentTimeMillis();
        queue.enqueue(message);
        System.out.println("Produced: " + message);
    }
}
