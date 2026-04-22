CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP,
    user_type VARCHAR(50) NOT NULL,
    user_address_id INTEGER UNIQUE NOT NULL,

    FOREIGN KEY (user_address_id) REFERENCES user_address(id) ON DELETE CASCADE
);

