package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

//Esta interfaz implementa la tecnología Spring Data JPA para hacer llamadas a la base de datos mediante findby(), save()...
public interface IActoresJPA extends JpaRepository<Actor, Integer> {

}