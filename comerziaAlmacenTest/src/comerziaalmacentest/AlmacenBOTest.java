/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaalmacentest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.AlmacenBO;

public class AlmacenBOTest {
    private static AlmacenBO almacenBO;
    private static ArrayList<Almacen> listaAlmacenes;
    
    public static void testAlmacenBO() {
        System.out.println("\ntestAlmacenBO");
        almacenBO = new AlmacenBO();

        ArrayList<Integer> listaId = new ArrayList<>();
        testAlmacenBOInsertar(listaId);
        testAlmacenBOListarTodos();
        testAlmacenBOModificar(listaId);
        testAlmacenBOListarTodos();
        testAlmacenBOObtenerPorId(listaId);
        testAlmacenBOEliminar();
    }

    private static void testAlmacenBOEliminar() {
        System.out.println("\ntestAlmacenBOEliminar");
        for (Almacen almacen : listaAlmacenes) {
            almacenBO.eliminar(almacen.getIdAlmacen());        
        }
    }

    private static void testAlmacenBOObtenerPorId(ArrayList<Integer> listaId) {
        System.out.println("\ntestAlmacenBOObtenerPorId");
        for (Integer id : listaId) {
            Almacen almacen = almacenBO.obtenerPorId(id);
            System.out.println("idAlmacen: " + almacen.getIdAlmacen()+ " " + almacen.getEstado()+ " " + almacen.getDescripcion());
        }
    }

    private static void testAlmacenBOListarTodos() {
        System.out.println("\ntestAlmacenBOListarTodos");
        listaAlmacenes = almacenBO.listarTodos();
        for (Almacen almacen : listaAlmacenes) {
            System.out.print(almacen.getIdAlmacen().toString());
            System.out.print(", ");
            System.out.print(almacen.getNombre());
            System.out.print(", ");
            System.out.print(almacen.getEstado());
            System.out.print(", ");
            System.out.println(almacen.getDescripcion());
        }
    }

    private static void testAlmacenBOModificar(ArrayList<Integer> listaId) {
        System.out.println("\ntestAlmacenBOModificar");
        Integer resultado = almacenBO.modificar(listaId.get(0),"almacen1","operativo","elalmacen111");
        resultado = almacenBO.modificar(listaId.get(1),"almacen2","operativo","elalmacen222");
    }

    private static void testAlmacenBOInsertar(ArrayList<Integer> listaId) {
        System.out.println("\ntestAlmacenBOInsertar");
        int resultado;
        
        resultado = almacenBO.insertar("almacen1","operativo","elalmacen1");
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
        
        resultado = almacenBO.insertar("almacen2","operativo","elalmacen2");
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
    }
}
