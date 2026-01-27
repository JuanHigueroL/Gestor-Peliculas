package es.uah.clienteActoresPeliculas.model;

public class Authority {
    private Integer id;
    private String authority;

    public Authority (Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Authority() {}

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
}
