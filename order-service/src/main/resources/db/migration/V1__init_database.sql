CREATE TABLE order_items
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    product_id BIGINT                NULL,
    order_id   BIGINT                NULL,
    quantity   INT                   NULL,
    price      DECIMAL               NULL,
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    status     VARCHAR(255)          NULL,
    order_date datetime              NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);