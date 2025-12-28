package es.uah.clienteActoresPeliculas.controller;

import es.uah.clienteActoresPeliculas.model.Pelicula;
import es.uah.clienteActoresPeliculas.paginator.PageRender;
import es.uah.clienteActoresPeliculas.service.IPeliculaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    IActorService actorService;

    @Autowired
    IPeliculaService peliculaService;

    @GetMapping(value= {"", "/"})
    public String inicio(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado =actorService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("actores/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actores/paginaPrincipalActores";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "actores/formActor";
    }

    @GetMapping("/id")
    public String buscarActorPorId(@RequestParam(name="page", defaultValue="0") int page, Model model,@RequestParam(name= "id", required = false) Integer id) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado;
        if (id==null||id==0) {
            listado = actorService.buscarTodos(pageable);
        } else {
            //Hay ID, buscamos UNA película
            Actor actorEncontrado = actorService.buscarActorPorId(id);
            if (actorEncontrado != null) {
                // Creamos una lista con un solo elemento
                List<Actor> listaUnica = List.of(actorEncontrado);
                // Y la envolvemos manualmente en un objeto PageImpl para engañar a la vista
                listado = new PageImpl<>(listaUnica, pageable, 1);
            } else {
                // Si no existe, devolvemos una página vacía
                listado = new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }
        PageRender<Actor> pageRender =new PageRender<Actor>("actores/id?id=%s".formatted(id), listado);
        model.addAttribute("mensajeFiltro", "Se han filtrado los actores por el id '"+ id+ "'");
        model.addAttribute("titulo", "Búsqueda de película por ID");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actores/paginaPrincipalActores";
    }


    @PostMapping("/guardar")
    public String guardarActor(Model model, Actor actor, RedirectAttributes attributes) {
        if(actor.getId()!=null && actor.getId()>0){
            actorService.actualizarActor(actor);
            attributes.addFlashAttribute("mensajeActualizado","El actor fue actualizado");
        }else{
            actorService.guardarActor(actor);
            attributes.addFlashAttribute("mensajeGuardado","El actor fue guardado");
        }
        return "redirect:/actores";
    }

    @GetMapping("/editar/{id}")
    public String editarActor(@PathVariable("id") int id, Model model) {
        Actor actor = actorService.buscarActorPorId(id);
        model.addAttribute("titulo", "Editar actor");
        model.addAttribute("actor", actor);
        return "actores/formActor";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        String nombreActor = actorService.buscarActorPorId(id).getNombre();
        actorService.eliminarActor(id);
        attributes.addFlashAttribute("mensajeBorrado", "Los datos del actor con id '"+id+"' y nombre '"+nombreActor+ "' fueron borrados!");
        return "redirect:/actores";
    }

    @GetMapping("/unirPelicula/{id}")
    public String unirPelicula(@PathVariable("id") int id, Model model) {
        Actor actor = actorService.buscarActorPorId(id);
        model.addAttribute("nombreActor", actor.getNombre());
        model.addAttribute("idActor", id);
        return "actores/unirActorPelicula";
    }

    @PostMapping("/unirActorPelicula")
    public String añadirPelicula(Model model, int id1, int id2, RedirectAttributes attributes){
        if(peliculaService.buscarPeliculaPorId(id2)==null){
            attributes.addFlashAttribute("mensajeError", "La película que has elegido no existe");
        }else if (actorService.buscarActorPorId(id1)==null) {
            attributes.addFlashAttribute("mensajeError", "El actor que has elegido no existe");
        }else{
            actorService.añadirPelicula(id1, id2);
            Actor actor = actorService.buscarActorPorId(id1);
            String nombreActor = actor.getNombre();
            Pelicula pelicula = peliculaService.buscarPeliculaPorId(id2);
            String tituloPelicula = pelicula.getTitulo();
            attributes.addFlashAttribute("mensajeExito", "Se ha apuntado al actor con nombre '"+ nombreActor+ "' en la película '"+ tituloPelicula+ "'");
        }
        return "redirect:/actores";
    }


}
