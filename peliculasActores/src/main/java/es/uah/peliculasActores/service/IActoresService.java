package es.uah.peliculasActores.service;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;

import java.util.List;

//Interfaz de Service que da órdenes al DAO de actores, tiene otros objetivos que no sean únicamente acceder a la base de datos, contemplando posibles errores.
public interface IActoresService {

    List<Actor> buscarTodos();

    Actor buscarActorPorId(Integer idActor);

    List<Actor> buscarActoresPorNombre(String nombre);

    List<Actor> buscarActoresPorPais(String pais);

    List<Pelicula> buscarPeliculasDeActor(Integer idActor);

    void guardarActor(Actor actor);

    void eliminarActor(Integer idActor);

    void actualizarActor(Actor actor);

    void añadirPelicula(Integer idActor, Integer idPelicula);
}
