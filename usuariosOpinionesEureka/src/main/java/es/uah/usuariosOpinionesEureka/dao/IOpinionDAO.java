package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Opinion;

import java.util.List;

public interface IOpinionDAO {
    List<Opinion> buscarTodos();

    Opinion buscarPorId(int idOpinion);

    Opinion buscarOpinionPorUsuarioYPelicula(int idUsuario, int idPelicula);

    List<Opinion> buscarOpinionPorPelicula(int idPelicula);

    void guardarActualizarOpinion(Opinion opinion);

    void eliminarOpinion(int idOpinion);
}
