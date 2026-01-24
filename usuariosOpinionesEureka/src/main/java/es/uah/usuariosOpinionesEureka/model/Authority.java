package es.uah.usuariosOpinionesEureka.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authorities", schema = "usuariosopinionesdbsec")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol", nullable = false)
    private Integer id;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Authority authority)) return false;
        return Objects.equals(getId(), authority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}