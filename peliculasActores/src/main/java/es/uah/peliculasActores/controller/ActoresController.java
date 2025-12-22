package es.uah.peliculasActores.controller;

import es.uah.peliculasActores.dao.IActoresDAO;
import es.uah.peliculasActores.dao.IActoresJPA;
import es.uah.peliculasActores.dao.IPeliculasJPA;
import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import es.uah.peliculasActores.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//recibe las peticiones externas (HTTP GET, POST...) y se las pasa al Service para que las procese.
@RestController
public class ActoresController{

    //Llama al componente Service de los actores
    @Autowired
    IActoresService iActoresService;

    //Cuando realiza un get con la URL "/actores" llama a la función buscarTodos de Service
    @GetMapping("/actores")
    public List<Actor> buscarTodos() {
        return iActoresService.buscarTodos();
    }

    //Cuando realiza un get con la URL "/actores/(id del actor)" llama a la función buscarActorPorId de Service, enviándole la id de la URL como variable
    @GetMapping("/actores/{id}")
    public Actor buscarActorPorId(@PathVariable("id")Integer idActor) {
        return iActoresService.buscarActorPorId(idActor);
    }

    //Cuando realiza un post con la URL "/actores" llama a la función guardarActor, enviándole el JSON como un Actor
    @PostMapping("/actores")
    public void guardarActor(@RequestBody Actor actor) {
        iActoresService.guardarActor(actor);
    }

    //Cuando realiza un delete con la URL "/actores/(id del actor)" llama a la función eliminarActor, enviándole la id de la URL como variable
    @DeleteMapping("/actores/{id}")
    public void eliminarActor(@PathVariable("id") Integer idActor) {
        iActoresService.eliminarActor(idActor);
    }

    //Cuando realiza un put con la URL "/actores" llama a la función actualizarActor, enviándole el JSON como un Actor
    @PutMapping("/actores")
    public void actualizarActor(@RequestBody Actor actor) {
        iActoresService.actualizarActor(actor);
    }

    //Cuando realiza un post con la URL "/actores/(id de un actor)/(id de una pelicula)" llama a la función añadirPelicula, enviándole la id del actor y de la película de la URL como variable
    @PostMapping("/actores/{id1}/{id2}")
    public void añadirPelicula(@PathVariable("id1") Integer idActor,@PathVariable("id2") Integer idPelicula){
        iActoresService.añadirPelicula(idActor,idPelicula);
    }

}
