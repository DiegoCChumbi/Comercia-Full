package pe.edu.pucp.comerzia.modules.notificacion.model;

public class Notificacion {

  private Integer id;
  private Integer id_producto;
  private Integer id_almacen;
  private String mensaje;

  public Notificacion() {
    this.id = null;
    this.id_producto = null;
    this.id_almacen = null;
    this.mensaje = null;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdProducto() {
    return id_producto;
  }

  public void setIdProducto(Integer id_producto) {
    this.id_producto = id_producto;
  }

  public Integer getIdAlmacen() {
    return id_almacen;
  }

  public void setIdAlmacen(Integer id_almacen) {
    this.id_almacen = id_almacen;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
