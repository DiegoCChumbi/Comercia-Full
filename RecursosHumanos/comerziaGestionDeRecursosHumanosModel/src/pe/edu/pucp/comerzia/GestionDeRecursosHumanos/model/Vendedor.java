package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import java.util.Date;

public class Vendedor extends Empleado {

  private double ingresosVentas;
  private double porcentajeComision;

  public Vendedor(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    Date fechaContratacion,
    double ingresosVentas,
    double porcentajeComision
  ) {
    super(
      dni,
      nombreCompleto,
      telefono,
      correo,
      direccion,
      estado,
      nombreUsuario,
      contrasenha,
      salario,
      fechaContratacion
    );
    this.setTipoPersona("VENDEDOR");
    this.ingresosVentas = ingresosVentas;
    this.porcentajeComision = porcentajeComision;
  }

  public Vendedor(
    Integer idPersona,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    Date fechaContratacion,
    double ingresosVentas,
    double porcentajeComision
  ) {
    super(
      idPersona,
      dni,
      nombreCompleto,
      telefono,
      correo,
      direccion,
      estado,
      nombreUsuario,
      contrasenha,
      salario,
      fechaContratacion
    );
    this.setTipoPersona("VENDEDOR");
    this.ingresosVentas = ingresosVentas;
    this.porcentajeComision = porcentajeComision;
  }

  // null
  public Vendedor() {
    super();
    this.setTipoPersona("VENDEDOR");
    this.ingresosVentas = 0.0;
    this.porcentajeComision = 0.0;
  }

  public double getIngresosVentas() {
    return ingresosVentas;
  }

  public void setIngresosVentas(double ingresosVentas) {
    this.ingresosVentas = ingresosVentas;
  }

  public double getPorcentajeComision() {
    return porcentajeComision;
  }

  public void setPorcentajeComision(double porcentajeComision) {
    this.porcentajeComision = porcentajeComision;
  }
}
