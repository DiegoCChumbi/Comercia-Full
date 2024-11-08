package RecursosHumanosTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public class TrabajadorTest {

  private static TrabajadorDeAlmacenBO trabajadorBO;
  private static ArrayList<TrabajadorDeAlmacen> listaTrabajadores;

  public static void testTrabajadorBO() throws SQLException {
    System.out.println("\ntestTrabajadorBO");
    trabajadorBO = new TrabajadorDeAlmacenBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    testTrabajadorBOInsertar(listaId);
    testTrabajadorBOListarTodos();
    testTrabajadorBOModificar(listaId);
    testTrabajadorBOListarTodos();
    testTrabajadorBOObtenerPorId(listaId);
    testTrabajadorBOEliminar();
  }

  private static void testTrabajadorBOEliminar() throws SQLException {
    System.out.println("\ntestTrabajadorBOEliminar");
    for (TrabajadorDeAlmacen trabajador : listaTrabajadores) {
      trabajadorBO.eliminar(trabajador.getId());
    }
  }

  private static void testTrabajadorBOObtenerPorId(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestTrabajadorBOObtenerPorId");
    for (Integer id : listaId) {
      Optional<TrabajadorDeAlmacen> trabajador = trabajadorBO.obtenerPorId(id);
      if (trabajador.isPresent()) {
        System.out.println(
          "idPersona: " +
          trabajador.get().getId() +
          " " +
          trabajador.get().getDni() +
          " " +
          trabajador.get().getNombre() +
          " " +
          trabajador.get().getTelefono() +
          " " +
          trabajador.get().getCorreo() +
          " " +
          trabajador.get().getDireccion() +
          " " +
          trabajador.get().getEstado().toString() +
          " " +
          trabajador.get().getNombreUsuario() +
          " " +
          trabajador.get().getContrasenha() +
          " " +
          trabajador.get().getSalario() +
          " " +
          trabajador.get().getFechaContratacion() +
          " " +
          trabajador.get().getIdAlmacen()
        );
      }
    }
  }

  private static void testTrabajadorBOListarTodos() throws SQLException {
    System.out.println("\ntestTrabajadorBOListarTodos");
    listaTrabajadores = trabajadorBO.listarTodos();
    for (TrabajadorDeAlmacen trabajador : listaTrabajadores) {
      System.out.println(
        "idPersona: " +
        trabajador.getId() +
        " " +
        trabajador.getDni() +
        " " +
        trabajador.getNombre() +
        " " +
        trabajador.getTelefono() +
        " " +
        trabajador.getCorreo() +
        " " +
        trabajador.getDireccion() +
        " " +
        trabajador.getEstado().toString() +
        " " +
        trabajador.getNombreUsuario() +
        " " +
        trabajador.getContrasenha() +
        " " +
        trabajador.getSalario() +
        " " +
        trabajador.getFechaContratacion() +
        " " +
        trabajador.getIdAlmacen()
      );
    }
  }

  private static void testTrabajadorBOModificar(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestTrabajadorBOModificar");
    Integer resultado = trabajadorBO.modificar(
      listaId.get(0),
      "12345678",
      "Carlos Torres",
      "987654321",
      "2222@example.com",
      "Av. Principal 123",
      EstadoEmpleadoEnum.INACTIVO,
      "ctorres",
      "password789",
      2800.0,
      new Date(),
      1,
      true
    );
    resultado = trabajadorBO.modificar(
      listaId.get(1),
      "87654321",
      "Ana Gómez",
      "912345678",
      "ana.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleadoEnum.ACTIVO,
      "agomez",
      "password456",
      3500.0,
      new Date(),
      1,
      true
    );
  }

  private static void testTrabajadorBOInsertar(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestTrabajadorBOInsertar");
    int resultado;

    resultado = trabajadorBO.insertar(
      "12345678",
      "Carlos Torres",
      "987654321",
      "carlos.torres@example.com",
      "Av. Principal 123",
      EstadoEmpleadoEnum.INACTIVO,
      "ctorres",
      "password789",
      2800.0,
      new Date(),
      1,
      true
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = trabajadorBO.insertar(
      "87654321",
      "Ana Gómez",
      "912345678",
      "ana.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleadoEnum.ACTIVO,
      "agomez",
      "password456",
      3500.0,
      new Date(),
      1,
      false
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }
}
