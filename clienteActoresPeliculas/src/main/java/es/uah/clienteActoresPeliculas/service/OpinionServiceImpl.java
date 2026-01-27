package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.client.OpinionClient;
import es.uah.clienteActoresPeliculas.model.Opinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpinionServiceImpl implements IOpinionService {

    @Autowired
    OpinionClient opinionClient;

    @Override
    public List<Opinion> buscarTodos() {
        return opinionClient.buscarTodos();
    }

    @Override
    public Opinion buscarPorId(int idOpinion) {
        return opinionClient.buscarPorId(idOpinion);
    }

    @Override
    public Opinion buscarOpinionPorUsuarioYPelicula(int idUsuario, int idPelicula) {
        return opinionClient.buscarOpinionPorUsuarioYPelicula(idUsuario, idPelicula);
    }

    @Override
    public List<Opinion> buscarOpinionPorPelicula(int idPelicula) {
        return opinionClient.buscarOpinionPorPelicula(idPelicula);
    }

    @Override
    public void guardarOpinion(Opinion opinion) {
        opinionClient.guardarOpinion(opinion);
    }

    @Override
    public void actualizarOpinion(Opinion opinion) {
        opinionClient.actualizarOpinion(opinion);
    }

    @Override
    public void eliminarOpinion(int idOpinion) {
        opinionClient.eliminarOpinion(idOpinion);
    }
}
