package es.uah.clienteActoresPeliculas.client;

import es.uah.clienteActoresPeliculas.model.Authority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/authorities")
public interface AuthorityClient {
    @GetExchange("")
    public List<Authority> buscarTodos();

    @GetExchange("/{id}")
    public Authority buscarPorId(@PathVariable("id") int idRol);

}