package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.client.UserClient;
import es.uah.clienteActoresPeliculas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserClient userClient;

    // 1. Inyectamos el encriptador de contraseñas de Spring Security
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> buscarTodos() {
        return userClient.buscarTodos();
    }

    @Override
    public User buscarPorId(int id) {
        return userClient.buscarPorId(id);
    }

    @Override
    public User buscarPorUsernameAndPassword(String user, String clave) {
        return userClient.login(user, clave);
    }

    @Override
    public void guardarUser(User user) {
        user.setEnable(true);

        // Mandamos el usuario completo y seguro a la API
        userClient.guardarUser(user);
    }

    @Override
    public void actualizarUser(User user) {
        userClient.actualizarUser(user);
    }

    @Override
    public void eliminarUser(int idUser) {
        userClient.eliminarUser(idUser);
    }
}
