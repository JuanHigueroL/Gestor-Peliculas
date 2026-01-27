package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.Opinion;

import java.util.List;

public interface IOpinionService {
    List<Opinion> buscarTodos();

    Opinion buscarPorId(int idOpinion);

    Opinion buscarOpinionPorUsuarioYPelicula(int idUsuario, int idPelicula);

    List<Opinion> buscarOpinionPorPelicula(int idPelicula);

    void guardarOpinion(Opinion opinion);

    void actualizarOpinion(Opinion opinion);

    void eliminarOpinion(int idOpinion);
}
