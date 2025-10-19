package com.api.gestion.cine.dto.login;

public class LoginDTO {

    private String usuario;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    // * Getters y Setters */

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
