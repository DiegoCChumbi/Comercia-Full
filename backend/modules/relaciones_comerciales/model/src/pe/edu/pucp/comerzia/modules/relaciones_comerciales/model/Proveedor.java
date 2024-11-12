package pe.edu.pucp.comerzia.modules.relaciones_comerciales.model;

import java.util.Date;

public class Proveedor extends Empresa {

  private Date fecha_afiliacion;
  // TODO: Mover lo de abajo a Clase Empresa
  private String RUC;
  private String razonSocial;
  private Double calificacion;
  private String pais;

  public Proveedor() {
    super();
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
