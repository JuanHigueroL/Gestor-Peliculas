package es.uah.peliculasActores.service;

import es.uah.peliculasActores.dao.IActoresDAO;
import es.uah.peliculasActores.dao.IPeliculasDAO;
import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActoresServiceImpl implements IActoresService {

    //Llama al componente DAO de actores
    @Autowired
    IActoresDAO actoresDAO;

    @Autowired
    IPeliculasDAO peliculasDAO;

    //Solo llama a buscarTodos
    @Override
    public List<Actor> buscarTodos() {
        return actoresDAO.buscarTodos();
    }

    //Solo llama a buscarActorPorID, ya que los errores que pueden ocasionar Optional se han contemplado en el DAO.
    @Override
    public Actor buscarActorPorId(Integer idActor) {
        return actoresDAO.buscarActorPorId(idActor);
    }

    @Override
    public List<Actor> buscarActoresPorNombre(String nombre) {
        return actoresDAO.buscarActoresPorNombre(nombre);
    }

    @Override
    public List<Actor> buscarActoresPorPais(String pais) {
        return actoresDAO.buscarActoresPorPais(pais);
    }

    @Override
    public List<Pelicula> buscarPeliculasDeActor(Integer idActor) {
        return actoresDAO.buscarPeliculasDeActor(idActor);
    }

    //Comprueba que el Actor que queremos guardar no tiene un id o bien sea 0 ese id para convertirlo en Null y guardarlo
    @Override
    public void guardarActor(Actor actor) {
        if (actor.getId() == null || actor.getId()==0) {
            actor.setId(null);
            actoresDAO.guardarActualizarActor(actor);
        }
    }

    //Eliminar un actor directamente generaría problemas al no eliminar la tabla intermedia y ese actor de las peliculas en las que se encuentra.
    //Es por eso que debemos comprobar que películas contiene, eliminarlas de su lista y, por lo tanto, de la tabla intermedia y a la vez ese actor de las películas en las que se encontraba
    @Override
    public void eliminarActor(Integer idActor) {
        if (actoresDAO.buscarActorPorId(idActor)!=null){
            Actor actor = actoresDAO.buscarActorPorId(idActor);
            List<Pelicula> peliculas = List.copyOf(actor.getPeliculas());
            for(Pelicula p:peliculas){
                actor.removePelicula(p);
            }
            actoresDAO.eliminarActor(idActor);
        }
    }

    //Comprueba que al actualizar un actor su id exista y no este creando uno nuevo. Modificamos el actor que ya tenemos y mandamos ese en lugar del actor que nos han dado para mantener las películas que contiene.
    @Override
    public void actualizarActor(Actor actor) {
        Actor actorCambiado= actoresDAO.buscarActorPorId(actor.getId());
        if (actorCambiado!=null){
            actorCambiado.setNombre(actor.getNombre());
            actorCambiado.setPais(actor.getPais());
            actorCambiado.setFechaNacimiento(actor.getFechaNacimiento());
            actorCambiado.setImagen(actor.getImagen());
            actoresDAO.guardarActualizarActor(actorCambiado);
        }
    }

    //Comprueba que el actor y la película existan, añade la película al actor y actualiza el actor para cambiar la tabla intermedia
    @Override
    public void añadirPelicula(Integer idActor, Integer idPelicula) {
        Actor actorActual= actoresDAO.buscarActorPorId(idActor);
        Pelicula peliculaActual= peliculasDAO.buscarPeliculaPorId(idPelicula);
        if(actorActual!=null && peliculaActual!=null){
            actorActual.addPelicula(peliculaActual);
            actoresDAO.guardarActualizarActor(actorActual);
        }
    }

    @Override
    public void eliminarPelicula(Integer idActor, Integer idPelicula) {
        Actor actorActual = actoresDAO.buscarActorPorId(idActor);
        Pelicula peliculaActual = peliculasDAO.buscarPeliculaPorId(idPelicula);
        if (actorActual != null && peliculaActual != null) {
            actorActual.removePelicula(peliculaActual);
            actoresDAO.guardarActualizarActor(actorActual);
        }
    }


}
