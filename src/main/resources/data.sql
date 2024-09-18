CREATE TABLE IF NOT EXISTS payments (
    id uuid PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

/*DELETE FROM payments;

INSERT INTO payments (id, amount, currency, payment_method, description, status, created_at)
VALUES
    ('1f2e3d4c-5b6a-7e8f-9d0a-b1c2d3e4f5a6', 150.75, 'USD', 'CREDIT_CARD', 'Payment for subscription', 'PENDING', '2024-09-16 10:00:00'),
    ('2f3e4d5c-6b7a-8e9f-0d1a-b2c3d4e5f6a7', 200.00, 'EUR', 'PAYPAL', 'Payment for service', 'COMPLETED', '2024-09-15 11:00:00'),
    ('3f4e5d6c-7b8a-9e0f-1d2a-c3d4e5f6a7b8', 100.00, 'BRL', 'DEBIT_CARD', 'Payment for product', 'FAILED', '2024-09-14 12:00:00');*/

