/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relacionesComercialesTest;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.RepresentanteBO;

/**
 *
 * @author user
 */
public class representanteTest {
    private static RepresentanteBO representanteBO;
    private static ArrayList<Representante> listaRepresentantes;
    
    public static void testRepresentanteBO() {
        System.out.println("\ntestRepresentanteBO");
        representanteBO = new RepresentanteBO();

         
        testRepresentanteBOListarPorEmpresa(1);
        testRepresentanteBOListarPorNombre("María Gómez");

    }
    
    private static void testRepresentanteBOListarPorEmpresa(Integer idEmpresa) {
        System.out.println("\ntestRepresentanteBOListarId");
        listaRepresentantes = representanteBO.listarPorEmpresa(idEmpresa);
        for (Representante representante : listaRepresentantes) {
            System.out.println(representante.getIdEmpresa()+ " " + representante.getNombreCompleto());
        }
    }
    
    private static void testRepresentanteBOListarPorNombre(String nombre) {
        System.out.println("\ntestRepresentanteBOListarNombre");
        listaRepresentantes = representanteBO.listarPorNombre(nombre);
        for (Representante representante : listaRepresentantes) {
            System.out.println(representante.getIdEmpresa()+ " " + representante.getNombreCompleto());
        }
    }
    
    
    
}
