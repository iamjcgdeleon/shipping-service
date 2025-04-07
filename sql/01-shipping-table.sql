-- Create the schema
CREATE SCHEMA IF NOT EXISTS shipment_schema;
USE shipment_schema;

-- Create the shipments table inside shipment_schema
CREATE TABLE shipment_schema.shipments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    tracking_number VARCHAR(255) NOT NULL,
    address  VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_date DATE NOT NULL,
    shipment_date DATE,
    remarks  VARCHAR(255)
);