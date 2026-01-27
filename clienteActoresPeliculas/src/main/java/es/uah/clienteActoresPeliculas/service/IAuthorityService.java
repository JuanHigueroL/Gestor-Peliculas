package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> buscarTodos();

    Authority buscarPorId(int idAutority);
}
