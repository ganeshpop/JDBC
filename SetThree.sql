CREATE DATABASE rating;
USE rating;
DROP TABLE movies;
CREATE TABLE movies
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(25),
    release_year INT
);

INSERT INTO movies(name, release_year)
VALUES ('Avengers Endgame', 2019),
       ('Aquaman', 2018),
       ('Vertigo', 1958),
       ('Avatar', 2009),
       ('Titanic', 1997),
       ('The Lion King', 2019),
       ('Iron Man 3', 2013),
       ('Frozen 2', 2019),
       ('Minions', 2015),
       ('Spider Man', 2002);

CREATE TABLE ratings(id INT AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(25), movie_id INT, rating DOUBLE);

INSERT INTO ratings(user_name, movie_id, rating)
VALUES ('James', 1, 8.4),
       ('James', 8, 8.6),
       ('Tim', 1, 9.0),
       ('James', 1, 7.6),
       ('Tony', 10, 3.1),
       ('James', 6, 8.0),
       ('Tony', 9, 8.5),
       ('James', 3, 9.1),
       ('Zack', 10, 7.9),
       ('James', 7, 8.6),
       ('Albert', 9, 6.6),
       ('James', 5, 7.8),
       ('Tony', 7, 8.6),
       ('Zack', 5, 6.8),
       ('James', 2, 4.6),
       ('Tim', 4, 8.2),
       ('Albert', 3, 8.4),
       ('Zack', 8, 8.2),
       ('James', 5, 10),
       ('Fred', 7, 9.6),
       ('Albert', 2, 6.6);


SELECT * FROM movies WHERE name = 'Spider-Man';
SELECT r.name AS user, r.rating  FROM ratings r, movies m WHERE m.name = 'Spider-Man' AND r.movie_id = m.id ORDER BY  r.name;
SELECT r.name AS user, m.name AS movie, r.rating  FROM ratings r, movies m WHERE r.rating > 5 AND r.movie_id = m.id ORDER BY r.name, m.name;

# SELECT id , a.name, code, description, price FROM products a, (SELECT name, max(price) AS maxprice FROM products GROUP BY  name) b WHERE a.name = b.name and price = maxprice;

# SELECT r.name AS user, m.name AS movie, r.rating  FROM (SELECT name, movie_id, rating, max(rating) AS maxrating FROM ratings GROUP BY movie_id) r, movies m WHERE m.id = r.movie_id;


SELECT r.name AS user, m.name AS movie, r.rating FROM movies m JOIN rating.ratings r ON m.id = r.movie_id;


# SELECT r.name AS user, m.name AS movie, r.rating FROM movies m, (SELECT name, movie_id , max(rating) as rating FROM ratings GROUP BY  movie_id ORDER BY  movie_id) r WHERE m.id = r.movie_id ORDER BY m.name;


SELECT r.name AS user, m.name AS movie, r.rating FROM movies m, ratings r WHERE r.name = 'Zack' AND movie_id = (SELECT id FROM movies WHERE name = 'Spider-Man');

SELECT r.name as user, m.name as movie, r.rating FROM ratings r, (SELECT id as mid, name FROM movies WHERE name = 'Spider-Man') m WHERE r.name = 'Zack' AND r.movie_id = mid;
