/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import java.util.Date;

/**
 * s
 *
 * @author chumbi
 */
public class Administrador extends Empleado {

  private Integer idAlmacen;

  public Administrador(
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
    Integer idAlmacen
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
    this.setTipoPersona("ADMINISTRADOR");
    this.idAlmacen = idAlmacen;
  }

  public Administrador(
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
    Integer idAlmacen
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
    this.setTipoPersona("ADMINISTRADOR");
    this.idAlmacen = idAlmacen;
  }

  // null
  public Administrador() {
    super();
    this.setTipoPersona("ADMINISTRADOR");
    this.idAlmacen = null;
  }

  public Integer getIdAlmacen() {
    return idAlmacen;
  }

  public void setIdAlmacen(Integer idAlmacen) {
    this.idAlmacen = idAlmacen;
  }
}
