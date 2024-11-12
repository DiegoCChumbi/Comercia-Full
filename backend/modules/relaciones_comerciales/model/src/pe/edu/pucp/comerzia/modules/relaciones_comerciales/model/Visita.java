package pe.edu.pucp.comerzia.modules.relaciones_comerciales.model;

import java.util.Date;

public class Visita {

  private Integer id;
  private Date fecha;
  private Double duracion;
  private Integer idCliente;
  private Integer idVendedor;

  public Visita() {
    this.id = null;
    this.fecha = null;
    this.duracion = null;
    this.idCliente = null;
    this.idVendedor = null;
  }

  // Getters y Setters.
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Double getDuracion() {
    return duracion;
  }

  public void setDuracion(Double duracion) {
    this.duracion = duracion;
  }

  public Integer getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Integer idCliente) {
    this.idCliente = idCliente;
  }

  public Integer getIdVendedor() {
    return idVendedor;
  }

  public void setIdVendedor(Integer idVendedor) {
    this.idVendedor = idVendedor;
  }
}
