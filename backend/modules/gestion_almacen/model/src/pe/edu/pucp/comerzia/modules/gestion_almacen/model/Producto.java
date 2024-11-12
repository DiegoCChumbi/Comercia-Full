package pe.edu.pucp.comerzia.modules.gestion_almacen.model;

public class Producto {

  private Integer id;
  private String nombre;
  private Double precio;
  private Integer stockMinimo;

  public Producto() {
    this.id = null;
    this.nombre = null;
    this.precio = null;
    this.stockMinimo = 0;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public Integer getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(Integer stockMinimo) {
    this.stockMinimo = stockMinimo;
  }
}
