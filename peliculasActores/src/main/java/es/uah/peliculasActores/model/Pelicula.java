package es.uah.peliculasActores.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Peliculas", schema = "peliculasactoresdb")
public class Pelicula {
    //Enlace de las columnas de la base de datos con las variables de un objeto JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPelicula", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "`año`", nullable = false)
    private Integer año;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "pais", nullable = false, length = 45)
    private String pais;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "genero", length = 45)
    private String genero;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;

    @Lob
    @Column(name = "imagen", nullable = false)
    private String imagen;

    // mappedBy = "peliculas": Indica que Pelicula NO es el dueño de la relación.
    @ManyToMany(mappedBy = "peliculas")

    //Para evitar problemas de búsqueda con bucles infinitos
    @JsonIgnoreProperties("peliculas")

    //Conjunto de actores que tiene una pelicula
    private List<Actor> actores;

    //Creación de los Getter y los Setter de todas las variables
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    //Modificación de equals y hashcode para que tenga en cuenta el id
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //Creación de los métodos que añaden y eliminan a un actor de una pelicula
    public void addActor(Actor actor) {
        if (actor != null) {
            getActores().add(actor);
        }
    }

    public void removeActor(Actor actor) {
        if (actor != null) {
            getActores().remove(actor);
        }
    }


}