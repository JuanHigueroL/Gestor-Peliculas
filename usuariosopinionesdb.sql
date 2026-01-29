-- -----------------------------------------------------
-- Schema usuariosopinionesdbsec
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `usuariosopinionesdbsec` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `usuariosopinionesdbsec` ;

-- -----------------------------------------------------
-- Table `Authorities` 
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `Authorities` ;

CREATE TABLE IF NOT EXISTS `Authorities` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Users`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `Users` ;

CREATE TABLE IF NOT EXISTS `Users` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `enable` TINYINT NOT NULL DEFAULT 1,
  `idRol` INT NOT NULL, 
  
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  
  INDEX `fk_Users_Authorities_idx` (`idRol` ASC) VISIBLE,
  CONSTRAINT `fk_Users_Authorities`
    FOREIGN KEY (`idRol`)
    REFERENCES `Authorities` (`idRol`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO `usuariosopinionesdbsec`.`authorities` (idRol, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `usuariosopinionesdbsec`.`authorities` (idRol, authority) VALUES (2, 'ROLE_USER');


-- -----------------------------------------------------
-- Table `Opiniones` 
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `Opiniones` ;

CREATE TABLE IF NOT EXISTS `Opiniones` (
  `idOpinion` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idPelicula` INT NOT NULL,
  `opinion` TEXT,
  `puntuacion` INT NOT NULL,
  PRIMARY KEY (`idOpinion`),
  UNIQUE INDEX `unique_Usuario_Pelicula` (`idUsuario` ASC, `idPelicula` ASC) VISIBLE,
  INDEX `fk_Opiniones_Usuarios_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Opiniones_Users`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Users` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;