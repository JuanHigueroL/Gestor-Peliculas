package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.model.User;

import java.util.List;

public interface IUserService {
    List<User> buscarTodos();

    User buscarPorId(int id);

    User buscarPorUsernameAndPassword(String user, String clave);

    User buscarPorUsername(String username);

    void guardarUser(User user);

    void actualizarUser(User user);

    void eliminarUser(int idUser);
}
