package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

public class Empresa {

  private Integer idEmpresa;
  private String nombre;
  private String direccion;
  private String telefono;
  private String email;
  private String tipoIndustria;
  private String tipoEmpresa;

  public Empresa(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria
  ) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.email = email;
    this.tipoIndustria = tipoIndustria;
    this.tipoEmpresa = null;
  }

  public Empresa(
    Integer idEmpresa,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria
  ) {
    this.idEmpresa = idEmpresa;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.email = email;
    this.tipoIndustria = tipoIndustria;
    this.tipoEmpresa = null;
  }

  public Empresa() {
    this.idEmpresa = null;
    this.nombre = null;
    this.direccion = null;
    this.telefono = null;
    this.email = null;
    this.tipoIndustria = null;
  }

  public Integer getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Integer idEmpresa) {
    this.idEmpresa = idEmpresa;
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

  public String getTipoEmpresa() {
    return tipoEmpresa;
  }

  public void setTipoEmpresa(String tipoEmpresa) {
    this.tipoEmpresa = tipoEmpresa;
  }
}
