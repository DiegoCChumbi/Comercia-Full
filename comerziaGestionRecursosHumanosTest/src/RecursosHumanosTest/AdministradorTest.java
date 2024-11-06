/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RecursosHumanosTest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.AdministradorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;

/**
 *
 * @author user
 */
public class AdministradorTest {

  private static AdministradorBO administradorBO;
  private static ArrayList<Administrador> listaAdministradores;

  public static void testAdministradorBO() {
    System.out.println("\ntestAdministradorBO");
    administradorBO = new AdministradorBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    testAdministradorBOInsertar(listaId);
    testAdministradorBOListarTodos();
    testAdministradorBOModificar(listaId);
    testAdministradorBOListarTodos();
    testAdministradorBOObtenerPorId(listaId);
    testAdministradorBOEliminar();
  }

  private static void testAdministradorBOEliminar() {
    System.out.println("\ntestAdministradorBOEliminar");
    for (Administrador administrador : listaAdministradores) {
      administradorBO.eliminar(administrador.getIdPersona());
    }
  }

  private static void testAdministradorBOObtenerPorId(
    ArrayList<Integer> listaId
  ) {
    System.out.println("\ntestAdministradorBOObtenerPorId");
    for (Integer id : listaId) {
      Administrador administrador = administradorBO.obtenerPorId(id);
      System.out.println(
        "idAlmacen: " +
        administrador.getIdPersona() +
        " " +
        administrador.getDni() +
        " " +
        administrador.getNombreCompleto() +
        " " +
        administrador.getTelefono() +
        " " +
        administrador.getCorreo() +
        " " +
        administrador.getDireccion() +
        " " +
        administrador.getEstado().toString() +
        " " +
        administrador.getNombreUsuario() +
        " " +
        administrador.getContrasenha() +
        " " +
        administrador.getSalario() +
        " " +
        administrador.getFechaContratacion() +
        " " +
        administrador.getIdAlmacen()
      );
    }
  }

  private static void testAdministradorBOListarTodos() {
    System.out.println("\ntestAdministradorBOListarTodos");
    listaAdministradores = administradorBO.listarTodos();
    for (Administrador administrador : listaAdministradores) {
      System.out.println(
        "idAlmacen: " +
        administrador.getIdPersona() +
        " " +
        administrador.getDni() +
        " " +
        administrador.getNombreCompleto() +
        " " +
        administrador.getTelefono() +
        " " +
        administrador.getCorreo() +
        " " +
        administrador.getDireccion() +
        " " +
        administrador.getEstado().toString() +
        " " +
        administrador.getNombreUsuario() +
        " " +
        administrador.getContrasenha() +
        " " +
        administrador.getSalario() +
        " " +
        administrador.getFechaContratacion() +
        " " +
        administrador.getIdAlmacen()
      );
    }
  }

  private static void testAdministradorBOModificar(ArrayList<Integer> listaId) {
    System.out.println("\ntestAdministradorBOModificar");
    Integer resultado = administradorBO.modificar(
      listaId.get(0),
      "12345678",
      "Juan Pérez",
      "987654321",
      "juan.perez@example.com",
      "Av. Principal 123",
      EstadoEmpleado.INACTIVO,
      "jperez",
      "contrasenha123",
      2500.0,
      new Date(),
      1
    );
    resultado = administradorBO.modificar(
      listaId.get(1),
      "87654321",
      "María Gómez",
      "912345678",
      "maria.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleado.ACTIVO,
      "mgomez",
      "password456",
      10000.0,
      new Date(),
      2
    );
  }

  private static void testAdministradorBOInsertar(ArrayList<Integer> listaId) {
    System.out.println("\ntestAdministradorBOInsertar");
    int resultado;

    resultado = administradorBO.insertar(
      "12345678",
      "Juan Pérez",
      "987654321",
      "juan.perez@example.com",
      "Av. Principal 123",
      EstadoEmpleado.ACTIVO,
      "jperez",
      "contrasenha123",
      2500.0,
      new Date(),
      1
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = administradorBO.insertar(
      "87654321",
      "María Gómez",
      "912345678",
      "maria.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleado.ACTIVO,
      "mgomez",
      "password456",
      3000.0,
      new Date(),
      2
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }
}
