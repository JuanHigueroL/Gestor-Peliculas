package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityJPA extends JpaRepository<Authority, Integer> {
}