package com.github.chandrakanthrck.custom_queue.persistence;

import com.github.chandrakanthrck.custom_queue.model.Message;

public interface QueuePersistence {
    void enqueue(Message message);
    Message dequeue();
    Message peek();
    boolean isEmpty();
    int size();
}
