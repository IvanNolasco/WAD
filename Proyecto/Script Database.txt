CREATE DATABASE Moodle;

USE Moodle;

CREATE TABLE login(
	idLogin int auto_increment,
	userName varchar(25) not null,
	password varchar(45) not null,
	tipo int(1) not null,
	PRIMARY KEY (idLogin)
);

INSERT INTO login VALUES (1,"admin","admin",0);
INSERT INTO login VALUES (2, "Mayrasho", "mayrasho",2);
INSERT INTO login VALUES (3, "Ivan","ivan",1);
INSERT INTO login VALUES (4, "Loiz","loiz",2);
INSERT INTO login VALUES (4, "Ruben Peredo","rpv",1);