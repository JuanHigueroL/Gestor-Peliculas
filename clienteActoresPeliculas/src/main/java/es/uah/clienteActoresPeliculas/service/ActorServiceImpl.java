package es.uah.clienteActoresPeliculas.service;

import es.uah.clienteActoresPeliculas.client.ActorClient;
import es.uah.clienteActoresPeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ActorServiceImpl implements IActorService {

    private final ActorClient actorClient;

    @Autowired
    public ActorServiceImpl(ActorClient actorClient) {
        this.actorClient = actorClient;
    }

    @Override
    public Page<Actor> buscarTodos(Pageable pageable) {
        List<Actor> lista =actorClient.buscarTodos();
        return toPage(lista, pageable);
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        return actorClient.buscarActorPorId(idActor);
    }

    @Override
    public Page<Actor> buscarActoresPorNombre(String nombre, Pageable pageable) {
        List<Actor> lista = actorClient.buscarActoresPorNombre(nombre);
        return toPage(lista, pageable);
    }

    @Override
    public Page<Actor> buscarActoresPorPais(String pais, Pageable pageable) {
        List<Actor> lista = actorClient.buscarActoresPorPais(pais);
        return toPage(lista, pageable);
    }

    @Override
    public void guardarActor(Actor actor) {
        actorClient.guardarActor(actor);
    }

    @Override
    public void eliminarActor(Integer idActor) {
        actorClient.eliminarActor(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        if(actor==null || actor.getId()==null){
            throw new IllegalArgumentException("El actor y el id no pueden ser nulos.");
        }
        actorClient.actualizarActor(actor);

    }

    @Override
    public void añadirPelicula(Integer idActor, Integer idPelicula) {
        actorClient.añadirPelicula(idActor, idPelicula);
    }

    private Page<Actor> toPage(List<Actor> lista, Pageable pageable) {
        if (lista == null || lista.isEmpty()) {
            return Page.empty(pageable);
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Actor> subList;
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
