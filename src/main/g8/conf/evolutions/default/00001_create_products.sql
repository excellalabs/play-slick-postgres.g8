# products schema

# --- !Ups

CREATE TABLE products (
    id serial PRIMARY KEY,
    sku varchar(31) UNIQUE NOT NULL,
    name varchar(127) NOT NULL,
    description varchar(511) NOT NULL
);

# --- !Downs

DROP TABLE products;
