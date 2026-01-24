package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    IUserJPA userJPA;

    @Override
    public List<User> buscarTodos() {
        return userJPA.findAll();
    }

    @Override
    public User buscarPorId(int id) {
        return userJPA.findById(id).orElse(null);
    }

    @Override
    public void guardarActualizarUser(User user) {
        userJPA.save(user);
    }

    @Override
    public void eliminarUser(int idUser) {
        userJPA.deleteById(idUser);
    }
}
