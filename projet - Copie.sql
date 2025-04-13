CREATE DATABASE CAHIERTEXTE1;
USE CAHIERTEXTE1;



CREATE TABLE IF NOT EXISTS `Utilisateurs`
(
    `idUtilisateurs` INT NOT NULL AUTO_INCREMENT,
    `nomUtilisateur` VARCHAR(45) NOT NULL,
    `prenomUtilisateur` VARCHAR(45) NOT NULL,
    `emailUtilisateur` VARCHAR(45) NOT NULL UNIQUE ,
    `passwordUtilisateur` VARCHAR(45) NOT NULL,
    `roleUtilisateur` ENUM('Chef_departement','Professeur','Responsable') NOT NULL,
    PRIMARY KEY (`idUtilisateurs`)
);

CREATE TABLE IF NOT EXISTS `Departement`
(
    `idDepartement` INT AUTO_INCREMENT,
    `intitule` VARCHAR(37) NOT NULL,
    PRIMARY KEY (`idDepartement`)
); 

CREATE TABLE IF NOT EXISTS `ChefDepartement`
(
    `idChefDepartement` INT NOT NULL AUTO_INCREMENT,
    `idDepartement` INT NOT NULL UNIQUE,
    `idUtilisateurs` INT NOT NULL UNIQUE,
    PRIMARY KEY (`idChefDepartement`),
    FOREIGN KEY (`idUtilisateurs`) REFERENCES `Utilisateurs`(`idUtilisateurs`) ON DELETE CASCADE ON UPDATE  CASCADE,
    FOREIGN KEY (`idDepartement`)REFERENCES `Departement`(`idDepartement`) ON DELETE CASCADE ON UPDATE  CASCADE
);

CREATE TABLE IF NOT EXISTS `Professeur`
(
    `idProfesseur` INT NOT NULL AUTO_INCREMENT,
    `idUtilisateurs` INT NOT NULL UNIQUE,
    PRIMARY KEY (`idProfesseur`),
    FOREIGN KEY (`idUtilisateurs`)REFERENCES `Utilisateurs`(`idUtilisateurs`) ON DELETE CASCADE ON UPDATE  CASCADE
);

CREATE TABLE IF NOT EXISTS `Cours`
(
    `idCours` INT NOT NULL AUTO_INCREMENT,`Nom_du_cours` VARCHAR(45) NOT NULL,
    `nomCours` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`idCours`)
);


CREATE TABLE IF NOT EXISTS `CoursProfesseur`
(
    `idCoursProfesseur` INT AUTO_INCREMENT,
    `idProfesseur` INT NOT NULL,
    `idCours` INT NOT NULL,
    PRIMARY KEY(`idCoursProfesseur`),
    FOREIGN KEY(`idProfesseur`) REFERENCES `Professeur` (`idProfesseur`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(`idCours`) REFERENCES `Cours` (`idCours`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS `Classe`(
    `idClasse` INT NOT NULL AUTO_INCREMENT,
    `nom` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`idClasse`)
);
CREATE TABLE IF NOT EXISTS `CoursClasse`
(
    `idCoursClasse` INT AUTO_INCREMENT PRIMARY KEY,
    `idClasse` INT NOT NULL ,
    `idCours` INT NOT NULL,
    FOREIGN KEY(`idClasse`) REFERENCES `Classe` (`idClasse`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(`idCours`) REFERENCES `Cours` (`idCours`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Responsable` 
(
    `idResponsable` INT NOT NULL AUTO_INCREMENT,
    `idClasse` INT NOT NULL UNIQUE,
    `idUtilisateurs` INT NOT NULL UNIQUE,
    PRIMARY KEY (`idResponsable`),
    FOREIGN KEY (`idUtilisateurs`) REFERENCES `Utilisateurs`(`idUtilisateurs`) ON DELETE CASCADE ON UPDATE  CASCADE,
    FOREIGN KEY (`idClasse`) REFERENCES `Classe`(`idClasse`) ON DELETE CASCADE ON UPDATE  CASCADE
);


CREATE TABLE IF NOT EXISTS `Seance` 
(
    `idSeance` INT NOT NULL,
    `contenueSeance` VARCHAR(45) NOT NULL,
    `dureeSeance` VARCHAR(45) NOT NULL,
    `dateSeance` VARCHAR(45) NOT NULL,
    `idCours` INT NOT NULL UNIQUE,
    PRIMARY KEY (`idseance`),
    FOREIGN KEY(`idCours`) REFERENCES `Cours`(`idCours`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Validation`(
`idValidation` INT AUTO_INCREMENT PRIMARY KEY,
`idResponsable` INT NOT NULL UNIQUE,
`idCours` INT NOT NULL UNIQUE,
FOREIGN KEY (`idResponsable`) REFERENCES `Responsable`(`idResponsable`) ON DELETE CASCADE ON UPDATE  CASCADE,
FOREIGN KEY (`idCours`) REFERENCES `Cours`(`idCours`) ON DELETE CASCADE ON UPDATE  CASCADE
);


CREATE TABLE IF NOT EXISTS `Fiche` (
`idFiche` INT NOT NULL,
`dateGeneration` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
`contenu` TEXT,
`idCours` INT NOT NULL UNIQUE,
PRIMARY KEY (`idfiche`),
FOREIGN KEY(`idCours`) REFERENCES `Cours`(`idCours`) ON DELETE CASCADE ON UPDATE CASCADE
);







