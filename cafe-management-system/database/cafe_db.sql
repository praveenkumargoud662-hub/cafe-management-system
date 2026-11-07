CREATE DATABASE IF NOT EXISTS cafe_db;
USE cafe_db;
CREATE TABLE IF NOT EXISTS products (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 category VARCHAR(60) NOT NULL,
 price DECIMAL(10,2) NOT NULL,
 available BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS orders (
 id INT PRIMARY KEY AUTO_INCREMENT,
 product_id INT NOT NULL,
 product_name VARCHAR(100) NOT NULL,
 quantity INT NOT NULL,
 unit_price DECIMAL(10,2) NOT NULL,
 total DECIMAL(10,2) NOT NULL,
 order_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES products(id)
);
INSERT INTO products(name,category,price) VALUES
('Espresso','Coffee',90.00),('Cappuccino','Coffee',140.00),
('Cold Coffee','Coffee',160.00),('Masala Tea','Tea',40.00),
('Green Tea','Tea',60.00),('Veg Sandwich','Snacks',110.00),
('French Fries','Snacks',100.00),('Chocolate Brownie','Dessert',120.00);
