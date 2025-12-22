package es.uah.peliculasActores.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Actores", schema = "peliculasactoresdb")
public class Actor {
    //Enlace de las columnas de la base de datos con las variables de un objeto JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActor", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "pais", nullable = false, length = 45)
    private String pais;

    // Carga todos los datos de golpe ahora mismo
    // Usar EAGER es útil para evitar errores de conexión cuando estás empezando,
    // pero en aplicaciones grandes se usa LAZY para mejorar el rendimiento.
    @ManyToMany(fetch = FetchType.EAGER)

    //Enlaza las variables de la tabla Peliculas y Actores con los dos objetos JPA
    //Uniendo los respectivos ID
    @JoinTable(name = "Peliculas_y_actores", joinColumns = {
            @JoinColumn(name = "Actores_idActor", referencedColumnName = "idActor")}, inverseJoinColumns = {
            @JoinColumn(name = "Peliculas_idPelicula", referencedColumnName = "idPelicula")})

    //Para evitar problemas de búsqueda con bucles infinitos
    @JsonIgnoreProperties("actores")

    //Conjunto de peliculas que tiene un actor
    private List<Pelicula> peliculas;

    //Creación de los Getter y los Setter de todas las variables
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    //Modificación de equals y hashcode para que tenga en cuenta el id
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //Creación de los métodos que añaden y eliminan una pelicula a un actor y viceversa,
    //es decir que añaden y eliminan también ese actor a la pelicula
    public void addPelicula(Pelicula pelicula) {
        if (pelicula != null && !this.peliculas.contains(pelicula)) {
            getPeliculas().add(pelicula);
            pelicula.addActor(this);
        }
    }

    public void removePelicula(Pelicula pelicula) {
        if (pelicula != null) {
            getPeliculas().remove(pelicula);
            pelicula.removeActor(this);
        }
    }


}