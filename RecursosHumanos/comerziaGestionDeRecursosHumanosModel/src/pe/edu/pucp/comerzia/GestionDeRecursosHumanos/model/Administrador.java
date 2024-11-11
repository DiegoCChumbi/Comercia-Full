package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

public class Administrador extends Empleado {

  private Integer idAlmacen;

  public Administrador() {
    super();
    this.idAlmacen = null;
  }

  public Integer getIdAlmacen() {
    return idAlmacen;
  }

  public void setIdAlmacen(Integer idAlmacen) {
    this.idAlmacen = idAlmacen;
  }
}
