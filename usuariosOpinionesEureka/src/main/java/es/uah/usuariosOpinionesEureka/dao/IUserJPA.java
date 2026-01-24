package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJPA extends JpaRepository<User, Integer> {
}