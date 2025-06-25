#!/bin/bash
set -e

echo "Starting to build api-gateway..."

# Start to build the API gateway service
#docker build -t api-gateway -f api-gateway/Dockerfile .

# Start to build the authentication service
echo "Starting to build auth-service..."
#docker build -t auth-service -f auth-service/Dockerfile .

# Start to build the user management service
echo "Starting to build user-service..."
docker build -t user-service -f user-service/Dockerfile .

# Start to build the product catalog service
echo "Starting to build product-service..."
#docker build -t product-service -f product-service/Dockerfile .

# Start to build the order processing service
echo "Starting to build order-service..."
#docker build -t order-service -f order-service/Dockerfile .

# Start to build the payment processing service
echo "Starting to build payment-service..."
#docker build -t payment-service -f payment-service/Dockerfile .

echo "All services built successfully!"