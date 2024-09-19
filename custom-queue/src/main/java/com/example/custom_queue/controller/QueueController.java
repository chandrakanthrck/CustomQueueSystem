package com.example.custom_queue.controller;

import com.example.custom_queue.queue.CustomQueue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final CustomQueue<String> queue = new CustomQueue<>();

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
}