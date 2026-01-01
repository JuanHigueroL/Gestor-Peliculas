package es.uah.clienteActoresPeliculas.client;

import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/peliculas")
public interface PeliculaClient {

    //Cuando yo llame a este metodo Java, construye y envía una petición GET a esta URL
    @GetExchange
    List<Pelicula> buscarTodos();

    @GetExchange("/{id}")
    Pelicula buscarPeliculaPorId(@PathVariable("id") Integer idPelicula);

    @GetExchange("/titulo/{titulo}")
    List<Pelicula> buscarPeliculasPorTitulo(@PathVariable String titulo);

    @GetExchange("/genero/{genero}")
    List<Pelicula> buscarPeliculasPorGenero(@PathVariable String genero);

    @GetExchange("/actor/{idActor}")
    List<Pelicula> buscarPeliculasPorActor(@PathVariable Integer idActor);

    @GetExchange("/{idPelicula}/actores")
    List<Actor> buscarActoresDePelicula(@PathVariable Integer idPelicula);

    @PostExchange
    void guardarPelicula(@RequestBody Pelicula pelicula);

    @DeleteExchange("/{id}")
    void eliminarPelicula(@PathVariable("id") Integer idPelicula);

    @PutExchange
    void actualizarPelicula(@RequestBody Pelicula pelicula);

}
