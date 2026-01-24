package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.dao.IAuthorityDAO;
import es.uah.usuariosOpinionesEureka.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    IAuthorityDAO authorityDAO;

    @Override
    public List<Authority> buscarTodos() {
        return authorityDAO.buscarTodos();
    }

    @Override
    public Authority buscarPorId(int idAutority) {
        return authorityDAO.buscarPorId(idAutority);
    }
}
