-- Desactiva la comprobación de índices Únicos durante la ejecución del script. Lo comprueba al final
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

-- Déjame borrar o crear tablas en el orden que me dé la gana, no compruebes si el padre existe antes que el hijo.
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Define las "Reglas del Juego" o la rigurosidad de MySQL para esta sesión.
-- Para asegurar que los datos que insertas sean de alta calidad y cumplan los estándares modernos
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `peliculasactoresdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `peliculasactoresdb`;


-- Tabla de las películas
CREATE TABLE IF NOT EXISTS `peliculasactoresdb`.`Peliculas` (
  `idPelicula` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(150) NOT NULL,
  `año` YEAR NOT NULL,
  `duracion` INT(11) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `genero` VARCHAR(45) NULL,
  `sinopsis`TEXT NULL, 
  `imagen` TEXT NOT NULL,
  PRIMARY KEY (`idPelicula`))
ENGINE = InnoDB; -- motor de almacenamiento estándar, moderno y más usado en MySQL.



-- Tabla de los actores
CREATE TABLE IF NOT EXISTS `peliculasactoresdb`.`Actores` (
  `idActor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idActor`))
ENGINE = InnoDB; 



-- Tabla intermedia de peliculas y actores
CREATE TABLE IF NOT EXISTS `peliculasactoresdb`.`Peliculas_y_actores` (
  `Peliculas_idPelicula` INT NOT NULL,
  `Actores_idActorPeliculas_idPelicula` INT NOT NULL,
  PRIMARY KEY (`Peliculas_idPelicula`, `Actores_idActor`),
  -- Estos índices son puramente para optimizar la velocidad en la busqueda en JPA
  INDEX `fk_Peliculas_y_actores_Peliculas_idx` (`Peliculas_idPelicula` ASC) VISIBLE,
  INDEX `fk_Peliculas_y_actores_Actores_idx` (`Actores_idActor` ASC) VISIBLE,
  -- Estas 3 líneas "enlazan" la columna Pelicula_idPelicula (de esta tabla) con la columna idPelicula de la tabla Peliculas
  CONSTRAINT `fk_Peliculas_y_actores_Peliculas_idx`
    FOREIGN KEY (`Peliculas_idPelicula`)
    REFERENCES `peliculasactoresdb`.`Peliculas` (`idPelicula`)
	-- Esto le dice a MySQL qué hacer si intentas borrar o actualizar una pelicula. Que no haga nada, se encarga el programador
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  -- Estas 3 líneas "enlazan" la columna Pelicula_idPelicula (de esta tabla) con la columna idPelicula de la tabla Peliculas
  CONSTRAINT `fk_Peliculas_y_actores_Actores_idx`
    FOREIGN KEY (`Actores_idActor`)
    REFERENCES `peliculasactoresdb`.`Actores` (`idActor`)
    -- Esto le dice a MySQL qué hacer si intentas borrar o actualizar un actor. Que no haga nada, se encarga el programador
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- Apaga las normas de seguridad un momento para que pueda construir rápido y sin errores, y luego vuélvelas a encender
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;