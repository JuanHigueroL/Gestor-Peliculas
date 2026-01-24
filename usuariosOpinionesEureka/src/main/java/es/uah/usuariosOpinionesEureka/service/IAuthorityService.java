package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.model.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> buscarTodos();

    Authority buscarPorId(int idAutority);
}
