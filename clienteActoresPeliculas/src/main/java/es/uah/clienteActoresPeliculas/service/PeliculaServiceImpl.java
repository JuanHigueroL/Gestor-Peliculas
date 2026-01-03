package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.client.ActorClient;
import es.uah.clienteActoresPeliculas.client.PeliculaClient;
import es.uah.clienteActoresPeliculas.model.Actor;
import es.uah.clienteActoresPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    private final PeliculaClient peliculaClient;

    @Autowired
    public PeliculaServiceImpl(PeliculaClient peliculaClient, ActorClient actorClient) {
        this.peliculaClient = peliculaClient;
    }

    @Override
    public Page<Pelicula> buscarTodos(Pageable pageable) {
        List<Pelicula> lista = peliculaClient.buscarTodos();
        return toPage(lista, pageable);
    }

    @Override
    public List<Pelicula> buscarTodosLista() {
        return peliculaClient.buscarTodos();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        return peliculaClient.buscarPeliculaPorId(idPelicula);
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorTitulo(String titulo, Pageable pageable) {
        List<Pelicula> lista = peliculaClient.buscarPeliculasPorTitulo(titulo);
        return toPage(lista, pageable);
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorGenero(String genero, Pageable pageable) {
        List<Pelicula> lista = peliculaClient.buscarPeliculasPorGenero(genero);
        return toPage(lista, pageable);
    }

    @Override
    public Page<Pelicula> buscarPeliculasPorActor(Integer idActor, Pageable pageable) {
        List<Pelicula> lista = peliculaClient.buscarPeliculasPorActor(idActor);
        return toPage(lista, pageable);
    }

    @Override
    public List<Actor> buscarActoresDePelicula(Integer idPelicula) {
        return peliculaClient.buscarActoresDePelicula(idPelicula);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculaClient.guardarPelicula(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {
        if (idPelicula == null) {
            throw new IllegalArgumentException("El id no puede ser nulo.");
        }
        peliculaClient.eliminarPelicula(idPelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        if (pelicula == null||pelicula.getId()==null) {
            throw new IllegalArgumentException("La pelicula o su id no pueden ser nulos.");
        }
        peliculaClient.actualizarPelicula(pelicula);
    }
    //Función auxiliar para convertir List en una Page
    private Page<Pelicula> toPage(List<Pelicula> lista, Pageable pageable) {
        if (lista == null || lista.isEmpty()) {
            return Page.empty(pageable);
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Pelicula> subList;
        if (lista.size() <= startItem) {
            subList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lista.size());
            subList = lista.subList(startItem, toIndex);
        }

        // totalElements es la cantidad total de elementos sin paginar
        return new PageImpl<>(subList, PageRequest.of(currentPage, pageSize), lista.size());
    }
}
