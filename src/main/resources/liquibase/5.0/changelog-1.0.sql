CREATE TABLE Users
(
    user_id  SERIAL PRIMARY KEY,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255)
);
