CREATE TABLE IF NOT EXISTS PRODUCT (
                                       id SERIAL,
                                       description VARCHAR(50) NOT NULL,
                                       expiration DATE,
                                       amount int NOT NULL,
                                       location VARCHAR(50) NOT NULL,
                                       PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS SITE_USER (
                                         user_id SERIAL,
                                         username VARCHAR(20) NOT NULL,
                                         password VARCHAR(50) NOT NULL,
                                         active bool,
                                         roles VARCHAR(50) NOT NULL,
                                         PRIMARY KEY(user_id)
);