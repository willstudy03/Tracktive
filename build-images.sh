#!/bin/bash
set -e

docker build -t api-gateway -f api-gateway/Dockerfile .

docker build -t auth-service -f auth-service/Dockerfile .

docker build -t user-service -f user-service/Dockerfile .

docker build -t product-service -f product-service/Dockerfile .

docker build -t order-service -f order-service/Dockerfile .

docker build -t payment-service -f payment-service/Dockerfile .