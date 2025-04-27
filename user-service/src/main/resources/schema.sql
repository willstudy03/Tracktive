CREATE TABLE users (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    user_role VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE retailers (
    retailer_id VARCHAR(255) PRIMARY KEY,
    ssm_registration_number VARCHAR(100) UNIQUE NOT NULL,
    business_name VARCHAR(255) NOT NULL,
    business_address VARCHAR(500),
    bank_account VARCHAR(100),
    bank_name VARCHAR(100),
    pay_by_term_credit INT,
    FOREIGN KEY (retailer_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE suppliers (
    supplier_id VARCHAR(255) PRIMARY KEY,
    ssm_registration_number VARCHAR(100) UNIQUE NOT NULL,
    business_name VARCHAR(255) NOT NULL,
    business_address VARCHAR(500),
    bank_account VARCHAR(100),
    bank_name VARCHAR(100),
    FOREIGN KEY (supplier_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE couriers (
    courier_id VARCHAR(255) PRIMARY KEY,
    driving_license_number VARCHAR(100) UNIQUE NOT NULL,
    plate_number VARCHAR(50) NOT NULL,
    preferred_delivery_zone VARCHAR(255),
    bank_account VARCHAR(100),
    bank_name VARCHAR(100),
    FOREIGN KEY (courier_id) REFERENCES users(id) ON DELETE CASCADE
);