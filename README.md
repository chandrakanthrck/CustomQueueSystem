# CustomQueueSystem

## Overview

`CustomQueueSystem` is a Spring Boot application that provides custom implementations of queue systems, including a message queue. This system includes functionality for a simple queue, message queuing, and custom producer-consumer interactions.

## Features

- **Custom Queue**: A basic queue implementation with standard operations.
- **Message Queue**: Extends the custom queue to handle messages with timestamps.
- **Producer-Consumer**: Endpoints to simulate producer and consumer interactions with the queue.

## Project Structure

- **`src/main/java/com/example/custom_queue`**:
  - **`controller`**: Contains REST controllers for interacting with the queue.
  - **`consumer`**: Contains the producer classes responsible for producing items to the queue.
  - **`producer`**: Contains the consumer classes responsible for consuming items from the queue.
  - **`queue`**: Contains queue implementations (`CustomQueue`, `MessageQueue`).
  - **`model`**: Contains the message model class (`Message`).

## Custom Queue Implementation

### `CustomQueue<T>`

- **Description**: A generic queue implementation using a linked list.
- **Methods**:
  - `enqueue(T data)`: Adds an item to the queue.
  - `dequeue()`: Removes and returns the item at the front of the queue.
  - `peek()`: Returns the item at the front of the queue without removing it.
  - `isEmpty()`: Checks if the queue is empty.
  - `size()`: Returns the number of items in the queue.

### `MessageQueue<T>`

- **Description**: A message queue that extends `CustomQueue` to handle messages.
- **Methods**:
  - `enqueue(T content)`: Adds a message to the queue with a timestamp.
  - `dequeue()`: Removes and returns the message at the front of the queue.
  - `peek()`: Returns the message at the front of the queue without removing it.
  - `isEmpty()`: Checks if the queue is empty.
  - `size()`: Returns the number of messages in the queue.

### `Message<T>`

- **Description**: Represents a message with content and timestamp.
- **Fields**:
  - `content`: The content of the message.
  - `timestamp`: The timestamp when the message was created.

## REST API Endpoints

### QueueController

- **`POST /queue/enqueue/{item}`**: Enqueue a new item.
- **`DELETE /queue/dequeue`**: Dequeue an item.
- **`GET /queue/peek`**: Peek at the front item.
- **`GET /queue/size`**: Get the size of the queue.
- **`GET /queue/isEmpty`**: Check if the queue is empty.
- **`POST /queue/produce`**: Trigger the producer to add an item.
- **`DELETE /queue/consume`**: Trigger the consumer to remove an item.

## Getting Started

1. **Clone the Repository**:

   ```sh
   git clone git@github.com:chandrakanthrck/CustomQueueSystem.git
   ```

2. **Navigate to the Project Directory**:

   ```sh
   cd custom-queue
   ```

3. **Build and Run the Application**:

   ```sh
   ./mvnw spring-boot:run
   ```

4. **Access the Application**:

   Open your browser and navigate to `http://localhost:8080`.

## Contributing

Feel free to submit pull requests or open issues if you encounter any bugs or have suggestions for improvement.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Spring Boot for the framework.
- Custom implementations for learning and experimentation.