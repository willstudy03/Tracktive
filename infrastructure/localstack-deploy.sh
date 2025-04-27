#!/bin/bash
set -e

echo "Starting deployment to LocalStack..."

# Delete existing CloudFormation stack (if any)
echo "Checking and deleting existing stack..."
aws --endpoint-url=http://localhost:4566 cloudformation delete-stack \
    --stack-name tracktive-stack || true

# Wait for stack deletion to complete
echo "Waiting for stack deletion (if necessary)..."
aws --endpoint-url=http://localhost:4566 cloudformation wait stack-delete-complete \
    --stack-name tracktive-stack || true

# Delete the S3 Bucket if it exists
echo "Removing existing S3 bucket..."
aws --endpoint-url=http://localhost:4566 s3 rb s3://cf-templates --force || true

# Create a new S3 Bucket in LocalStack
echo "Creating S3 bucket for templates..."
aws --endpoint-url=http://localhost:4566 s3 mb s3://cf-templates

# Upload the CloudFormation Template to S3
echo "Uploading template to S3..."
aws --endpoint-url=http://localhost:4566 s3 cp ./cdk.out/localstack.template.json s3://cf-templates/

# Deploy CloudFormation stack from S3 URL
echo "Deploying CloudFormation stack..."
aws --endpoint-url=http://localhost:4566 cloudformation create-stack \
    --stack-name tracktive-stack \
    --template-url http://cf-templates.s3.localhost.localstack.cloud:4566/localstack.template.json \
    --capabilities CAPABILITY_IAM CAPABILITY_NAMED_IAM

echo "Waiting for stack creation to complete..."
aws --endpoint-url=http://localhost:4566 cloudformation wait stack-create-complete \
    --stack-name tracktive-stack

echo "Stack deployment complete!"

# Retrieve Load Balancer DNS name (if applicable)
aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text

echo "Deployment process finished."

