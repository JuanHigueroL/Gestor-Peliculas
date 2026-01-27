package es.uah.clienteActoresPeliculas.model;

public class Opinion {
    private Integer id;
    private User usuario;
    private Integer idPelicula;
    private String opinion;
    private Integer puntuacion;

    public Opinion (Integer id, User usuario, Integer idPelicula, String opinion, Integer puntuacion) {
        this.id = id;
        this.usuario = usuario;
        this.idPelicula = idPelicula;
        this.opinion = opinion;
        this.puntuacion = puntuacion;
    }

    public Opinion() {}

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
}
