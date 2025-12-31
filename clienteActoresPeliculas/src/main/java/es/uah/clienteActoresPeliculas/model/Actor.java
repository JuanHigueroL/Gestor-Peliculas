package es.uah.clienteActoresPeliculas.model;

import java.time.LocalDate;

public class Actor {

    private Integer id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String pais;
    private String imagen;

    public Actor(Integer id, String pais, LocalDate fechaNacimiento, String nombre, String imagen) {
        this.id = id;
        this.pais = pais;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.imagen =imagen;
    }

    public Actor() {}

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}