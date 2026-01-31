package es.uah.clienteActoresPeliculas.controller;

import es.uah.clienteActoresPeliculas.model.Authority;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import es.uah.clienteActoresPeliculas.model.User;
import es.uah.clienteActoresPeliculas.service.IAuthorityService;
import es.uah.clienteActoresPeliculas.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IAuthorityService authorityService;

    @GetMapping(value= {"", "/", "/listado"})
    public String inicio(Model model) {
        List<User> listado = userService.buscarTodos();

        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("listadoUsuarios", listado);

        return "usuarios/paginaPrincipalUsuarios";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarUsuario(Model model, @PathVariable("id") Integer id) {
        userService.eliminarUser(id);
        return "redirect:/user";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model) {
        User user = userService.buscarPorId(id);
        model.addAttribute("titulo", "Editar usuario");
        model.addAttribute("usuario", user);
        model.addAttribute("listaRoles", authorityService.buscarTodos());
        return "usuarios/FormUser";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(User user, @RequestParam("rolId") Integer rolId) {

        Authority rolSeleccionado = authorityService.buscarPorId(rolId);
        user.setRol(rolSeleccionado);

        if (user.getEnable() == null) {
            user.setEnable(true);
        }
        userService.actualizarUser(user);

        return "redirect:/user";
    }

}
