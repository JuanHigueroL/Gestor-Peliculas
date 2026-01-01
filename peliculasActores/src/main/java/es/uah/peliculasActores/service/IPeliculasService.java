package es.uah.peliculasActores.service;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;

import java.util.List;

//Interfaz de Service que da órdenes al DAO de peliculas, tiene otros objetivos que no sean únicamente acceder a la base de datos, contemplando posibles errores.
public interface IPeliculasService {

    List<Pelicula> buscarTodos();

    Pelicula buscarPeliculaPorId(Integer idPelicula);

    List<Pelicula> buscarPeliculasPorTitulo(String titulo);

    List<Pelicula> buscarPeliculasPorGenero(String genero);

    List<Pelicula> buscarPeliculasPorActor(Integer idActor);

    List<Actor> buscarActoresDePelicula(Integer idPelicula);

    void guardarPelicula(Pelicula pelicula);

    void eliminarPelicula(Integer idPelicula);

    void actualizarPelicula(Pelicula pelicula);

}
