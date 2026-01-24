package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Opinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OpinionDAOImpl implements IOpinionDAO {

    @Autowired
    IOpinionJPA opinionJPA;

    @Override
    public List<Opinion> buscarTodos() {
        return opinionJPA.findAll();
    }

    @Override
    public Opinion buscarPorId(int idOpinion) {
            return opinionJPA.findById(idOpinion).orElse(null);
    }

    @Override
    public Opinion buscarOpinionPorUsuarioYPelicula(int idUsuario, int idPelicula) {
        return opinionJPA.findByUsuario_IdAndIdPelicula(idUsuario, idPelicula).orElse(null);
    }

    @Override
    public List<Opinion> buscarOpinionPorPelicula(int idPelicula) {
        return opinionJPA.findByIdPelicula(idPelicula);
    }

    @Override
    public void guardarActualizarOpinion(Opinion opinion) {
        opinionJPA.save(opinion);
    }

    @Override
    public void eliminarOpinion(int idOpinion) {
        opinionJPA.deleteById(idOpinion);
    }
}
