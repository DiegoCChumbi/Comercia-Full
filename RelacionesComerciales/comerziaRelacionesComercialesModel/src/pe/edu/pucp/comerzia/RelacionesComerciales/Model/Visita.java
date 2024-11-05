package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.sql.Time;
import java.util.Date;

public class Visita {

  private Integer idVisita;
  private Date fecha;
  private Double duracion;
  private Integer idCliente;
  private Integer idVendedor;

  // Constructores.
  public Visita(
    Integer idVisita,
    Date fecha,
    Double duracion,
    Integer idCliente,
    Integer idVendedor
  ) {
    this.idVisita = idVisita;
    this.fecha = fecha;
    this.duracion = duracion;
    this.idCliente = idCliente;
    this.idVendedor = idVendedor;
  }

  public Visita(
    Date fecha,
    Double duracion,
    Integer idCliente,
    Integer idVendedor
  ) {
    this.fecha = fecha;
    this.duracion = duracion;
    this.idCliente = idCliente;
    this.idVendedor = idVendedor;
  }

  // null
  public Visita() {
    this.idVisita = null;
    this.fecha = null;
    this.duracion = null;
    this.idCliente = null;
    this.idVendedor = null;
  }

  // Getters y Setters.
  public Integer getIdVisita() {
    return idVisita;
  }

  public void setIdVisita(Integer idVisita) {
    this.idVisita = idVisita;
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
