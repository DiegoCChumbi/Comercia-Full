package pe.edu.pucp.comerzia.modules.recursos_humanos.model;

public class TrabajadorDeAlmacen extends Empleado {

  private Integer idAlmacen;
  private boolean licenciaMontacarga;

  public TrabajadorDeAlmacen() {
    super();
    this.idAlmacen = null;
    this.licenciaMontacarga = false;
  }

  public Integer getIdAlmacen() {
    return idAlmacen;
  }

  public void setIdAlmacen(Integer idAlmacen) {
    this.idAlmacen = idAlmacen;
  }

  public boolean getLicenciaMontacarga() {
    return licenciaMontacarga;
  }

  public void setLicenciaMontacarga(boolean licenciaMontacarga) {
    this.licenciaMontacarga = licenciaMontacarga;
  }
}
