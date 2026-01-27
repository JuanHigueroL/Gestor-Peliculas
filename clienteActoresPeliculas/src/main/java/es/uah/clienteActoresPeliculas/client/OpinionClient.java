package es.uah.clienteActoresPeliculas.client;

import es.uah.clienteActoresPeliculas.model.Opinion;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/opinions")
public interface OpinionClient {
    @GetExchange("")
    List<Opinion> buscarTodos();

    @GetExchange("/{id}")
    Opinion buscarPorId(@PathVariable int id);

    @GetExchange("/usuario/{idUsuario}/{idPelicula}")
    Opinion buscarOpinionPorUsuarioYPelicula(@PathVariable("idUsuario") int idUsuario, @PathVariable("idPelicula") int idPelicula);

    @GetExchange("/pelicula/{idPelicula}")
    List<Opinion> buscarOpinionPorPelicula(@PathVariable("idPelicula") int idPelicula);

    @PostExchange("/guardar")
    void guardarOpinion(@RequestBody Opinion opinion);

    @PutExchange("/actualizar")
    void actualizarOpinion(@RequestBody Opinion opinion);

    @DeleteExchange("/eliminar/{id}")
    void eliminarOpinion(@PathVariable("id") int idOpinion);

}
