CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    username varchar(200) NOT NULL UNIQUE,
    password varchar(200) NOT NULL,
    role varchar(100) NOT NULL
);