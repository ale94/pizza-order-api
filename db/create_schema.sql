-- Tabla customer
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    address VARCHAR(40) NOT NULL,
    email VARCHAR(30),
    last_name VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL
);
-- Tabla pizza
CREATE TABLE pizza (
    id BIGSERIAL PRIMARY KEY,
    available BOOLEAN NOT NULL,
    description VARCHAR(100),
    name VARCHAR(30) NOT NULL,
    price DOUBLE PRECISION NOT NULL
);
-- Crear los tipos ENUM
CREATE TYPE payment_method_enum AS ENUM ('credito', 'debito', 'efectivo', 'transferencia');
CREATE TYPE status_enum AS ENUM ('cancelado', 'entregado', 'pendiente');
-- Tabla orders
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    delivery_address VARCHAR(30) NOT NULL,
    observations VARCHAR(50),
    order_date TIMESTAMP(6) NOT NULL,
    payment_method payment_method_enum NOT NULL,
    status status_enum NOT NULL,
    customer_id BIGINT REFERENCES customer(id)
);
-- Tabla order_detail
CREATE TABLE order_detail (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP(6),
    quantity INTEGER,
    total DOUBLE PRECISION,
    order_id BIGINT REFERENCES orders(id),
    pizza_id BIGINT REFERENCES pizza(id)
);