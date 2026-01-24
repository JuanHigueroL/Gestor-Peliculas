package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.dao.IOpinionDAO;
import es.uah.usuariosOpinionesEureka.model.Opinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements IOpinionService {

    @Autowired
    IOpinionDAO opinionDAO;

    @Override
    public List<Opinion> buscarTodos() {
        return opinionDAO.buscarTodos();
    }

    @Override
    public Opinion buscarPorId(int idOpinion) {
        return opinionDAO.buscarPorId(idOpinion);
    }

    @Override
    public Opinion buscarOpinionPorUsuarioYPelicula(int idUsuario, int idPelicula) {
        return opinionDAO.buscarOpinionPorUsuarioYPelicula(idUsuario, idPelicula);
    }

    @Override
    public List<Opinion> buscarOpinionPorPelicula(int idPelicula) {
        return opinionDAO.buscarOpinionPorPelicula(idPelicula);
    }

    @Override
    public void guardarOpinion(Opinion opinion) {
        if (opinion.getId()==null || opinion.getId()==0) {
            opinion.setId(null);
            opinionDAO.guardarActualizarOpinion(opinion);
        }
    }

    @Override
    public void actualizarOpinion(Opinion opinion) {
        Opinion opinionCambiada = opinionDAO.buscarPorId(opinion.getId());
        if (opinionCambiada != null) {
            opinionDAO.guardarActualizarOpinion(opinion);
        }
    }

    @Override
    public void eliminarOpinion(int idOpinion) {
        opinionDAO.eliminarOpinion(idOpinion);
    }
}
