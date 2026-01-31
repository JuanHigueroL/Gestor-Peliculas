package es.uah.clienteActoresPeliculas.controller;

import es.uah.clienteActoresPeliculas.model.Authority;
import es.uah.clienteActoresPeliculas.model.Opinion;
import es.uah.clienteActoresPeliculas.model.User;
import es.uah.clienteActoresPeliculas.service.IOpinionService;
import es.uah.clienteActoresPeliculas.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/opinions")
public class OpinionController {

    @Autowired
    IOpinionService opinionService;

    // Asegúrate de tener inyectado el servicio de usuarios arriba
    @Autowired
    private IUserService userService;

    @GetMapping("/anadir/{idPelicula}")
    public String editarOpinion(@PathVariable("idPelicula") Integer idPelicula, Model model, Authentication authentication) {

        // Obtenemos el nombre del usuario logueado
        String username = authentication.getName();

        // Buscamos el objeto User completo en la base de datos
        User usuarioLogueado = userService.buscarPorUsername(username);

        // Buscamos si ya existe una opinión de este usuario para esta película
        Opinion opinion = opinionService.buscarOpinionPorUsuarioYPelicula(usuarioLogueado.getId(), idPelicula);

        // Si la opinión es null se crea una nueva vacía y se asigna la película y el usuario para que no falle el formulario.
        if (opinion == null) {
            opinion = new Opinion();
            opinion.setUsuario(usuarioLogueado);
            opinion.setIdPelicula(idPelicula);
            model.addAttribute("titulo", "Añadir nueva opinión");
        } else {
            model.addAttribute("titulo", "Editar tu opinión");
        }

        model.addAttribute("opinion", opinion);
        return "opiniones/FormOpinion";
    }

    @PostMapping("/guardar")
    public String guardarOpinion(Opinion opinion, Authentication authentication) {

        //  Recuperamos el usuario real que está logueado
        String username = authentication.getName();
        User usuarioLogueado = userService.buscarPorUsername(username);

        // Forzamos que la opinión pertenezca a este usuario
        opinion.setUsuario(usuarioLogueado);

        // Guardamos o Actualizamos
        if(opinion.getId() == null || opinion.getId() == 0) {
            opinionService.guardarOpinion(opinion);
        } else {
            opinionService.actualizarOpinion(opinion);
        }

        return "redirect:/peliculas";
    }
}
