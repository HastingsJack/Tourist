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