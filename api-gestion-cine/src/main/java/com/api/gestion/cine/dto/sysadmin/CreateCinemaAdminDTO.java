package com.api.gestion.cine.dto.sysadmin;

public class CreateCinemaAdminDTO {
  private int idRol;
  private String nombreCompleto;
  private String tipoUsuario;
  private String usuario;
  private String password;
  private String correo;
  private String telefono;
  private byte[] foto;

  public CreateCinemaAdminDTO() {
  }

  public int getIdRol() {
    return idRol;
  }

  public void setIdRol(int idRol) {
    this.idRol = idRol;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public String getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

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

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }
}