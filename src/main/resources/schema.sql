CREATE TABLE IF NOT EXISTS injections (
    id UUID PRIMARY KEY,
    url VARCHAR(255),
    ip_address VARCHAR(255)
);
