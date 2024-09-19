package com.example.custom_queue.model;

public class Message<T> {
    private final T content;
    private final long timestamp;

    public Message(T content) {
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public T getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}