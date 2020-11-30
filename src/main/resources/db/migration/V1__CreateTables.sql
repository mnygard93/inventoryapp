
CREATE TABLE IF NOT EXISTS SITE_USER (
                                         user_id SERIAL,
                                         username VARCHAR(50) UNIQUE NOT NULL,
                                         password VARCHAR(100) NOT NULL,
                                         email VARCHAR(50) NOT NULL,
                                         active bool,
                                         roles VARCHAR(50) NOT NULL,
                                         PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS PRODUCT (
                                       id SERIAL,
                                       user_id INTEGER,
                                       description VARCHAR(50) NOT NULL,
                                       expiration DATE,
                                       amount int NOT NULL,
                                       location VARCHAR(50) NOT NULL,
                                       PRIMARY KEY (id),
                                       FOREIGN KEY (user_id) REFERENCES SITE_USER(user_id)

);