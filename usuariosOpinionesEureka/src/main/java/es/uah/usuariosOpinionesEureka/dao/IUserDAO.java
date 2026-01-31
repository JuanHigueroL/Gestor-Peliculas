package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User> buscarTodos();

    User buscarPorId(int id);

    User buscarPorUsernameAndPassword(String user, String clave);

    User buscarPorUsername(String username);

    void guardarActualizarUser(User user);

    void eliminarUser(int idUser);

}
