package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.model.User;

import java.util.List;


public interface IUserService {
    List<User> buscarTodos();

    User buscarPorId(int id);

    User buscarPorUsernameAndPassword(String user, String clave);

    void guardarUser(User user);

    void actualizarUser(User user);

    void eliminarUser(int idUser);
}
