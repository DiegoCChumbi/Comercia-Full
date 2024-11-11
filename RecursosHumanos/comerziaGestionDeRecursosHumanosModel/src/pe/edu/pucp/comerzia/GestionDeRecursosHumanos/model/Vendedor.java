package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

public class Vendedor extends Empleado {

  private Double ingresosVentas;
  private Double porcentajeComision;

  public Vendedor() {
    super();
    this.ingresosVentas = 0.0;
    this.porcentajeComision = 0.0;
  }

  public Double getIngresosVentas() {
    return ingresosVentas;
  }

  public void setIngresosVentas(Double ingresosVentas) {
    this.ingresosVentas = ingresosVentas;
  }

  public Double getPorcentajeComision() {
    return porcentajeComision;
  }

  public void setPorcentajeComision(Double porcentajeComision) {
    this.porcentajeComision = porcentajeComision;
  }
}
