package es.uah.clienteActoresPeliculas.controller;


import es.uah.clienteActoresPeliculas.model.Authority;
import es.uah.clienteActoresPeliculas.model.User;
import es.uah.clienteActoresPeliculas.service.IAuthorityService;
import es.uah.clienteActoresPeliculas.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    IUserService userService;

    @Autowired
    private IAuthorityService rolService;

    // Muestra la pantalla de login
    @GetMapping("/login")
    public String login(Model model, Principal principal) {

        if (principal != null) {
            return "redirect:/peliculas";
        }
        //model.addAttribute("userFailed", "Error al iniciar sesión");
        return "login"; // Carga login.html
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/peliculas";
    }

    // Muestra la pantalla de registro
    @GetMapping("/registro")
    public String registrar(Model model) {
        model.addAttribute("usuario", new User()); // Crea un usuario vacío para el formulario

        // Buscamos la lista de objetos Rol en la BBDD
        List<Authority> todosLosRoles = rolService.buscarTodos();
        model.addAttribute("listaRoles", todosLosRoles);

        return "registro"; // Carga registro.html
    }

    // Muestra la pantalla de registro
    @PostMapping("/registrado")
    public String registrado(Model model, @Valid @ModelAttribute("usuario") User usuario, BindingResult result, @RequestParam("rolId") Integer rolId ){
        if (result.hasErrors()) {
            List<Authority> todosLosRoles = rolService.buscarTodos();
            model.addAttribute("listaRoles", todosLosRoles);
            return "registro";
        }

        Authority rolSeleccionado = rolService.buscarPorId(rolId);
        usuario.setRol(rolSeleccionado);
        userService.guardarUser(usuario);
        return "redirect:/login"; // Carga registro.html
    }




}