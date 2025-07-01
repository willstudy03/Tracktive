# Tracktive
Final Year Project: A Unified Cloud Based System for Tire Retail Industry In Malaysia

## Overview
Tracktive is a microservices-based backend system built with Spring Boot that provides a comprehensive platform for tire retail management. The system includes multiple services for authentication, user management, product catalog, order processing, payment handling, and delivery tracking.

## Architecture
The system consists of the following microservices:
- **API Gateway** (Port 4004) - Routes requests to appropriate services
- **Auth Service** (Port 8081) - Authentication and authorization
- **User Service** (Port 8082) - User management
- **Product Service** (Port 8083) - Product catalog and inventory
- **Order Service** (Port 8084) - Order processing
- **Payment Service** (Port 8085) - Payment processing with Stripe integration
- **Delivery Service** - Delivery tracking (under development)

## Prerequisites
Before running the application locally, ensure you have the following installed:

### Required Software
1. **Java 21** - Required for Spring Boot 3.4.3
2. **Maven 3.6+** - For building the application
3. **Docker & Docker Compose** - For running infrastructure services
4. **MySQL 8.0+** - Database for each microservice
5. **Git** - For version control

### Required Accounts & CLI Tools
1. **Stripe Account** - For payment processing
   - Sign up at [https://stripe.com](https://stripe.com)
   - Get your API keys from the dashboard
2. **AWS Account** (optional) - For LocalStack configuration
3. **Stripe CLI** - For webhook testing
4. **AWS CLI** - For LocalStack interactions

## Local Development Setup

### 1. Environment Setup

#### Install Required CLI Tools

**Stripe CLI:**
```bash
# Windows (using Chocolatey)
choco install stripe-cli

# macOS (using Homebrew)
brew install stripe/stripe-cli/stripe

# Linux
wget https://github.com/stripe/stripe-cli/releases/latest/download/stripe_*_linux_x86_64.tar.gz
tar -xzf stripe_*_linux_x86_64.tar.gz
sudo mv stripe /usr/local/bin/
```

**AWS CLI:**
```bash
# Windows (using MSI installer)
# Download from: https://aws.amazon.com/cli/

# macOS (using Homebrew)
brew install awscli

# Linux
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

### 2. Database Setup

Create MySQL databases for each service:

```sql
-- Connect to MySQL as root
mysql -u root -p

-- Create databases
CREATE DATABASE auth_service_db;
CREATE DATABASE user_service_db;
CREATE DATABASE product_service_db;
CREATE DATABASE order_service_db;
CREATE DATABASE payment_service_db;

-- Create database user (optional)
CREATE USER 'tracktive'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON auth_service_db.* TO 'tracktive'@'localhost';
GRANT ALL PRIVILEGES ON user_service_db.* TO 'tracktive'@'localhost';
GRANT ALL PRIVILEGES ON product_service_db.* TO 'tracktive'@'localhost';
GRANT ALL PRIVILEGES ON order_service_db.* TO 'tracktive'@'localhost';
GRANT ALL PRIVILEGES ON payment_service_db.* TO 'tracktive'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Kafka Setup (Bitnami)

#### Option A: Single Container Setup (Recommended)

Create a Docker network and run Kafka in KRaft mode (no Zookeeper required):

```bash
# Create Docker network
docker network create tracktive

# Pull and run Bitnami Kafka
docker pull bitnami/kafka
docker run -d --name kafka --network tracktive \
  -p 9092:9092 \
  -p 9094:9094 \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,EXTERNAL://localhost:9094 \
  -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka:9093 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,EXTERNAL:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093,EXTERNAL://:9094 \
  -e KAFKA_CFG_NODE_ID=0 \
  -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
  bitnami/kafka
```

#### Create Required Kafka Topics

After Kafka is running, create the required topics:

```bash
# Create all required topics
docker exec -it kafka kafka-topics.sh --create --topic payment-cancellation-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-generated --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-results --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-deduction-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-deduction-results --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-restore-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic user-created --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic user-deleted --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

#### Verify Topics Creation

```bash
# List all topics to verify they were created
docker exec -it kafka kafka-topics.sh --list --bootstrap-server localhost:9092
```

#### Option B: Docker Compose Setup (Alternative)

If you prefer Docker Compose, create a `docker-compose.yml` file for Kafka:

```yaml
version: '3.8'
services:
  zookeeper:
    image: bitnami/zookeeper:latest
    ports:
      - "2181:2181"
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
    volumes:
      - zookeeper_data:/bitnami

  kafka:
    image: bitnami/kafka:latest
    ports:
      - "9092:9092"
      - "9094:9094"
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
      - KAFKA_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9094
      - KAFKA_CFG_ADVERTISED_LISTENERS=CLIENT://kafka:9092,EXTERNAL://localhost:9094
      - KAFKA_CFG_INTER_BROKER_LISTENER_NAME=CLIENT
    depends_on:
      - zookeeper
    volumes:
      - kafka_data:/bitnami

volumes:
  zookeeper_data:
  kafka_data:
```

Start Kafka:
```bash
docker-compose up -d
```

#### Kafka Management Commands

```bash
# Check if Kafka container is running
docker ps | grep kafka

# Start existing Kafka container
docker start kafka

# Stop Kafka container
docker stop kafka

# Remove Kafka container (to recreate)
docker rm kafka

# View Kafka logs
docker logs kafka

# Access Kafka container shell
docker exec -it kafka bash

# List all topics
docker exec -it kafka kafka-topics.sh --list --bootstrap-server localhost:9092

# Delete a topic (if needed)
docker exec -it kafka kafka-topics.sh --delete --topic TOPIC_NAME --bootstrap-server localhost:9092

# Test Kafka producer (for debugging)
docker exec -it kafka kafka-console-producer.sh --topic user-created --bootstrap-server localhost:9092

# Test Kafka consumer (for debugging)
docker exec -it kafka kafka-console-consumer.sh --topic user-created --from-beginning --bootstrap-server localhost:9092
```

### 4. LocalStack Setup

Install and start LocalStack for AWS services simulation:

```bash
# Install LocalStack
pip install localstack

# Start LocalStack
localstack start
```

Configure AWS CLI for LocalStack:
```bash
aws configure set aws_access_key_id test
aws configure set aws_secret_access_key test
aws configure set region us-east-1
aws configure set output json
```

### 5. Stripe Configuration

#### Configure Stripe CLI
```bash
# Login to Stripe
stripe login

# Listen for webhooks (run in separate terminal)
stripe listen --forward-to localhost:8085/api/stripe/webhook
```

#### Update Stripe Keys
Update the following files with your Stripe keys:
- `payment-service/src/main/resources/application-dev.properties`

Replace the following properties with your own keys:
```properties
# Replace with your Stripe keys
stripe.api.secret.key=sk_test_YOUR_SECRET_KEY
stripe.api.public.key=pk_test_YOUR_PUBLIC_KEY
stripe.webhook.secret=whsec_YOUR_WEBHOOK_SECRET
```

### 6. LocalStack Configuration

Deploy infrastructure to LocalStack:
```bash
cd infrastructure
./localstack-deploy.sh
```

### 7. Application Configuration

Update database credentials in all `application-dev.properties` files if you changed them:

```properties
# Update in all service application-dev.properties files
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

## Running the Application

### Option 1: Run with Maven (Recommended for Development)

1. **Start Infrastructure Services:**
```bash
# Create Docker network (if not already created)
docker network create tracktive

# Start Kafka (if not already running)
docker run -d --name kafka --network tracktive \
  -p 9092:9092 \
  -p 9094:9094 \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,EXTERNAL://localhost:9094 \
  -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka:9093 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,EXTERNAL:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093,EXTERNAL://:9094 \
  -e KAFKA_CFG_NODE_ID=0 \
  -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
  bitnami/kafka

# Create Kafka topics (if not already created)
docker exec -it kafka kafka-topics.sh --create --topic payment-cancellation-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-generated --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic payment-results --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-deduction-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-deduction-results --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic stock-restore-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic user-created --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic user-deleted --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

# Start LocalStack
localstack start

# Start Stripe webhook listener (in separate terminal)
stripe listen --forward-to localhost:8085/api/stripe/webhook
```

2. **Build the Application:**
```bash
# From project root
mvn clean install
```

3. **Run Services in Order:**

```bash
# Terminal 1 - Auth Service
cd auth-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 2 - User Service
cd user-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 3 - Product Service
cd product-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 4 - Order Service
cd order-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 5 - Payment Service
cd payment-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Terminal 6 - API Gateway
cd api-gateway
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Option 2: Run with Docker

1. **Build Docker Images:**
```bash
# Make build script executable
chmod +x build-images.sh

# Build all service images
./build-images.sh
```

2. **Start Services:**
```bash
# Start infrastructure and services
docker-compose up -d
```

## Service Endpoints

Once all services are running, you can access:

- **API Gateway:** http://localhost:4004
- **Auth Service:** http://localhost:8081
- **User Service:** http://localhost:8082
- **Product Service:** http://localhost:8083
- **Order Service:** http://localhost:8084
- **Payment Service:** http://localhost:8085

## API Documentation

The API follows RESTful conventions. Key endpoints include:

- `POST /api/authentication/login` - User login
- `POST /api/authentication/register` - User registration
- `GET /api/products-management/products` - Get products
- `POST /api/cart/add` - Add item to cart
- `POST /api/order-placement/place` - Place order
- `POST /api/check-out/create-payment-intent` - Create payment

## Troubleshooting

### Common Issues

1. **Database Connection Errors:**
   - Ensure MySQL is running and databases are created
   - Check credentials in application-dev.properties files

2. **Kafka Connection Errors:**
   - Ensure Kafka is running: `docker ps | grep kafka`
   - Check Kafka logs: `docker logs kafka`
   - Restart Kafka: `docker restart kafka`
   - Check port 9094 is not blocked by firewall
   - Verify topics exist: `docker exec -it kafka kafka-topics.sh --list --bootstrap-server localhost:9092`

3. **LocalStack Issues:**
   - Restart LocalStack: `localstack restart`
   - Check if port 4566 is available

4. **Stripe Webhook Issues:**
   - Ensure Stripe CLI is running and listening
   - Check webhook secret matches in application properties

5. **Port Conflicts:**
   - Ensure no other applications are using ports 8081-8085, 4004
   - Stop conflicting services or change ports in application properties

### Logs

Check service logs for errors:
```bash
# For Maven runs, logs appear in the terminal
# For Docker runs:
docker-compose logs [service-name]
```

## Development Notes

- Use `dev` profile for local development
- Database schemas are auto-created on startup
- Hot reload is enabled for Maven runs
- API Gateway handles CORS for frontend integration
- All services communicate via Kafka for event-driven architecture

### Kafka Topics Usage
- `payment-cancellation-requests` - Payment cancellation requests
- `payment-generated` - Notifications when payments are generated
- `payment-requests` - Payment processing requests
- `payment-results` - Payment processing results
- `stock-deduction-requests` - Stock reduction requests from orders
- `stock-deduction-results` - Results of stock deduction operations
- `stock-restore-requests` - Stock restoration requests (order cancellations)
- `user-created` - User registration events
- `user-deleted` - User deletion events

## Production Deployment

For production deployment:
1. Use `prod` profile
2. Update all configuration with production values
3. Use proper database credentials and AWS configuration
4. Enable SSL/TLS for all services
5. Set up proper monitoring and logging
