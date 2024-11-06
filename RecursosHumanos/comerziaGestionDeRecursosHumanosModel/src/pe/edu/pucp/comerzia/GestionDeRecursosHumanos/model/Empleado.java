package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import java.util.Date;

public class Empleado extends Persona {

  private EstadoEmpleado estado;
  private String nombreUsuario;
  private String contrasenha;
  private double salario;
  private Date fechaContratacion;

  public Empleado(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    Date fechaContratacion
  ) {
    super(dni, nombreCompleto, telefono, correo, direccion);
    this.setTipoPersona("EMPLEADO");
    this.estado = estado;
    this.nombreUsuario = nombreUsuario;
    this.contrasenha = contrasenha;
    this.salario = salario;
    this.fechaContratacion = fechaContratacion;
  }

  public Empleado(
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
    Date fechaContratacion
  ) {
    super(idPersona, dni, nombreCompleto, telefono, correo, direccion);
    this.setTipoPersona("EMPLEADO");
    this.estado = estado;
    this.nombreUsuario = nombreUsuario;
    this.contrasenha = contrasenha;
    this.salario = salario;
    this.fechaContratacion = fechaContratacion;
  }

  // nulls
  public Empleado() {
    super();
    this.setTipoPersona("EMPLEADO"); // Ensure tipoPersona is set
  }

  public EstadoEmpleado getEstado() {
    return estado;
  }

  public void setEstado(EstadoEmpleado estado) {
    this.estado = estado;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getContrasenha() {
    return contrasenha;
  }

  public void setContrasenha(String contrasenha) {
    this.contrasenha = contrasenha;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public Date getFechaContratacion() {
    return fechaContratacion;
  }

  public void setFechaContratacion(Date fechaContratacion) {
    this.fechaContratacion = fechaContratacion;
  }
}
