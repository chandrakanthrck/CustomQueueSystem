package com.example.custom_queue.controller;

import com.example.custom_queue.consumer.Consumer;
import com.example.custom_queue.producer.Producer;
import com.example.custom_queue.queue.CustomQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private static final Logger logger = LoggerFactory.getLogger(QueueController.class);
    private final CustomQueue<String> queue = new CustomQueue<>();
    private final Producer producer;
    private final Consumer consumer;

    public QueueController(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @PostMapping("/enqueue/{item}")
    public ResponseEntity<String> enqueue(@PathVariable String item) {
        if (item == null || item.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Item cannot be null or empty.");
        }
        queue.enqueue(item);
        logger.info("Enqueued item: {}", item);
        return ResponseEntity.ok(item + " has been added to the queue.");
    }

    @DeleteMapping("/dequeue")
    public ResponseEntity<String> dequeue() {
        try {
            String item = queue.dequeue();
            logger.info("Dequeued item: {}", item);
            return ResponseEntity.ok(item + " has been removed from the queue.");
        } catch (Exception e) {
            logger.error("Error during dequeue: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/peek")
    public ResponseEntity<String> peek() {
        try {
            return ResponseEntity.ok("Front of the queue: " + queue.peek());
        } catch (NoSuchElementException e) {
            logger.error("Error during peek: Queue is empty.");
            return ResponseEntity.ok("Queue is empty.");
        } catch (Exception e) {
            logger.error("Error during peek: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/size")
    public ResponseEntity<String> getSize() {
        return ResponseEntity.ok("Current queue size: " + queue.size());
    }

    @GetMapping("/isEmpty")
    public ResponseEntity<String> isEmpty() {
        return ResponseEntity.ok("Is the queue empty? " + queue.isEmpty());
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produce() throws InterruptedException {
        producer.produce();
        logger.info("Produced an item to the queue.");
        return ResponseEntity.ok("Produced an item to the queue.");
    }

    @DeleteMapping("/consume")
    public ResponseEntity<String> consume() throws InterruptedException {
        consumer.consume();
        logger.info("Consumer has consumed an item from the queue.");
        return ResponseEntity.ok("Consumer has consumed an item from the queue.");
    }
}