package es.uah.clienteActoresPeliculas.client;

import es.uah.clienteActoresPeliculas.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/user")
public interface UserClient {

    @GetExchange("")
    List<User> buscarTodos();

    @GetExchange("/{id}")
    User buscarPorId(@PathVariable("id") int idUser);

    @GetExchange("/login/{username}/{password}")
    User login(@PathVariable("username") String username, @PathVariable("password") String password);

    @PostExchange("/guardar")
    void guardarUser(@RequestBody User user);

    @PutExchange("/actualizar")
    void actualizarUser(@RequestBody User user);

    @DeleteExchange("/{id}")
    void eliminarUser(@PathVariable("id") int idUser);
}

