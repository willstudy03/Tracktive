CREATE TABLE orders (
    id VARCHAR(255) PRIMARY KEY,
    retailer_id VARCHAR(255) NOT NULL,
    supplier_id VARCHAR(255) NOT NULL,
    payment_id VARCHAR(255),
    delivery_task_id VARCHAR(255),
    total_amount DECIMAL(15,2) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    order_status VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cart_items (
    id VARCHAR(255) PRIMARY KEY,
    retailer_id VARCHAR(255) NOT NULL,
    supplier_product_id VARCHAR(255) NOT NULL,
    supplier_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price_snapshot DECIMAL(15,2) NOT NULL,
    discount_snapshot DECIMAL(15,2) DEFAULT 0,
    subtotal DECIMAL(15,2) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    id VARCHAR(255) PRIMARY KEY,
    order_id VARCHAR(255) NOT NULL,
    supplier_product_id VARCHAR(255) NOT NULL,
    supplier_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price_snapshot DECIMAL(15,2) NOT NULL,
    discount_snapshot DECIMAL(15,2) DEFAULT 0,
    subtotal DECIMAL(15,2) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);