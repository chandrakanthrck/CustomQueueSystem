# CustomQueueSystem

## Overview

`CustomQueueSystem` is a Spring Boot application that provides custom implementations of queue systems, including message queuing. This system includes functionality for a simple queue, message queuing, and custom producer-consumer interactions, with optional persistence using SQL or NoSQL databases.

## Features

- **Custom Queue**: A basic queue implementation with standard operations like enqueue, dequeue, and peek.
- **Message Queue**: Extends the custom queue to handle timestamped messages.
- **Producer-Consumer**: REST endpoints to simulate producer and consumer interactions with the queue.
- **Hybrid Persistence**: Optionally store the queue in either a SQL database (MySQL) or a NoSQL database (MongoDB).

## Project Structure

- **`src/main/java/com/github/chandrakanthrck/custom_queue`**:
  - **`controller`**: Handles REST API requests for queue operations.
  - **`consumer`**: Contains the consumer classes responsible for consuming items from the queue.
  - **`producer`**: Contains the producer classes responsible for adding items to the queue.
  - **`queue`**: Contains queue implementations like `CustomQueue` and `MessageQueue`.
  - **`model`**: Contains the data model classes, such as `Message`.
  - **`persistence`**: Contains persistence logic for both SQL and NoSQL, with interfaces and implementations for different storage types.

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

- **Description**: A message queue that extends `CustomQueue` to handle messages with timestamps.
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

## Persistence

This system allows for hybrid persistence, meaning the queue can be stored either in a SQL or NoSQL database depending on the active profile:

- **SQL Persistence (MySQL)**: The system can store messages in a MySQL database. This is active when the `sql` profile is enabled.
- **NoSQL Persistence (MongoDB)**: For applications requiring NoSQL storage, MongoDB is supported and is activated by enabling the `nosql` profile.

### Switching Between SQL and NoSQL

- By default, the application uses **MySQL**. To use **MongoDB**, update the profile to `nosql`.
  
In `application.properties`:
  
```properties
spring.profiles.active=sql  # For MySQL
spring.profiles.active=nosql  # For MongoDB
```

### Persistence Classes

- **`QueuePersistence` Interface**: Defines the contract for all persistence operations (e.g., `enqueue`, `dequeue`, etc.).
- **`NoSqlQueuePersistence`**: Implements persistence using MongoDB.
- **`SqlQueuePersistence`**: Implements persistence using MySQL.

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

### Prerequisites

Ensure that you have the following installed:
- **Java 17** or later
- **Maven**
- **MySQL** (if using SQL persistence)
- **MongoDB** (if using NoSQL persistence)

### Clone the Repository

```bash
git clone git@github.com:chandrakanthrck/CustomQueueSystem.git
```

### Navigate to the Project Directory

```bash
cd custom-queue
```

### Build and Run the Application

```bash
./mvnw spring-boot:run
```

### Access the Application

Open your browser and navigate to `http://localhost:8080`.

## Contributing

Feel free to submit pull requests or open issues if you encounter any bugs or have suggestions for improvement.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.