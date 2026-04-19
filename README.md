# real-time-order-tracking

## Features
- Create an order
- View order status
- Update order status (simulate delivery flow)
- Real-time updates (later phase)

## Order Lifecycle
CREATED → PREPARING → SHIPPED → DELIVERED

## Services

### Order Service
- Create orders
- Update order status
- Publish events to Kafka

### Notification Service
- Consume Kafka events
- Send real-time updates

### Frontend
- Display order status
- Listen for updates

## Tech Stack

- Frontend: React
- Backend: Spring Boot
- Database: PostgreSQL
- Messaging: Kafka
- Containerization: Docker
- CI/CD: GitHub Actions

## API Design

POST /orders
GET /orders/{id}
PUT /orders/{id}/status
