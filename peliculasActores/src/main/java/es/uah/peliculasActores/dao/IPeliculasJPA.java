package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Esta interfaz implementa la tecnología Spring Data JPA para hacer llamadas a la base de datos mediante findby(), save()...
public interface IPeliculasJPA extends JpaRepository<Pelicula, Integer> {

    //Buscar películas que contengan el siguiente título e ignorando mayúsculas.
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    //Busca películas que contengan el siguiente género e ignorando mayúsculas.
    List<Pelicula> findByGeneroContainingIgnoreCase(String genero);

    //Busca películas que contengan el siguiente actor
    List<Pelicula> findByActoresContainingIgnoreCase(Actor actor);

}