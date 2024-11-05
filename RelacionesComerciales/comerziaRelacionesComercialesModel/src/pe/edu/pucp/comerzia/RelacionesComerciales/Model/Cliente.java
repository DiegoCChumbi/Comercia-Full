package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;

public class Cliente extends Empresa {

  private Date fechaRegistro;
  private Date fechaUltimaCompra;

  public Cliente(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaRegistro,
    Date fechaUltimaCompra
  ) {
    super(nombre, direccion, telefono, email, tipoIndustria);
    this.fechaRegistro = fechaRegistro;
    this.fechaUltimaCompra = fechaUltimaCompra;
  }

  public Cliente(
    Integer idEmpresa,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaRegistro,
    Date fechaUltimaCompra
  ) {
    super(idEmpresa, nombre, direccion, telefono, email, tipoIndustria);
    this.fechaRegistro = fechaRegistro;
    this.fechaUltimaCompra = fechaUltimaCompra;
  }

  public Cliente(Date fechaRegistro, Date fechaUltimaCompra) {
    this.fechaRegistro = fechaRegistro;
    this.fechaUltimaCompra = fechaUltimaCompra;
  }

  // Default constructor
  public Cliente() {
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
