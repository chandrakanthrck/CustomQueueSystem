package com.example.custom_queue.queue;

import java.util.NoSuchElementException;

//front: Points to the first element of the queue
//rear: Points to the last element of the queue
//size: Tracks the number of elements in the queue
public class CustomQueue<T> {
    //represents a single node in the queue's linked list
    private static class Node<T>{
        private T data;
        private Node<T> next;
        public Node(T data){
            this.data = data;
            this. next = null;
        }
    }
    private Node<T> front;
    private Node<T> rear;
    private int size;
    public CustomQueue(){
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    //Adds an element to the rear of the queue. If the queue is empty, front and rear both point to the new node
    public void enqueue(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            front = newNode;
        }else{
            rear.next = newNode;
        }
        rear = newNode;
        size++;
        System.out.println(data + " enqueued to the queue.");
    }

    //Removes the front element from the queue and adjusts the front pointer.
    //If the queue is empty after dequeuing, the rear is also set to null.
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        T data = front.data;
        front = front.next;
        size--;
        if(isEmpty()){
            // If the queue is now empty, set rear to null as well
            rear = null;
        }
        System.out.println(data + " dequeued from the queue.");
        return data; // return the dequeued data
    }

    //Returns the front element without removing it.
    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty. Cannot peek.");
        }
        //return the data at the front
        return front.data;
    }

    //Returns true if the queue is empty, otherwise false.
    public boolean isEmpty(){
        return size == 0;
    }

    //Returns the number of elements in the queue.
    public int size(){
        return size;
    }
}
