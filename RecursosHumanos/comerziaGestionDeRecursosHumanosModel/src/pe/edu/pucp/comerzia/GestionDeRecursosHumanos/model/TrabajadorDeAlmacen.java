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

  private Integer idTrabajadorDeAlmacen;

  // private Integer idEmpleado;
  // private Empleado empleado;
  private Integer idAlmacen;
  // private Almacen almacenDeTrabajo;
  private boolean licenciaMontacarga;

  public TrabajadorDeAlmacen(
    Integer idTrabajadorDeAlmacen,
    Integer idEmpleado,
    boolean licenciaMontacarga,
    Integer idAlmacen
  ) {
    this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;

    //this.idEmpleado = idEmpleado;
    this.idAlmacen = idAlmacen;

    this.licenciaMontacarga = licenciaMontacarga;
  }

  public TrabajadorDeAlmacen(Integer idAlmacen, boolean licenciaMontacarga) {
    //this.idEmpleado = idEmpleado;
    this.idAlmacen = idAlmacen;
    this.licenciaMontacarga = licenciaMontacarga;
  }

  public TrabajadorDeAlmacen(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    boolean licenciaMontacarga,
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
    //this.idEmpleado = idEmpleado;
    this.idAlmacen = idAlmacen;
    this.licenciaMontacarga = licenciaMontacarga;
  }

  // null
  public TrabajadorDeAlmacen() {
    this.idTrabajadorDeAlmacen = null;
    //this.idEmpleado = null;
    this.idAlmacen = null;
    // this.licenciaMontacarga = false;
  }

  public Integer getIdTrabajadorDeAlmacen() {
    return idTrabajadorDeAlmacen;
  }

  public void setIdTrabajadorDeAlmacen(Integer idTrabajadorDeAlmacen) {
    this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
  }

  //    public Integer getIdEmpleado() {
  //        return idEmpleado;
  //    }
  //
  //    public void setIdEmpleado(Integer idEmpleado) {
  //        this.idEmpleado = idEmpleado;
  //    }

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
