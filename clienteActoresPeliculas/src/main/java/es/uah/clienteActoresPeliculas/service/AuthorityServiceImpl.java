package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.client.AuthorityClient;
import es.uah.clienteActoresPeliculas.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    AuthorityClient authorityClient;

    @Override
    public List<Authority> buscarTodos() {
        return authorityClient.buscarTodos();
    }

    @Override
    public Authority buscarPorId(int idAutority) {
        return authorityClient.buscarPorId(idAutority);
    }
}
