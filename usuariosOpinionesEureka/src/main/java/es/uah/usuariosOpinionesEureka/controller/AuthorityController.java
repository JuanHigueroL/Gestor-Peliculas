package es.uah.usuariosOpinionesEureka.controller;

import es.uah.usuariosOpinionesEureka.dao.IAuthorityDAO;
import es.uah.usuariosOpinionesEureka.model.Authority;
import es.uah.usuariosOpinionesEureka.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    IAuthorityService authorityService;

    @GetMapping("/authorities")
    public List<Authority> buscarTodos() {
        return authorityService.buscarTodos();
    }

    @GetMapping("/authorities/{id}")
    public Authority buscarPorId(@PathVariable("id") int idRol) {
        return authorityService.buscarPorId(idRol);
    }


}
