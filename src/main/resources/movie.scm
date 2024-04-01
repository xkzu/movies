DROP TABLE movie;

CREATE TABLE movie(
id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
title varchar2(50) NOT NULL ,
YEAR varchar2(4) NOT NULL ,
director varchar2(50) NOT NULL ,
genre varchar2(50) NOT NULL ,
synopsis varchar2(500) NOT NULL 
);

-- Insertar la película Interstellar
INSERT INTO movie (title, YEAR, director, genre, synopsis)
VALUES ('Interstellar', '2014', 'Christopher Nolan', 'Science Fiction', 'A group of scientists and explorers, led by Cooper, embark on a space journey to find a place with the necessary conditions to replace Earth and start a new life there. Earth is coming to an end and this group needs to find a planet beyond our galaxy that will guarantee the future of the human race.');

-- Insertar la película Inception
INSERT INTO movie (title, YEAR, director, genre, synopsis)
VALUES ('Inception', '2010', 'Christopher Nolan', 'Science Fiction', 'Dom Cobb (Leonardo DiCaprio) is a thief with an uncanny ability to enter people''s dreams and steal the secrets of their subconscious. His ability has made him a hot commodity in the world of corporate espionage, but it has come at a great cost to the people he loves. Cobb has a chance to be forgiven when he is given an impossible task: plant an idea in a person''s mind. If he succeeds, it will be the perfect crime, but an enemy anticipates his moves.');

-- Insertar la película Arrival
INSERT INTO movie (title, YEAR, director, genre, synopsis)
VALUES ('Arrival', '2016', 'Denis Villeneuve', 'Science Fiction', 'Twelve alien spacecraft begin arriving on our planet. The military high command asks for the help of an expert linguist to try to find out if the aliens come in peace or pose a threat. Little by little the woman will try to learn to communicate with the strange invaders, who have their own language, to find out the true and mysterious reason for the extraterrestrial visit.');

-- Insertar la película The Martian
INSERT INTO movie (title, YEAR, director, genre, synopsis)
VALUES ('The Martian', '2015', 'Ridley Scott', 'Science Fiction', 'A space explorer is trapped on Mars after being abandoned by his crew members, who thought he had died in a storm. With almost no resources and only his wits, he will try to survive while NASA, on the one hand, and his crew members, on their own, try to rescue him.');

-- Insertar la película The Matrix
INSERT INTO movie (title, YEAR, director, genre, synopsis)
VALUES ('The Matrix', '1999', 'Lana Wachowski, Lilly Wachowski', 'Science Fiction', 'Computer programmer Thomas Anderson, better known in the hacker world as Neo, is targeted by the dreaded Agent Smith. Two other hackers, Trinity and Morpheus, contact Neo to help him escape. The Matrix possesses you. Follow the white rabbit.');

COMMIT;

SELECT * FROM movie;