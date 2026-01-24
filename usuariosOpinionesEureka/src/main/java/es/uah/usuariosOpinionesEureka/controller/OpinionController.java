package es.uah.usuariosOpinionesEureka.controller;

import es.uah.usuariosOpinionesEureka.model.Opinion;
import es.uah.usuariosOpinionesEureka.service.IOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OpinionController {
    @Autowired
    IOpinionService opinionService;

    @GetMapping("/opinions")
    public List<Opinion> buscarTodos() {
        return opinionService.buscarTodos();
    }

    @GetMapping("/opinions/{id}")
    public Opinion buscarPorId(@PathVariable("id") int idOpinion){
        return opinionService.buscarPorId(idOpinion);
    }

    @GetMapping("/opinions/usuario/{idUsuario}/{idPelicula}")
    public Opinion buscarOpinionPorUsuarioYPelicula(@PathVariable("idUsuario") int idUsuario, @PathVariable("idPelicula") int idPelicula){
        return opinionService.buscarOpinionPorUsuarioYPelicula(idUsuario, idPelicula);
    }

    @GetMapping("/opinions/pelicula/{idPelicula}")
    public List<Opinion> buscarOpinionPorPelicula(@PathVariable("idPelicula") int idPelicula){
        return opinionService.buscarOpinionPorPelicula(idPelicula);
    }

    @PostMapping("/opinions/guardar")
    public void guardarOpinion(@RequestBody Opinion opinion){
        opinionService.guardarOpinion(opinion);
    }

    @PutMapping("/opinions/actualizar")
    public void actualizarOpinion(@RequestBody Opinion opinion){
        opinionService.actualizarOpinion(opinion);
    }

    @DeleteMapping("/opinions/eliminar/{id}")
    public void eliminarOpinion(@PathVariable("id") int idOpinion) {
        opinionService.eliminarOpinion(idOpinion);
    }

}
