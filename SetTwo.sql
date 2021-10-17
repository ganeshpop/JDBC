CREATE DATABASE catalog;
USE catalog;

DROP TABLE products;

CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT,
    code        VARCHAR(10),
    name        VARCHAR(20),
    description VARCHAR(30),
    price       DOUBLE,
    PRIMARY KEY (id)
);


INSERT INTO products(code, name, description, price)
VALUES ('P1001', 'Snow Shovel', 'Basic 15 inch', 9.99),
       ('P1002', 'Snow Shovel', 'Basic 22 inch', 12.99),
       ('P1003', 'Snow Shovel', 'Deluxe 24 inch', 19.99),
       ('P1004', 'Snow Shovel', 'Super Deluxe 26 inch', 49.99),
       ('P1005', 'Ice Scraper', 'Windshield 4 inch', 3.99),
       ('P1006', 'Roof Rake', 'Lightweight Metal Rake 65 Inch', 27.99),
       ('P1007', 'Roof Rake', 'Lightweight Metal Rake 35 Inch', 17.99),
       ('P1008', 'Roof Rake', 'Lightweight Metal Rake 25 Inch', 10.99),
       ('P1009', 'Snow Sledge', 'Basic 15 inch', 9.99),
       ('P1010', 'Snow Sledge', 'Basic 22 inch', 12.99),
       ('P1011', 'Snow Sledge', 'Deluxe 24 inch', 19.99),
       ('P1012', 'Snow Sledge', 'Super Deluxe 26 inch', 49.99),
       ('P1013', 'Ice Scraper', 'Windshield 12 inch', 10.99),
       ('P1014', 'Ice Scraper', 'Windshield 16 inch', 14.99),
       ('P1015', 'Winter Gloves', 'Polyurethane Material', 14.99);



TRUNCATE TABLE products;


SELECT MAX(price)
FROM products
WHERE name IN (SELECT DISTINCT name FROM products);


SELECT *
FROM products
WHERE name = ?
  AND price = (SELECT MAX(price) FROM products WHERE name = '?');

SELECT id, a.name, code, description, price
FROM products a,
     (SELECT name, max(price) AS maxprice FROM products GROUP BY name) b
WHERE a.name = b.name
  and price = maxprice;

