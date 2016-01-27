DROP DATABASE agenda;

CREATE DATABASE agenda;

USE agenda;

CREATE TABLE pessoa (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  email varchar(40) DEFAULT NULL,
  telefone varchar(40) DEFAULT NULL,
  dtaNasc datetime NOT NULL,
  estCivil varchar(12) NOT NULL,
  PRIMARY KEY (id),
);
