package pe.edu.pucp.comerzia.modules.relaciones_comerciales.model;

import pe.edu.pucp.comerzia.core.model.BaseEntity;

public class Empresa extends BaseEntity {

  private Integer id;
  private String nombre;
  private String direccion;
  private String telefono;
  private String email;
  private String tipoIndustria;

  public Empresa() {
    this.id = null;
    this.nombre = null;
    this.direccion = null;
    this.telefono = null;
    this.email = null;
    this.tipoIndustria = null;
  }

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

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTipoIndustria() {
    return tipoIndustria;
  }

  public void setTipoIndustria(String tipoIndustria) {
    this.tipoIndustria = tipoIndustria;
  }
}
