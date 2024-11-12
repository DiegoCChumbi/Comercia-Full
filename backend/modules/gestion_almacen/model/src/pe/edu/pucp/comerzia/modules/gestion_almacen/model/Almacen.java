package pe.edu.pucp.comerzia.modules.gestion_almacen.model;

public class Almacen {

  private Integer id;
  private String nombre;
  private String estado;
  private String descripcion;

  public Almacen() {
    this.id = null;
    this.nombre = null;
    this.estado = null;
    this.descripcion = null;
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

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
