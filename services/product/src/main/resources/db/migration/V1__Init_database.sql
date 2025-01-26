CREATE TABLE IF NOT EXISTS category
(
    id UUID NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product(
    id UUID NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price MONEY ,
    category_id UUID CONSTRAINT FK1_CONSTRAINT REFERENCES category
);
