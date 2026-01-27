package es.uah.usuariosOpinionesEureka.service;

import es.uah.usuariosOpinionesEureka.dao.IUserDAO;
import es.uah.usuariosOpinionesEureka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public List<User> buscarTodos() {
        return userDAO.buscarTodos();
    }

    @Override
    public User buscarPorId(int id) {
        return userDAO.buscarPorId(id);
    }

    @Override
    public User buscarPorUsernameAndPassword(String user, String clave) {
        return userDAO.buscarPorUsernameAndPassword(user, clave);
    }

    @Override
    public void guardarUser(User user) {
        if (user.getId() == null || user.getId() == 0) {
            user.setId(null);
            userDAO.guardarActualizarUser(user);
        }
    }

    @Override
    public void actualizarUser(User user) {
        User usuarioCambiado = userDAO.buscarPorId(user.getId());
        if(usuarioCambiado != null){
            userDAO.guardarActualizarUser(user);
        }
    }

    @Override
    public void eliminarUser(int idUser) {
        userDAO.eliminarUser(idUser);
    }
}
