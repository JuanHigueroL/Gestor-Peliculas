package es.uah.peliculasActores.controller;

import es.uah.peliculasActores.dao.IPeliculasDAO;
import es.uah.peliculasActores.dao.IPeliculasJPA;
import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import es.uah.peliculasActores.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//recibe las peticiones externas (HTTP GET, POST...) y se las pasa al Service para que las procese.
@RestController
public class PeliculasController {

    //Llama al componente Service de las peliculas
    @Autowired
    IPeliculasService peliculasService;

    //Cuando realiza un get con la URL "/peliculas" llama a la función buscarTodos de Service
    @GetMapping("/peliculas")
    public List<Pelicula> buscarTodos() {
        return peliculasService.buscarTodos();
    }

    //Cuando realiza un get con la URL "/peliculas/(id de la pelicula)" llama a la función buscarPeliculaPorId de Service, enviándole la id de la URL como variable
    @GetMapping("/peliculas/{id}")
    public Pelicula buscarPeliculaPorId(@PathVariable("id") Integer idPelicula) {
        return peliculasService.buscarPeliculaPorId(idPelicula);
    }

    //Cuando realiza un get con la URL "/peliculas/titulo/(titulo de la pelicula)" llama a la función buscarPeliculaPorTitulo de Service, enviándole el título de la URL como variable
    @GetMapping("/peliculas/titulo/{titulo}")
    public List<Pelicula> buscarPeliculasPorTitulo(@PathVariable String titulo) {
        return  peliculasService.buscarPeliculasPorTitulo(titulo);
    }

    //Cuando realiza un get con la URL "/peliculas/genero/(genero de la pelicula)" llama a la función buscarPeliculaPorGenero de Service, enviándole el genero de la URL como variable
    @GetMapping("/peliculas/genero/{genero}")
    public List<Pelicula> buscarPeliculasPorGenero(@PathVariable String genero) {
        return  peliculasService.buscarPeliculasPorGenero(genero);
    }

    //Cuando realiza un get con la URL "/peliculas/actor/(id del actor)" llama a la función buscarPeliculaPorActor de Service, enviándole el id del actor de la URL como variable
    @GetMapping("/peliculas/actor/{idActor}")
    public List<Pelicula> buscarPeliculasPorActor(@PathVariable Integer idActor) {
        return  peliculasService.buscarPeliculasPorActor(idActor);
    }

    //Cuando realiza un get con la URL "/peliculas/(id de la pelicula)/actores" llama a la función buscarActoresDePelicula de Service, enviándole el id de la pelicula de la URL como variable
    @GetMapping("/peliculas/{id}/actores")
    public List<Actor> buscarActoresDePelicula(@PathVariable Integer id) {
        return peliculasService.buscarActoresDePelicula(id);
    }

    //Cuando realiza un post con la URL "/peliculas" llama a la función guardarPelicula, enviándole el JSON como una pelicula
    @PostMapping("/peliculas")
    public void guardarPelicula(@RequestBody Pelicula pelicula) {
        peliculasService.guardarPelicula(pelicula);
    }

    //Cuando realiza un delete con la URL "/peliculas/(id de la pelicula)" llama a la función eliminarPelicula, enviándole la id de la URL como variable
    @DeleteMapping("/peliculas/{id}")
    public void eliminarPelicula(@PathVariable("id") Integer idPelicula) {
        peliculasService.eliminarPelicula(idPelicula);
    }

    //Cuando realiza un put con la URL "/peliculas" llama a la función actualizarPelicula, enviándole el JSON como una Pelicula
    @PutMapping("/peliculas")
    public void actualizarPelicula(@RequestBody Pelicula pelicula) {
        peliculasService.actualizarPelicula(pelicula);
    }

    @PostMapping("/peliculas/{id1}/{id2}")
    public void añadirActor(@PathVariable("id1") Integer idPelicula,@PathVariable("id2") Integer idActor){
        peliculasService.añadirActor(idPelicula,idActor);
    }

    @PostMapping("/peliculas/eliminar/{id1}/{id2}")
    public void eliminarActor(@PathVariable("id1") Integer idPelicula,@PathVariable("id2") Integer idActor){
        peliculasService.eliminarActor(idPelicula,idActor);
    }

}
