package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Esta interfaz implementa la tecnología Spring Data JPA para hacer llamadas a la base de datos mediante findby(), save()...
public interface IActoresJPA extends JpaRepository<Actor, Integer> {

    List<Actor> findByNombreContainingIgnoreCase(String nombre);

    List<Actor> findByPaisContainingIgnoreCase(String pais);

}
