package es.uah.clienteActoresPeliculas.model;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String correo;
    private Boolean enable;
    private Authority rol;

    public User(Integer id, String username, String password, String correo, Boolean enable, Authority rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.enable = enable;
        this.rol = rol;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Authority getRol() {
        return rol;
    }

    public void setRol(Authority rol) {
        this.rol = rol;
    }
}
