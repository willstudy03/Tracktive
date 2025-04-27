CREATE TABLE products (
    product_id VARCHAR(255) PRIMARY KEY,
    product_category VARCHAR(50) NOT NULL,
    product_brand VARCHAR(100) NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    product_description TEXT,
    recommended_price DECIMAL(15, 2),
    product_status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tires (
    id VARCHAR(255) PRIMARY KEY,
    tire_sku VARCHAR(100) UNIQUE NOT NULL,
    width INT NOT NULL,
    aspect_ratio INT NOT NULL,
    rim_diameter INT NOT NULL,
    construction_type VARCHAR(50) NOT NULL,
    load_index VARCHAR(10) NOT NULL,
    speed_rating VARCHAR(10) NOT NULL,
    tire_season VARCHAR(50) NOT NULL,
    tread_pattern VARCHAR(50) NOT NULL,
    tire_type VARCHAR(50) NOT NULL,
    run_flat BOOLEAN NOT NULL,
    CONSTRAINT fk_product_id FOREIGN KEY (id) REFERENCES products(product_id) ON DELETE CASCADE
);

CREATE TABLE supplier_products (
    supplier_product_id VARCHAR(255) PRIMARY KEY,
    supplier_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discount_percentage DECIMAL(5, 2),
    stock_quantity INT NOT NULL,
    product_status VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE retailer_inventory (
    retailer_inventory_id VARCHAR(255) PRIMARY KEY,
    retailer_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    stock_quantity INT NOT NULL,
    reorder_level INT NOT NULL,
    product_status VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);