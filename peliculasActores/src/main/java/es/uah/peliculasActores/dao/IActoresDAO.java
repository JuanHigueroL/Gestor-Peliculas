package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;

import java.util.List;

//Interfaz con los métodos que hacen de intermediario para realizar llamadas a la base de datos de la tabla Actor y la tabla intermedia entre actor y pelicula
public interface IActoresDAO {
    List<Actor> buscarTodos();

    Actor buscarActorPorId(Integer idActor);

    List<Actor> buscarActoresPorNombre(String nombre);

    List<Actor> buscarActoresPorPais(String pais);

    List<Pelicula> buscarPeliculasDeActor(Integer idActor);

    void guardarActualizarActor(Actor actor);

    void eliminarActor(Integer idActor);


}
