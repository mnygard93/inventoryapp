CREATE TABLE IF NOT EXISTS LOCATION(
    location_id SERIAL,
    description VARCHAR(50) NOT NULL,
    PRIMARY KEY (location_id)
);

CREATE TABLE IF NOT EXISTS PRODUCT (
    id SERIAL,
    description VARCHAR(50) NOT NULL,
    expiration DATE,
    amount int NOT NULL,
    location int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (location) REFERENCES LOCATION(location_id)

);