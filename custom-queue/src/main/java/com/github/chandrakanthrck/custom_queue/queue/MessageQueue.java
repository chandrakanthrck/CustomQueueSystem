package com.github.chandrakanthrck.custom_queue.queue;

import com.github.chandrakanthrck.custom_queue.model.Message;

public class MessageQueue<T> {
    private final CustomQueue<Message<T>> queue = new CustomQueue<>();

    // Adds a message to the queue
    public void enqueue(T content) {
        Message<T> message = new Message<>(content);
        queue.enqueue(message);
        System.out.println("Message added to the queue: " + content);
    }

    // Removes and returns the message at the front of the queue
    public Message<T> dequeue() {
        return queue.dequeue();
    }

    // Peeks at the front message without removing it
    public Message<T> peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}