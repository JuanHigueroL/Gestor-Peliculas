package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActorService {

    Page<Actor> buscarTodos(Pageable pageable);

    List<Actor> buscarTodosLista();

    Actor buscarActorPorId(Integer idActor);

    Page<Actor> buscarActoresPorNombre(String nombre, Pageable pageable);

    Page<Actor> buscarActoresPorPais(String pais, Pageable pageable);

    List<Pelicula> buscarPeliculasDeActor(Integer idActor);

    void guardarActor(Actor actor);

    void eliminarActor(Integer idActor);

    void actualizarActor(Actor actor);

    void añadirPelicula(Integer idActor, Integer idPelicula);

    void eliminarPelicula(Integer idActor, Integer idPelicula);

}
