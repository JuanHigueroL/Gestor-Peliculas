package es.uah.clienteActoresPeliculas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pelicula {
    @JsonProperty("id")
    private Integer id;

    private String titulo;

    @JsonProperty("año")
    private Integer año;

    private Integer duracion;

    private String pais;

    private String direccion;

    private String genero;

    private String sinopsis;

    private String imagen;

    public Pelicula(Integer id, String titulo, Integer año, Integer duracion, String pais, String direccion, String genero, String sinopsis, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.pais = pais;
        this.direccion = direccion;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }

    public Pelicula() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
