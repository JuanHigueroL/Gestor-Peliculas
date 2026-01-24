package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Authority;

import java.util.List;

public interface IAuthorityDAO {
    List<Authority> buscarTodos();

    Authority buscarPorId(int idAutority);
}
