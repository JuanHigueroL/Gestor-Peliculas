package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActorService {

    Page<Actor> buscarTodos(Pageable pageable);

    Actor buscarActorPorId(Integer idActor);

    void guardarActor(Actor actor);

    void eliminarActor(Integer idActor);

    void actualizarActor(Actor actor);

    void añadirPelicula(Integer idActor, Integer idPelicula);

}
