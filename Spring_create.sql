CREATE TABLE Type
(
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Hardware
(
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    typeId INT NOT NULL,
    stock INT NOT NULL,
    FOREIGN KEY (typeId) REFERENCES Type(id)
);