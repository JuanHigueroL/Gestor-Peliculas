package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPeliculaService {

    Page<Pelicula> buscarTodos(Pageable pageable);

    List<Pelicula> buscarTodosLista();

    Pelicula buscarPeliculaPorId(Integer idPelicula);

    Page<Pelicula> buscarPeliculasPorTitulo(String titulo, Pageable pageable);

    Page<Pelicula> buscarPeliculasPorGenero(String genero, Pageable pageable);

    Page<Pelicula> buscarPeliculasPorActor(Integer idActor, Pageable pageable);

    List<Actor> buscarActoresDePelicula(Integer idPelicula);

    void guardarPelicula(Pelicula pelicula);

    void eliminarPelicula(Integer idPelicula);

    void actualizarPelicula(Pelicula pelicula);

    void añadirActor(Integer idPelicula, Integer idActor);

    void eliminarActor(Integer idPelicula, Integer idActor);

}
