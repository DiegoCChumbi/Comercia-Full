package pe.edu.pucp.comerzia.modules.recursos_humanos.model;

import java.util.Date;

public class Empleado extends Persona {

  private EstadoEmpleadoEnum estado;
  private String nombreUsuario;
  private String contrasenha;
  private Double salario;
  private Date fechaContratacion;

  public Empleado() {
    super();
  }

  public EstadoEmpleadoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoEmpleadoEnum estado) {
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

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  public Date getFechaContratacion() {
    return fechaContratacion;
  }

  public void setFechaContratacion(Date fechaContratacion) {
    this.fechaContratacion = fechaContratacion;
  }
}
