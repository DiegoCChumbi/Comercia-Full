/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RecursosHumanosTest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.VendedorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

/**
 *
 * @author user
 */
public class VendedorTest {

  private static VendedorBO vendedorBO;
  private static ArrayList<Vendedor> listaVendedores;

  public static void testVendedorBO() {
    System.out.println("\ntestVendedorBO");
    vendedorBO = new VendedorBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    testVendedorBOInsertar(listaId);
    testVendedorBOListarTodos();
    testVendedorBOModificar(listaId);
    testVendedorBOListarTodos();
    testVendedorBOObtenerPorId(listaId);
    testVendedorBOEliminar();
  }

  private static void testVendedorBOEliminar() {
    System.out.println("\ntestTrabajadorBOEliminar");
    for (Vendedor vendedor : listaVendedores) {
      vendedorBO.eliminar(vendedor.getIdEmpleado());
    }
  }

  private static void testVendedorBOObtenerPorId(ArrayList<Integer> listaId) {
    System.out.println("\ntestTrabajadorBOObtenerPorId");
    for (Integer id : listaId) {
      Vendedor vendedor = vendedorBO.obtenerPorId(id);
      System.out.println(
        "idPersona: " +
        vendedor.getIdPersona() +
        " " +
        vendedor.getDni() +
        " " +
        vendedor.getNombreCompleto() +
        " " +
        vendedor.getTelefono() +
        " " +
        vendedor.getCorreo() +
        " " +
        vendedor.getDireccion() +
        " " +
        vendedor.getEstado().toString() +
        " " +
        vendedor.getNombreUsuario() +
        " " +
        vendedor.getContrasenha() +
        " " +
        vendedor.getSalario() +
        " " +
        vendedor.getFechaContratacion() +
        " " +
        vendedor.getIngresosVentas() +
        " " +
        vendedor.getPorcentajeComision()
      );
    }
  }

  private static void testVendedorBOListarTodos() {
    System.out.println("\ntestTrabajadorBOListarTodos");
    listaVendedores = vendedorBO.listarTodos();
    for (Vendedor vendedor : listaVendedores) {
      System.out.println(
        "idPersona: " +
        vendedor.getIdPersona() +
        " " +
        vendedor.getDni() +
        " " +
        vendedor.getNombreCompleto() +
        " " +
        vendedor.getTelefono() +
        " " +
        vendedor.getCorreo() +
        " " +
        vendedor.getDireccion() +
        " " +
        vendedor.getEstado().toString() +
        " " +
        vendedor.getNombreUsuario() +
        " " +
        vendedor.getContrasenha() +
        " " +
        vendedor.getSalario() +
        " " +
        vendedor.getFechaContratacion() +
        " " +
        vendedor.getIngresosVentas() +
        " " +
        vendedor.getPorcentajeComision()
      );
    }
  }

  private static void testVendedorBOModificar(ArrayList<Integer> listaId) {
    System.out.println("\ntestTrabajadorBOModificar");
    Integer resultado = vendedorBO.modificar(
      listaId.get(0),
      "87654321",
      "Roberto García",
      "998877665",
      "roberto.garcia@email.com",
      "Av. Los Jardines 456",
      EstadoEmpleado.ACTIVO,
      "rgarcia",
      "password321",
      3000.50,
      new Date(),
      5000.0,
      0.7283
    );
    resultado = vendedorBO.modificar(
      listaId.get(1),
      "12345678",
      "Mariana Torres",
      "912345678",
      "mariana.torres@email.com",
      "Calle Las Flores 789",
      EstadoEmpleado.INACTIVO,
      "mtorres",
      "pass456",
      2500.75,
      new Date(),
      9500.0,
      0.3457
    );
  }

  private static void testVendedorBOInsertar(ArrayList<Integer> listaId) {
    System.out.println("\ntestTrabajadorBOInsertar");
    int resultado;

    resultado = vendedorBO.insertar(
      "87654321",
      "Roberto García",
      "998877665",
      "roberto.garcia@email.com",
      "Av. Los Jardines 456",
      EstadoEmpleado.ACTIVO,
      "rgarcia",
      "password321",
      3000.50,
      new Date(),
      1000.0,
      0.7283
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = vendedorBO.insertar(
      "12345678",
      "Mariana Torres",
      "912345678",
      "mariana.torres@email.com",
      "Calle Las Flores 789",
      EstadoEmpleado.INACTIVO,
      "mtorres",
      "pass456",
      2500.75,
      new Date(),
      1000.0,
      0.3457
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }
}
