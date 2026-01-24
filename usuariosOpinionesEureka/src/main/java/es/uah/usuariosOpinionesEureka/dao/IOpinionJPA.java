package es.uah.usuariosOpinionesEureka.dao;

import es.uah.usuariosOpinionesEureka.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOpinionJPA extends JpaRepository<Opinion, Integer> {

    Optional<Opinion> findByUsuario_IdAndIdPelicula(Integer idUsuario, Integer idPelicula);

    List<Opinion> findByIdPelicula(Integer idPelicula);

}