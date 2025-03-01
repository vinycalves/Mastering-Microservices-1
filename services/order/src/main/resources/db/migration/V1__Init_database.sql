CREATE TABLE IF NOT EXISTS customer_order
(
    id             UUID PRIMARY KEY,
    reference      VARCHAR(255),
    total_amount   DOUBLE PRECISION,
    payment_method VARCHAR(255),
    customer_id    UUID,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS order_line
(
    id         UUID PRIMARY KEY,
    quantity   DECIMAL(12, 2) NOT NULL,
    unit_price DECIMAL(19, 2),
    product_id UUID,
    order_id   UUID    NOT NULL,
    CONSTRAINT fk_order_order_line FOREIGN KEY (order_id) REFERENCES customer_order (id)
);