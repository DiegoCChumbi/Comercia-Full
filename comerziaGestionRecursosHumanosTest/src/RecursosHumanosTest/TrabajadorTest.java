/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RecursosHumanosTest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.AdministradorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

/**
 *
 * @author user
 */
public class TrabajadorTest {
    private static TrabajadorDeAlmacenBO trabajadorBO;
    private static ArrayList<TrabajadorDeAlmacen> listaTrabajadores;
    
    public static void testTrabajadorBO() {
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

    private static void testTrabajadorBOEliminar() {
        System.out.println("\ntestTrabajadorBOEliminar");
        for (TrabajadorDeAlmacen trabajador : listaTrabajadores) {
            trabajadorBO.eliminar(trabajador.getIdEmpleado());        
        }
    }

    private static void testTrabajadorBOObtenerPorId(ArrayList<Integer> listaId) {
        System.out.println("\ntestTrabajadorBOObtenerPorId");
        for (Integer id : listaId) {
            TrabajadorDeAlmacen trabajador = trabajadorBO.obtenerPorId(id);
            System.out.println("idPersona: " + trabajador.getIdPersona()+ " " + trabajador.getDni() + " " + trabajador.getNombreCompleto() + " " + trabajador.getTelefono() + " " + trabajador.getCorreo() + " " + trabajador.getDireccion() + " " + trabajador.getEstado().toString() + " " + trabajador.getNombreUsuario() + " " + trabajador.getContrasenha() + " " + trabajador.getSalario() + " " + trabajador.getFechaContratacion() + " " + trabajador.getIdAlmacen());
        }
    }

    private static void testTrabajadorBOListarTodos() {
        System.out.println("\ntestTrabajadorBOListarTodos");
        listaTrabajadores = trabajadorBO.listarTodos();
        for (TrabajadorDeAlmacen trabajador : listaTrabajadores) {
            System.out.println("idPersona: " + trabajador.getIdPersona()+ " " + trabajador.getDni() + " " + trabajador.getNombreCompleto() + " " + trabajador.getTelefono() + " " + trabajador.getCorreo() + " " + trabajador.getDireccion() + " " + trabajador.getEstado().toString() + " " + trabajador.getNombreUsuario() + " " + trabajador.getContrasenha() + " " + trabajador.getSalario() + " " + trabajador.getFechaContratacion() + " " + trabajador.getIdAlmacen());
        }
    }

    private static void testTrabajadorBOModificar(ArrayList<Integer> listaId) {
        System.out.println("\ntestTrabajadorBOModificar");
        Integer resultado = trabajadorBO.modificar(listaId.get(0),"12345678", "Carlos Torres", "987654321", "2222@example.com", "Av. Principal 123", EstadoEmpleado.INACTIVO, "ctorres", "password789", 2800.0, new Date(), 1, true);
        resultado = trabajadorBO.modificar(listaId.get(1),"87654321", "Ana Gómez", "912345678", "ana.gomez@example.com", "Calle Secundaria 456", EstadoEmpleado.ACTIVO, "agomez", "password456", 3500.0, new Date(), 1, true);
    }

    private static void testTrabajadorBOInsertar(ArrayList<Integer> listaId) {
        System.out.println("\ntestTrabajadorBOInsertar");
        int resultado;
        
        resultado = trabajadorBO.insertar("12345678", "Carlos Torres", "987654321", "carlos.torres@example.com", "Av. Principal 123", EstadoEmpleado.INACTIVO, "ctorres", "password789", 2800.0, new Date(), 1, true);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
        
        resultado = trabajadorBO.insertar("87654321", "Ana Gómez", "912345678", "ana.gomez@example.com", "Calle Secundaria 456", EstadoEmpleado.ACTIVO, "agomez", "password456", 3500.0, new Date(), 1, false);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
    }
}
