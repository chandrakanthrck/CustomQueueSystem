package com.example.custom_queue.controller;

import com.example.custom_queue.consumer.Consumer;
import com.example.custom_queue.producer.Producer;
import com.example.custom_queue.queue.CustomQueue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final CustomQueue<String> queue = new CustomQueue<>();
    private final Producer producer;
    private final Consumer consumer;
    public QueueController(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    // Endpoint to enqueue a new item
    @PostMapping("/enqueue/{item}")
    public String enqueue(@PathVariable String item) {
        queue.enqueue(item);
        return item + " has been added to the queue.";
    }

    // Endpoint to dequeue an item
    @DeleteMapping("/dequeue")
    public String dequeue() {
        try {
            String item = queue.dequeue();
            return item + " has been removed from the queue.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Endpoint to peek at the front item
    @GetMapping("/peek")
    public String peek() {
        try {
            return "Front of the queue: " + queue.peek();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Endpoint to check the size of the queue
    @GetMapping("/size")
    public String getSize() {
        return "Current queue size: " + queue.size();
    }

    // Endpoint to check if the queue is empty
    @GetMapping("/isEmpty")
    public String isEmpty() {
        return "Is the queue empty? " + queue.isEmpty();
    }
    // New endpoint to manually trigger producer to enqueue a message
    @PostMapping("/produce")
    public String produce() throws InterruptedException {
        producer.produce();
        return "Produced an item to the queue.";
    }
    // New endpoint to manually trigger consumer to dequeue a message
    @DeleteMapping("/consume")
    public String consume() throws InterruptedException {
        consumer.consume();
        return "Consumer has consumed an item from the queue.";
    }
}