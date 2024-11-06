/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import java.util.Date;

/**
 *
 * @author chumbi
 */
public class TrabajadorDeAlmacen extends Empleado {

  private Integer idAlmacen;
  private boolean licenciaMontacarga;

  public TrabajadorDeAlmacen(
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
    Integer idAlmacen,
    boolean licenciaMontacarga
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
    this.setTipoPersona("TRABAJADOR_DE_ALMACEN");

    this.idAlmacen = idAlmacen;
    this.licenciaMontacarga = licenciaMontacarga;
  }

  public TrabajadorDeAlmacen(
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
    Integer idAlmacen,
    boolean licenciaMontacarga
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
    this.setTipoPersona("TRABAJADOR_DE_ALMACEN");
    this.idAlmacen = idAlmacen;
    this.licenciaMontacarga = licenciaMontacarga;
  }

  // null
  public TrabajadorDeAlmacen() {
    super();
    this.setTipoPersona("TRABAJADOR_DE_ALMACEN");
    this.idAlmacen = null;
    this.licenciaMontacarga = false;
  }

  public Integer getIdAlmacen() {
    return idAlmacen;
  }

  public void setIdAlmacen(Integer idAlmacen) {
    this.idAlmacen = idAlmacen;
  }

  public boolean isLicenciaMontacarga() {
    return licenciaMontacarga;
  }

  public void setLicenciaMontacarga(boolean licenciaMontacarga) {
    this.licenciaMontacarga = licenciaMontacarga;
  }
}
