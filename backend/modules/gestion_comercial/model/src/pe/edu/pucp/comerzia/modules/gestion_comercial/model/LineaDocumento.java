package pe.edu.pucp.comerzia.modules.gestion_comercial.model;

import pe.edu.pucp.comerzia.core.model.BaseEntity;

public class LineaDocumento extends BaseEntity {

  private Integer id;
  private Integer idDocumento;
  private Integer idProducto;
  private Integer cantidad;
  private Double precioUnitario;

  public LineaDocumento() {
    this.id = null;
    this.idDocumento = null;
    this.idProducto = null;
    this.cantidad = null;
    this.precioUnitario = null;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdDocumento() {
    return idDocumento;
  }

  public void setIdDocumento(Integer idDocumento) {
    this.idDocumento = idDocumento;
  }

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }
}
