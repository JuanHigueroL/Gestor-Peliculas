package es.uah.usuariosOpinionesEureka.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "opiniones", schema = "usuariosopinionesdbsec")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOpinion", nullable = false)
    private Integer id;

    // FetchType LAZY es para cargar los datos solo cuando se necesitan
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idUsuario", nullable = false)
    private User usuario;

    @Column(name = "idPelicula", nullable = false)
    private Integer idPelicula;

    @Lob
    @Column(name = "opinion")
    private String opinion;

    @Column(name = "puntuacion", nullable = false)
    private Integer puntuacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Opinion opiniones)) return false;
        return Objects.equals(getId(), opiniones.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}