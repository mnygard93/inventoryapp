
CREATE TABLE IF NOT EXISTS PRODUCT (
    id SERIAL,
    description VARCHAR(50) NOT NULL,
    expiration DATE,
    amount int NOT NULL,
    location VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)

);