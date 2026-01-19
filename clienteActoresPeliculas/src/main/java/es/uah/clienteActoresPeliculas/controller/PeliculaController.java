package es.uah.clienteActoresPeliculas.controller;


import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import es.uah.clienteActoresPeliculas.paginator.PageRender;
import es.uah.clienteActoresPeliculas.service.IActorService;
import es.uah.clienteActoresPeliculas.service.IPeliculaService;
import es.uah.clienteActoresPeliculas.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    IPeliculaService peliculaService;

    @Autowired
    IActorService actorService;

    @Autowired
    private IUploadFileService uploadFileService;

    //Se añade .+ para agregar las extensiones de archivos, ya que Spring Boot las elimina
    //ResponseEntity<Resource>: Devuelve una respuesta http con el archivo físico que lleva dentro
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        //Crea un recurso vacío
        Resource recurso = null;

        try {
            //Uso de la función load para dejar listo el archivo
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Manda la imagen encontrada
        //attachment hace que la descargue el usuario
        //filename + recurso.Filename le dice que con que nombre guardarlo
        //body(recurso) le manda el archivo físico que tiene que mostrar
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

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
    public String guardarPelicula(Model model, Pelicula pelicula, @RequestParam("file") MultipartFile foto, RedirectAttributes attributes) throws IOException {

        //Si obtenemos foto de la página web
        if(!foto.isEmpty()){
            //Si existe la pelicula y tiene imagen la eliminamos de los archivos
            if (pelicula.getId()!=null && pelicula.getId()>0 && pelicula.getImagen()!=null && pelicula.getImagen().length()>0){
                uploadFileService.delete(pelicula.getImagen());
            }

            //Guardamos la nueva imagen en la carpeta y guardamos su nombre
            String uniqueFilename =null;
            try {
                uniqueFilename= uploadFileService.copy(foto);
            } catch(IOException e) {
                e.printStackTrace();
            }

            //Le damos a la pelicula la nueva imagen dada
            pelicula.setImagen(uniqueFilename);

        }

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
        //Se elimina la imagen de los archivos que tiene la película
        uploadFileService.delete(peliculaService.buscarPeliculaPorId(id).getImagen());
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
    public String añadirActor(Model model, Integer id1,@RequestParam(name="ids", required=false) List<Integer> id2, RedirectAttributes attributes) {
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
        attributes.addFlashAttribute("mensajeActores", "La lista de actores participes de la película '"+peliculaService.buscarPeliculaPorId(id1).getTitulo()+ "' fueron modificados");
        return "redirect:/peliculas";
    }


}
