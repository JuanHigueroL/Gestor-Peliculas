package es.uah.usuariosOpinionesEureka.controller;

import es.uah.usuariosOpinionesEureka.model.User;
import es.uah.usuariosOpinionesEureka.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/user")
    public List<User> buscarTodos(){
        return userService.buscarTodos();
    }

    @GetMapping("/user/{id}")
    public User buscarPorId(@PathVariable("id") int idUser) {
        return userService.buscarPorId(idUser);
    }

    @GetMapping("/user/login/{username}/{password}")
    public User buscarPorUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.buscarPorUsernameAndPassword(username, password);
    }

    @GetMapping("/user/username/{username}")
    public User buscarPorUsername(@PathVariable("username") String username) {
        return userService.buscarPorUsername(username);
    }

    @PostMapping("/user/guardar")
    public void guardarUser(@RequestBody User user){
        userService.guardarUser(user);
    }

    @PutMapping("/user/actualizar")
    public void actualizarUser(@RequestBody User user){
        userService.actualizarUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void eliminarUser(@PathVariable("id") int idUser) {
        userService.eliminarUser(idUser);
    }

}
