package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorityDAOImpl implements IAuthorityDAO {

    @Autowired
    IAuthorityJPA authorityJPA;

    @Override
    public List<Authority> buscarTodos() {
        return authorityJPA.findAll();
    }

    @Override
    public Authority buscarPorId(int idAutority) {
        return authorityJPA.findById(idAutority).orElse(null);
    }
}
