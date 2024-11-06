package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;

public class Proveedor extends Empresa {

  private Date fecha_afiliacion;
  private String RUC;
  private String razonSocial;
  private Double calificacion;
  private String pais;

  public Proveedor(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fecha_afiliacion,
    String RUC,
    String razonSocial,
    Double calificacion,
    String pais
  ) {
    super(nombre, direccion, telefono, email, tipoIndustria);
    this.setTipoEmpresa("PROVEEDOR");
    this.fecha_afiliacion = fecha_afiliacion;
    this.RUC = RUC;
    this.razonSocial = razonSocial;
    this.calificacion = calificacion;
    this.pais = pais;
  }

  public Proveedor(
    Integer idEmpresa,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fecha_afiliacion,
    String RUC,
    String razonSocial,
    Double calificacion,
    String pais
  ) {
    super(idEmpresa, nombre, direccion, telefono, email, tipoIndustria);
    this.setTipoEmpresa("PROVEEDOR");
    this.fecha_afiliacion = fecha_afiliacion;
    this.RUC = RUC;
    this.razonSocial = razonSocial;
    this.calificacion = calificacion;
    this.pais = pais;
  }

  public Proveedor() {
    super();
    this.setTipoEmpresa("PROVEEDOR");
    this.fecha_afiliacion = null;
    this.RUC = null;
    this.razonSocial = null;
    this.calificacion = null;
    this.pais = null;
  }

  public Date getFecha_afiliacion() {
    return fecha_afiliacion;
  }

  public void setFecha_afiliacion(Date fecha_afiliacion) {
    this.fecha_afiliacion = fecha_afiliacion;
  }

  public String getRUC() {
    return RUC;
  }

  public void setRUC(String RUC) {
    this.RUC = RUC;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public Double getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(Double calificacion) {
    this.calificacion = calificacion;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }
}
