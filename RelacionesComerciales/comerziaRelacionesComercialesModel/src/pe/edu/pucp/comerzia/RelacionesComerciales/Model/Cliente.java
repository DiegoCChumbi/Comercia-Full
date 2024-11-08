package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;

public class Cliente extends Empresa {

  private Date fechaRegistro;
  private Date fechaUltimaCompra;

  public Cliente() {
    super();
    this.fechaRegistro = null;
    this.fechaUltimaCompra = null;
  }

  public Date getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(Date fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public Date getFechaUltimaCompra() {
    return fechaUltimaCompra;
  }

  public void setFechaUltimaCompra(Date fechaUltimaCompra) {
    this.fechaUltimaCompra = fechaUltimaCompra;
  }
}
