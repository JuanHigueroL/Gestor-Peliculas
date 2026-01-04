package es.uah.clienteActoresPeliculas.controller;


import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import es.uah.clienteActoresPeliculas.paginator.PageRender;
import es.uah.clienteActoresPeliculas.service.IActorService;
import es.uah.clienteActoresPeliculas.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    IPeliculaService peliculaService;

    @Autowired
    IActorService actorService;

    @GetMapping(value= {"", "/", "/listado"})
    public String inicio(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarTodos(pageable);

        // Creamos un mapa: CLAVE = ID Película y Lista de Actores
        Map<Integer, List<Actor>> actoresPorPelicula = new HashMap<>();

        for (Pelicula peli : listado) {
            // Asignamos al ID de la peli y sus actores
            actoresPorPelicula.put(peli.getId(), peliculaService.buscarActoresDePelicula(peli.getId()));
        }

        model.addAttribute("actoresMap", actoresPorPelicula);

        PageRender<Pelicula> pageRender = new PageRender<>("/peliculas/listado", listado);

        model.addAttribute("titulo", "Listado de todas las películas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);

        return "peliculas/paginaPrincipalPeliculas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("titulo", "Nueva Pelicula");
        return "peliculas/formPelicula";
    }

    @GetMapping("/id")
    public String buscarPeliculaPorId(@RequestParam(name="page", defaultValue="0") int page, Model model,@RequestParam(name= "id", required = false) Integer id) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (id==null||id==0) {
            listado = peliculaService.buscarTodos(pageable);
        } else {
            //Hay ID, buscamos UNA película
            Pelicula peliculaEncontrada = peliculaService.buscarPeliculaPorId(id);
            if (peliculaEncontrada != null) {
                // Creamos una lista con un solo elemento
                List<Pelicula> listaUnica = List.of(peliculaEncontrada);
                // Y la envolvemos manualmente en un objeto PageImpl para engañar a la vista
                listado = new PageImpl<>(listaUnica, pageable, 1);
            } else {
                // Si no existe, devolvemos una página vacía
                listado = new PageImpl<>(Collections.emptyList(), pageable, 0);
            }
        }

        // Creamos un mapa: CLAVE = ID Película y Lista de Actores
        Map<Integer, List<Actor>> actoresPorPelicula = new HashMap<>();

        for (Pelicula peli : listado) {
            // Asignamos al ID de la peli y sus actores
            actoresPorPelicula.put(peli.getId(), peliculaService.buscarActoresDePelicula(peli.getId()));
        }

        model.addAttribute("actoresMap", actoresPorPelicula);

        PageRender<Pelicula> pageRender =new PageRender<Pelicula>("/peliculas/id?id=%s".formatted(id), listado);
        model.addAttribute("mensajeFiltro", "Se han filtrado las películas por el id '"+ id+ "'");
        model.addAttribute("titulo", "Búsqueda de película por ID");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "peliculas/paginaPrincipalPeliculas";
    }

    @GetMapping("/titulo")
    public String buscarPeliculaPorTitulo(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam(name="titulo", required = false) String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (titulo==null||titulo.isEmpty()) {
            listado = peliculaService.buscarTodos(pageable);
        } else{
            listado= peliculaService.buscarPeliculasPorTitulo(titulo, pageable);
        }

        // Creamos un mapa: CLAVE = ID Película y Lista de Actores
        Map<Integer, List<Actor>> actoresPorPelicula = new HashMap<>();

        for (Pelicula peli : listado) {
            // Asignamos al ID de la peli y sus actores
            actoresPorPelicula.put(peli.getId(), peliculaService.buscarActoresDePelicula(peli.getId()));
        }

        model.addAttribute("actoresMap", actoresPorPelicula);

        PageRender<Pelicula> pageRender =new PageRender<Pelicula>("/peliculas/titulo?titulo=%s".formatted(titulo), listado);
        model.addAttribute("mensajeFiltro", "Se ha filtrado las películas por el título '"+ titulo+"'");
        model.addAttribute("titulo", "Listado de peliculas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "peliculas/paginaPrincipalPeliculas";
    }

    @GetMapping("/genero")
    public String buscarPeliculaPorGenero(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam(name="genero", required = false) String genero) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (genero==null||genero.isEmpty()) {
            listado = peliculaService.buscarTodos(pageable);
        } else{
            listado= peliculaService.buscarPeliculasPorGenero(genero, pageable);
        }

        // Creamos un mapa: CLAVE = ID Película y Lista de Actores
        Map<Integer, List<Actor>> actoresPorPelicula = new HashMap<>();

        for (Pelicula peli : listado) {
            // Asignamos al ID de la peli y sus actores
            actoresPorPelicula.put(peli.getId(), peliculaService.buscarActoresDePelicula(peli.getId()));
        }

        model.addAttribute("actoresMap", actoresPorPelicula);

        PageRender<Pelicula> pageRender =new PageRender<Pelicula>("/peliculas/genero?genero=%s".formatted(genero), listado);
        model.addAttribute("mensajeFiltro", "Se ha filtrado las películas por el genero '"+ genero+"'");
        model.addAttribute("titulo", "Listado de peliculas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "peliculas/paginaPrincipalPeliculas";
    }

    @GetMapping("/actor")
    public String buscarPeliculaPorActor(Model model, @RequestParam(name="page", defaultValue="0") int page, @RequestParam(name= "actor", required = false) Integer actor) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado;
        if (actor==null) {
            listado = peliculaService.buscarTodos(pageable);
        } else{
            listado= peliculaService.buscarPeliculasPorActor(actor, pageable);
        }

        // Creamos un mapa: CLAVE = ID Película y Lista de Actores
        Map<Integer, List<Actor>> actoresPorPelicula = new HashMap<>();

        for (Pelicula peli : listado) {
            // Asignamos al ID de la peli y sus actores
            actoresPorPelicula.put(peli.getId(), peliculaService.buscarActoresDePelicula(peli.getId()));
        }

        model.addAttribute("actoresMap", actoresPorPelicula);

        PageRender<Pelicula> pageRender =new PageRender<Pelicula>("/peliculas/actor?actor=%s".formatted(actor), listado);
        model.addAttribute("mensajeFiltro", "Se ha filtrado las películas por el id del actor '"+ actor+"'");
        model.addAttribute("actor", "Listado de peliculas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "peliculas/paginaPrincipalPeliculas";
    }


    @PostMapping("/guardar")
    public String guardarPelicula(Model model, Pelicula pelicula, RedirectAttributes attributes) {
        if(pelicula.getId()!=null && pelicula.getId()>0){
            peliculaService.actualizarPelicula(pelicula);
            attributes.addFlashAttribute("mensajeActualizado","La película fue actualizada");
        }else{
            peliculaService.guardarPelicula(pelicula);
            attributes.addFlashAttribute("mensajeGuardado","La pelicula fue guardada");
        }
        return "redirect:/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String editarPelicula(@PathVariable("id") int id, Model model) {
        Pelicula pelicula = peliculaService.buscarPeliculaPorId(id);
        model.addAttribute("titulo", "Editar pelicula");
        model.addAttribute("pelicula", pelicula);
        return "peliculas/formPelicula";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        String titulo= peliculaService.buscarPeliculaPorId(id).getTitulo();
        peliculaService.eliminarPelicula(id);
        attributes.addFlashAttribute("mensajeBorrado", "Los datos de la película '"+titulo+ "' "+ "con id '"+id+"' fue borrada");
        return "redirect:/peliculas";
    }

    @GetMapping("/unirActor/{id}")
    public String unirActor(@PathVariable("id") int id, Model model) {
        Pelicula pelicula = peliculaService.buscarPeliculaPorId(id);
        List<Actor> actoresPelicula = peliculaService.buscarActoresDePelicula(id);
        List<Integer> idActores = new ArrayList<>();
        for (Actor a : actoresPelicula) {
            idActores.add(a.getId());
        }
        model.addAttribute("listadoActores", actorService.buscarTodosLista());
        model.addAttribute("tituloPelicula", pelicula.getTitulo());
        model.addAttribute("actoresPelicula", idActores);
        model.addAttribute("idPelicula", id);
        return "peliculas/unirPeliculaActor";
    }

    @PostMapping("/unirPeliculaActor")
    public String añadirActor(Model model, Integer id1,@RequestParam(name="ids", required=false) List<Integer> id2) {
        List<Actor> actoresPelicula = peliculaService.buscarActoresDePelicula(id1);
        for (Actor a : actoresPelicula) {
            if (actorService.buscarActorPorId(a.getId()) != null && (a.getId() != null)) {
                peliculaService.eliminarActor(id1, a.getId());
            }
        }
        if (id2 != null) {
            for (Integer id : id2) {
                if (actorService.buscarActorPorId(id) != null && peliculaService.buscarPeliculaPorId(id1) != null) {
                    peliculaService.añadirActor(id1, id);
                }
            }
        }
        return "redirect:/peliculas";
    }


}
