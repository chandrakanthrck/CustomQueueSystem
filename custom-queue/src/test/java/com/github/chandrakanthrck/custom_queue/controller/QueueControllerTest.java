package com.github.chandrakanthrck.custom_queue.controller;

import com.github.chandrakanthrck.custom_queue.queue.CustomQueue;
import com.github.chandrakanthrck.custom_queue.consumer.Consumer;
import com.github.chandrakanthrck.custom_queue.producer.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QueueController.class)
public class QueueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomQueue<String> queue;

    @MockBean
    private Producer producer;

    @MockBean
    private Consumer consumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testEnqueueAndCheckSizeDynamically() throws Exception {
        // Before enqueue, the queue size is 0
        when(queue.size()).thenReturn(0);
        mockMvc.perform(get("/queue/size")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Current queue size: 0"));

        // After enqueuing an item, mock the size to increase dynamically
        doAnswer(invocation -> {
            when(queue.size()).thenReturn(1);  // Enqueue an item
            return null;
        }).when(queue).enqueue(anyString());

        mockMvc.perform(post("/queue/enqueue/{item}", "testItem")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("testItem has been added to the queue."));

        // Verify that the queue size has increased after enqueueing
        mockMvc.perform(get("/queue/size")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Current queue size: 1"));
    }

    @Test
    public void testDequeueAndCheckEmptyStateDynamically() throws Exception {
        // Initially, the queue has 1 item
        when(queue.size()).thenReturn(1);
        when(queue.isEmpty()).thenReturn(false);

        // Mock dequeue behavior to remove the last item and make the queue empty
        doAnswer(invocation -> {
            when(queue.size()).thenReturn(0);  // After dequeue, the queue size is 0
            when(queue.isEmpty()).thenReturn(true);  // Queue becomes empty
            return "testItem";
        }).when(queue).dequeue();

        // Perform dequeue operation
        mockMvc.perform(delete("/queue/dequeue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("testItem has been removed from the queue."));

        // Check if the queue is empty after dequeueing the item
        mockMvc.perform(get("/queue/isEmpty")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Is the queue empty? true"));
    }

    @Test
    public void testPeekQueueDynamically() throws Exception {
        when(queue.peek()).thenThrow(new NoSuchElementException("Queue is empty"));

        mockMvc.perform(get("/queue/peek")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect 200 status
                .andExpect(content().string("Queue is empty."));  // Expect "Queue is empty." message
    }

}