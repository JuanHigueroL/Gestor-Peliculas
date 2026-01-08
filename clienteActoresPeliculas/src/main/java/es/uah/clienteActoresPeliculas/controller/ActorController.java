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

import java.util.*;

@Controller
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    IActorService actorService;

    @Autowired
    IPeliculaService peliculaService;

    @GetMapping(value= {"", "/", "/listado"})
    public String inicio(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado =actorService.buscarTodos(pageable);

        // Creamos un mapa: CLAVE = ID Actor y Lista de Películas
        Map<Integer, List<Pelicula>> peliculasPorActor = new HashMap<>();
        for (Actor actor : listado) {
            // Asignamos al ID del actor y sus películas
            peliculasPorActor.put(actor.getId(), actorService.buscarPeliculasDeActor(actor.getId()));
        }
        model.addAttribute("peliculasMap", peliculasPorActor);

        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/listado", listado);

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
        PageRender<Actor> pageRender =new PageRender<Actor>("/actores/id?id=%s".formatted(id), listado);
        model.addAttribute("mensajeFiltro", "Se han filtrado los actores por el id '"+ id+ "'");
        model.addAttribute("titulo", "Búsqueda de película por ID");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actores/paginaPrincipalActores";
    }

    @GetMapping("/pais")
    public String buscarActorPorPais(@RequestParam(name="page", defaultValue="0") int page, Model model,@RequestParam(name= "pais", required = false) String pais) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado;
        if (pais==null||pais.isEmpty()) {
            listado = actorService.buscarTodos(pageable);
        } else {
            listado = actorService.buscarActoresPorPais(pais, pageable);
        }
        PageRender<Actor> pageRender =new PageRender<Actor>("/actores/pais?pais=%s".formatted(pais), listado);
        model.addAttribute("mensajeFiltro", "Se han filtrado los actores por el país '"+ pais+ "'");
        model.addAttribute("titulo", "Búsqueda de actores por país");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actores/paginaPrincipalActores";
    }

    @GetMapping("/nombre")
    public String buscarActorPorNombre(@RequestParam(name="page", defaultValue="0") int page, Model model,@RequestParam(name= "nombre", required = false) String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado;
        if (nombre==null||nombre.isEmpty()) {
            listado = actorService.buscarTodos(pageable);
        } else {
            listado = actorService.buscarActoresPorNombre(nombre, pageable);
        }
        PageRender<Actor> pageRender =new PageRender<Actor>("/actores/nombre?nombre=%s".formatted(nombre), listado);
        model.addAttribute("mensajeFiltro", "Se han filtrado los actores por el nombre '"+ nombre+ "'");
        model.addAttribute("titulo", "Búsqueda de actores por nombre");
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
        List<Pelicula> peliculasActor = actorService.buscarPeliculasDeActor(id);
        List<Integer> idPeliculas = new ArrayList<>();
        for (Pelicula p : peliculasActor) {
            idPeliculas.add(p.getId());
        }
        model.addAttribute("listadoPeliculas", peliculaService.buscarTodosLista());
        model.addAttribute("nombreActor", actor.getNombre());
        model.addAttribute("peliculasActor", idPeliculas);
        model.addAttribute("idActor", id);
        return "actores/unirActorPelicula";
    }

    @PostMapping("/unirActorPelicula")
    public String añadirPelicula(Model model, Integer id1,@RequestParam(name="ids", required=false) List<Integer> id2, RedirectAttributes attributes) {
        List<Pelicula>peliculasActor = actorService.buscarPeliculasDeActor(id1);
        for (Pelicula p : peliculasActor) {
            if(peliculaService.buscarPeliculaPorId(p.getId())!=null && (p.getId() != null)) {
                actorService.eliminarPelicula(id1, p.getId());
            }
        }

        if(id2!=null) {
            for (Integer id : id2) {
                if (peliculaService.buscarPeliculaPorId(id) != null && actorService.buscarActorPorId(id1) != null) {
                    actorService.añadirPelicula(id1, id);
                }
            }
        }
        attributes.addFlashAttribute("mensajePeliculas", "La lista de películas en las que ha participado '"+actorService.buscarActorPorId(id1).getNombre()+ "' fueron modificadas");
        return "redirect:/actores";
    }

}
