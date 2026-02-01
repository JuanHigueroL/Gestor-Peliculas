USE `peliculasactoresdb`;

-- Desactivamos claves foráneas para poder limpiar
SET FOREIGN_KEY_CHECKS = 0;

-- 1. LIMPIEZA (Para que los IDs empiecen por 1 y coincidan con las relaciones)
TRUNCATE TABLE `Peliculas_y_actores`;
TRUNCATE TABLE `Actores`;
TRUNCATE TABLE `Peliculas`;

-- 2. INSERTAR 10 PELÍCULAS (Con imagen vacía '')
INSERT INTO `Peliculas` (`titulo`, `año`, `duracion`, `pais`, `direccion`, `genero`, `sinopsis`, `imagen`) VALUES
('El Padrino', 1972, 175, 'EEUU', 'Francis Ford Coppola', 'Crimen', 'El patriarca de una organización criminal transfiere el control a su hijo.', ''),
('Pulp Fiction', 1994, 154, 'EEUU', 'Quentin Tarantino', 'Crimen', 'Historias cruzadas de criminales en Los Ángeles.', ''),
('El Caballero Oscuro', 2008, 152, 'EEUU', 'Christopher Nolan', 'Acción', 'Batman se enfrenta al Joker, quien busca el caos en Gotham.', ''),
('Cadena Perpetua', 1994, 142, 'EEUU', 'Frank Darabont', 'Drama', 'La historia de esperanza de dos hombres encarcelados.', ''),
('Forrest Gump', 1994, 142, 'EEUU', 'Robert Zemeckis', 'Drama', 'La vida de un hombre con bajo CI que presencia momentos históricos.', ''),
('Inception', 2010, 148, 'EEUU', 'Christopher Nolan', 'Ciencia Ficción', 'Un ladrón roba secretos corporativos a través del uso de la tecnología de compartir sueños.', ''),
('Matrix', 1999, 136, 'EEUU', 'Hermanas Wachowski', 'Ciencia Ficción', 'Un hacker descubre la verdadera naturaleza de su realidad.', ''),
('El Señor de los Anillos: La Comunidad del Anillo', 2001, 178, 'Nueva Zelanda', 'Peter Jackson', 'Fantasía', 'Un hobbit debe destruir un anillo poderoso para salvar el mundo.', ''),
('Interstellar', 2014, 169, 'EEUU', 'Christopher Nolan', 'Ciencia Ficción', 'Un equipo de exploradores viaja a través de un agujero de gusano en el espacio.', ''),
('Gladiator', 2000, 155, 'EEUU', 'Ridley Scott', 'Acción', 'Un general romano traicionado busca venganza como gladiador.', '');

-- 3. INSERTAR 20 ACTORES (Con imagen vacía '')
INSERT INTO `Actores` (`nombre`, `fechaNacimiento`, `pais`, `imagen`) VALUES
('Marlon Brando', '1924-04-03', 'EEUU', ''),      -- ID 1
('Al Pacino', '1940-04-25', 'EEUU', ''),          -- ID 2
('John Travolta', '1954-02-18', 'EEUU', ''),    -- ID 3
('Samuel L. Jackson', '1948-12-21', 'EEUU', ''),  -- ID 4
('Christian Bale', '1974-01-30', 'Reino Unido', ''),-- ID 5
('Heath Ledger', '1979-04-04', 'Australia', ''),  -- ID 6
('Tim Robbins', '1958-10-16', 'EEUU', ''),       -- ID 7
('Morgan Freeman', '1937-06-01', 'EEUU', ''),    -- ID 8
('Tom Hanks', '1956-07-09', 'EEUU', ''),           -- ID 9
('Robin Wright', '1966-04-08', 'EEUU', ''),        -- ID 10
('Leonardo DiCaprio', '1974-11-11', 'EEUU', ''),     -- ID 11
('Joseph Gordon-Levitt', '1981-02-17', 'EEUU', ''),-- ID 12
('Keanu Reeves', '1964-09-02', 'Líbano', ''),      -- ID 13
('Laurence Fishburne', '1961-07-30', 'EEUU', ''),-- ID 14
('Elijah Wood', '1981-01-28', 'EEUU', ''),         -- ID 15
('Ian McKellen', '1939-05-25', 'Reino Unido', ''),-- ID 16
('Matthew McConaughey', '1969-11-04', 'EEUU', ''),-- ID 17
('Anne Hathaway', '1982-11-12', 'EEUU', ''),        -- ID 18
('Russell Crowe', '1964-04-07', 'Nueva Zelanda', ''),-- ID 19
('Joaquin Phoenix', '1974-10-28', 'Puerto Rico', ''); -- ID 20

-- 4. UNIR ACTORES CON PELÍCULAS
INSERT INTO `Peliculas_y_actores` (`Peliculas_idPelicula`, `Actores_idActor`) VALUES
(1, 1), (1, 2),   -- El Padrino: Brando, Pacino
(2, 3), (2, 4),   -- Pulp Fiction: Travolta, Samuel L
(3, 5), (3, 6),   -- Batman: Bale, Ledger
(4, 7), (4, 8),   -- Cadena Perpetua: Robbins, Freeman
(5, 9), (5, 10),  -- Forrest Gump: Hanks, Wright
(6, 11), (6, 12), -- Inception: DiCaprio, Gordon-Levitt
(7, 13), (7, 14), -- Matrix: Keanu, Fishburne
(8, 15), (8, 16), -- Anillos: Elijah, Ian
(9, 17), (9, 18), -- Interstellar: Matthew, Anne
(10, 19), (10, 20); -- Gladiator: Crowe, Phoenix

-- Reactivamos claves foráneas
SET FOREIGN_KEY_CHECKS = 1;