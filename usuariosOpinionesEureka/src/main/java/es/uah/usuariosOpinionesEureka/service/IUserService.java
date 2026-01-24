package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.model.User;

import java.util.List;

public interface IUserService {
    List<User> buscarTodos();

    User buscarPorId(int id);

    void guardarUser(User user);

    void actualizarUser(User user);

    void eliminarUser(int idUser);
}
