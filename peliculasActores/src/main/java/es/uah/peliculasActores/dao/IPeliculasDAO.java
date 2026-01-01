package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;

import java.util.List;

//Interfaz con los métodos que hacen de intermediario para realizar llamadas a la base de datos de la tabla Pelicula
public interface IPeliculasDAO {

    List<Pelicula> buscarTodos();

    Pelicula buscarPeliculaPorId(Integer idPelicula);

    List<Pelicula> buscarPeliculasPorTitulo(String titulo);

    List<Pelicula> buscarPeliculasPorGenero(String genero);

    List<Pelicula> buscarPeliculasPorActor(Actor actorPelicula);

    List<Actor> buscarActoresDePelicula(Integer idPelicula);

    void guardarActualizarPelicula(Pelicula pelicula);

    void eliminarPelicula(Integer idPelicula);

}
