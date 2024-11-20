package pe.edu.pucp.comerzia.modules.gestion_almacen.model;

import java.util.Date;
import pe.edu.pucp.comerzia.core.model.BaseEntity;

public class ProductoAlmacenado extends BaseEntity {

  private Integer id;
  private Integer idAlmacen;
  private Integer idProducto;
  private Date fechaAlmacenado;
  private Integer stockActual;

  public ProductoAlmacenado() {
    this.id = null;
    this.idAlmacen = null;
    this.fechaAlmacenado = null;
    this.stockActual = 0;
    this.idProducto = null;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdAlmacen() {
    return idAlmacen;
  }

  public void setIdAlmacen(Integer idAlmacen) {
    this.idAlmacen = idAlmacen;
  }

  public Date getFechaAlmacenado() {
    return fechaAlmacenado;
  }

  public void setFechaAlmacenado(Date fechaAlmacenado) {
    this.fechaAlmacenado = fechaAlmacenado;
  }

  public Integer getStockActual() {
    return stockActual;
  }

  public void setStockActual(Integer stockActual) {
    this.stockActual = stockActual;
  }

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
  }
}
