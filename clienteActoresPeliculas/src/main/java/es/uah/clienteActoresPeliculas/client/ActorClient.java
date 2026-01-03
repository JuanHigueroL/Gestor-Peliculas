package es.uah.clienteActoresPeliculas.client;

import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/actores")
public interface ActorClient {

    @GetExchange
    List<Actor> buscarTodos();

    @GetExchange("/{id}")
    Actor buscarActorPorId(@PathVariable("id")Integer idActor);

    @GetExchange("/nombre/{nombre}")
    List<Actor> buscarActoresPorNombre(@PathVariable String nombre);

    @GetExchange("/pais/{pais}")
    List<Actor> buscarActoresPorPais(@PathVariable String pais);

    @GetExchange("/{idActor}/peliculas")
    List<Pelicula> buscarPeliculasDeActor(@PathVariable Integer idActor);

    @PostExchange
    void guardarActor(@RequestBody Actor actor);

    @DeleteExchange("/{id}")
    void eliminarActor(@PathVariable("id") Integer idActor);

    @PutExchange
    void actualizarActor(@RequestBody Actor actor);

    @PostExchange("/{id1}/{id2}")
    void añadirPelicula(@PathVariable("id1") Integer idActor,@PathVariable("id2") Integer idPelicula);

    @PostExchange("/eliminar/{id1}/{id2}")
    void eliminarPelicula(@PathVariable("id1") Integer idActor,@PathVariable("id2") Integer idPelicula);

}
