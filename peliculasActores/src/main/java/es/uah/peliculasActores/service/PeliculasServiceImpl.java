package es.uah.peliculasActores.service;

import es.uah.peliculasActores.dao.IActoresDAO;
import es.uah.peliculasActores.dao.IPeliculasDAO;
import es.uah.peliculasActores.dao.IPeliculasJPA;
import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculasServiceImpl implements IPeliculasService {

    @Autowired
    IPeliculasDAO peliculasDAO;

    @Autowired
    IActoresDAO actoresDAO;

    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasDAO.buscarTodos();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        return peliculasDAO.buscarPeliculaPorId(idPelicula);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        return peliculasDAO.buscarPeliculasPorTitulo(titulo);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        return peliculasDAO.buscarPeliculasPorGenero(genero);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorActor(Integer idActor) {
        return peliculasDAO.buscarPeliculasPorActor(actoresDAO.buscarActorPorId(idActor));
    }

    //Comprueba que la película que queremos guardar no tiene un id o bien sea 0 ese id para convertirlo en Null y guardarlo
    @Override
    public void guardarPelicula(Pelicula pelicula) {
        if (pelicula.getId()==null || pelicula.getId()==0) {
            pelicula.setId(null);
            peliculasDAO.guardarActualizarPelicula(pelicula);
        }
    }

    //Eliminar una película directamente generaría problemas al no eliminar la tabla intermedia y ese pelicula de los actores que han actuado en ella.
    //Es por eso que debemos comprobar que actores contiene, eliminar esa película de los actores que la contengan y, por lo tanto, de la tabla intermedia y a la vez todos los actores que la película contenía.
    @Override
    public void eliminarPelicula(Integer idPelicula) {
        if(peliculasDAO.buscarPeliculaPorId(idPelicula)!=null){
            Pelicula pelicula = peliculasDAO.buscarPeliculaPorId(idPelicula);
            List<Actor>actores = List.copyOf(pelicula.getActores());
            for(Actor a:actores){
                a.removePelicula(pelicula);
            }
            peliculasDAO.eliminarPelicula(idPelicula);
        }
    }

    //Comprueba que al actualizar una película su id exista y no este creando uno nuevo. Modificamos la pelicula que ya tenemos y mandamos esa en lugar de la pelicula que nos han dado para mantener los actores que contiene.
    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        Pelicula peliculaCambiada = peliculasDAO.buscarPeliculaPorId(pelicula.getId());
        if (peliculaCambiada!=null) {
            peliculaCambiada.setTitulo(pelicula.getTitulo());
            peliculaCambiada.setAño(pelicula.getAño());
            peliculaCambiada.setPais(pelicula.getPais());
            peliculaCambiada.setDireccion(pelicula.getDireccion());
            peliculaCambiada.setGenero(pelicula.getGenero());
            peliculaCambiada.setSinopsis(pelicula.getSinopsis());
            peliculaCambiada.setImagen(pelicula.getImagen());
            peliculasDAO.guardarActualizarPelicula(pelicula);
        }
    }
}
