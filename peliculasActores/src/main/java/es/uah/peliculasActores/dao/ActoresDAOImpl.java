package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Implementa la interfaz DAO
@Repository
public class ActoresDAOImpl implements IActoresDAO {

    //Llama al componente actoresJPA
    @Autowired
    IActoresJPA actoresJPA;


    //Implementa la función de llamada de todos los actores
    @Override
    public List<Actor> buscarTodos() {
        return actoresJPA.findAll();
    }

    //Implementa la función de búsqueda por ID del actor, haciendo uso de opcional para comprobar si existe y devolviendo este opcional como un actor
    //Es el único que tiene en cuenta algún aspecto más además de conectar con la base de datos, ya que nos evita usar Optional en Service
    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    //Implementa la función para guardar o actualizar un actor
    @Override
    public void guardarActualizarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    //Implementa la función para eliminar un actor.
    @Override
    public void eliminarActor(Integer idActor) {
        actoresJPA.deleteById(idActor);
    }



}
