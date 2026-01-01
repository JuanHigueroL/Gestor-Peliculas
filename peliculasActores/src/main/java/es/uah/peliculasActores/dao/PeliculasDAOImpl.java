package es.uah.peliculasActores.dao;

import es.uah.peliculasActores.model.Actor;
import es.uah.peliculasActores.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Implementa la interfaz DAO
@Repository
public class PeliculasDAOImpl implements IPeliculasDAO {

    //Llama al componente peliculasJPA
    @Autowired
    IPeliculasJPA peliculasJPA;

    //Implementa la función de llamada de todas las peliculas
    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasJPA.findAll();
    }

    //Implementa la función de búsqueda por ID de una pelicula, haciendo uso de opcional para comprobar si existe y devolviendo este opcional como una pelicula
    //Es el único que tiene en cuenta algún aspecto más además de conectar con la base de datos, ya que nos evita usar Optional en Service
    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        Optional<Pelicula> optional = peliculasJPA.findById(idPelicula);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    //Implementa la función de búsqueda por título de películas
    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String tituloPelicula) {
        return peliculasJPA.findByTituloContainingIgnoreCase(tituloPelicula);
    }

    //Implementa la función de búsqueda por género de películas
    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        return peliculasJPA.findByGeneroContainingIgnoreCase(genero);
    }

    //Implementa la función de búsqueda por Actor de películas
    @Override
    public List<Pelicula> buscarPeliculasPorActor(Actor actorPelicula) {
        return peliculasJPA.findByActoresContainingIgnoreCase(actorPelicula);
    }

    @Override
    public List<Actor> buscarActoresDePelicula(Integer idPelicula) {
        Optional<Pelicula> optional = peliculasJPA.findById(idPelicula);
        if (optional.isPresent()) {
            Pelicula pelicula = optional.get();
            return List.copyOf(pelicula.getActores());
        }
        return null;
    }

    //Implementa la función para guardar o actualizar una pelicula.
    @Override
    public void guardarActualizarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }

    //Implementa la función para eliminar una pelicula.
    @Override
    public void eliminarPelicula(Integer idPelicula) {
        peliculasJPA.deleteById(idPelicula);
    }


}
