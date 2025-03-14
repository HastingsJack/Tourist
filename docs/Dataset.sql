CREATE DATABASE IF NOT EXISTS EMP_ATTRACT_25;
USE EMP_ATTRACT_25;
drop table if exists EMP;
drop table if exists attraction_by_tags;
drop table if exists attraction;
drop table if exists tags;
drop table if exists city;

CREATE TABLE city(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(30) UNIQUE,

PRIMARY KEY(id)
);


CREATE TABLE attraction(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(30) UNIQUE,
description VARCHAR(255),
website VARCHAR(300),
cityId Integer,
FOREIGN KEY (cityId) REFERENCES city(id),

PRIMARY KEY(id)
);

CREATE TABLE tags(
id INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(30) UNIQUE,

PRIMARY KEY(id)
);


CREATE TABLE attraction_by_tags(
tagId INTEGER,
attractionId INTEGER,

FOREIGN KEY (tagId) REFERENCES tags(id),
FOREIGN KEY (attractionId) REFERENCES attraction(id)
);



INSERT INTO tags (name) VALUES
('Nature'),
('Adventure'),
('Paid'),
('Hiking'),
('Vulcano'),
('Diving');

INSERT INTO attraction (name, description, website) VALUES
('YellowStone', 'National Park', 'https://www.nps.gov/yell/index.htm'),
('Shenandoah', 'National Park', 'https://www.nps.gov/shen/index.htm'),
('Apo Island', 'Diving Site', 'https://www.tripadvisor.dk/Tourism-g1074098-Apo_Island_Dauin_Negros_Oriental_Negros_Island_Visayas-Vacations.html');


INSERT INTO attraction_by_tags (tagId, attractionId) VALUES
-- YellowStone (Nature, Adventure, Hiking, Vulcano)
(1, 1),  -- Nature
(2, 1),  -- Adventure
(4, 1),  -- Hiking
(5, 1),  -- Vulcano

-- Shenandoah (Nature, Adventure, Hiking)
(1, 2),  -- Nature
(2, 2),  -- Adventure
(4, 2),  -- Hiking

-- Apo Island (Diving, Nature, Adventure)
(6, 3),  -- Diving
(1, 3),  -- Nature
(2, 3);  -- Adventure



