USE `usuariosopinionesdbsec`;

SET FOREIGN_KEY_CHECKS = 0;

-- 1. LIMPIEZA
TRUNCATE TABLE `Opiniones`;
TRUNCATE TABLE `Users`;
DELETE FROM `Authorities` WHERE idRol > 0;
ALTER TABLE `Authorities` AUTO_INCREMENT = 1;

-- 2. INSERTAR ROLES
INSERT INTO `Authorities` (`authority`) VALUES ('ROLE_ADMIN'); -- 1
INSERT INTO `Authorities` (`authority`) VALUES ('ROLE_USER');  -- 2

-- 3. INSERTAR USUARIOS (Password '1234' en texto plano)
INSERT INTO `Users` (`username`, `password`, `correo`, `enable`, `idRol`) VALUES 
('admin', '1234', 'admin@cineando.com', 1, 1),
('cinefilo_pro', '1234', 'cinefilo@test.com', 1, 2),
('mr_hater', '1234', 'hater@test.com', 1, 2),
('ana_garcia', '1234', 'ana@test.com', 1, 2),
('super_fan', '1234', 'fan@test.com', 1, 2);

-- 4. INSERTAR OPINIONES
INSERT INTO `Opiniones` (`idUsuario`, `idPelicula`, `opinion`, `puntuacion`) VALUES
(1, 1, 'Imprescindible. Obra maestra.', 10),
(2, 1, 'Marlon Brando está increíble.', 10),
(3, 1, 'Muy lenta, me aburrí.', 4),
(4, 2, 'Muy divertida y rara.', 8),
(5, 2, 'Tarantino es un genio.', 9),
(2, 3, 'El mejor Joker de la historia.', 10),
(3, 3, 'Otra de superhéroes más...', 5),
(1, 4, 'Una historia preciosa.', 9),
(5, 4, 'Lloré al final.', 10),
(3, 5, 'Demasiado ñoña.', 3),
(4, 5, '¡Corre Forrest!', 10),
(1, 6, 'Visualmente perfecta.', 9),
(2, 6, 'Un lío mental maravilloso.', 8),
(5, 7, 'Revolucionaria.', 10),
(4, 7, 'Keanu Reeves guapísimo.', 8),
(1, 10, 'Épica.', 9),
(3, 10, 'Solo peleas y sangre.', 4),
(5, 10, 'Fuerza y Honor.', 10);

SET FOREIGN_KEY_CHECKS = 1;