/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.RepresentanteDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.RepresentanteDAOImpl;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;

public class RepresentanteBO {
    
    private RepresentanteDAO representanteDAO; 
    
    public RepresentanteBO(){
        this.representanteDAO = new RepresentanteDAOImpl();
    }
    
    public Integer insertar(String dni, String nombreCompleto, String telefono, 
                            String correo, String direccion, 
                            Integer idEmpresa){
        Representante representante = new Representante(null, dni, nombreCompleto, telefono, correo, direccion, idEmpresa);
        return this.representanteDAO.insertar(representante);
    }
    
    public Integer modificar(Integer idPersona, String dni, String nombreCompleto, 
                            String telefono, String correo, String direccion, Integer idEmpresa){
        Representante representante = new Representante(idPersona, dni, nombreCompleto, telefono, correo, direccion, idEmpresa);
        return representanteDAO.modificar(representante);
    }
    
    public Integer eliminar(Integer idPersona){
        Representante representante = new Representante();
        representante.setIdPersona(idPersona);
        return this.representanteDAO.eliminar(representante);
    }
    
    public Representante obtenerPorId(Integer idPersona){
        return this.representanteDAO.obtenerPorId(idPersona);
    }
    
    public ArrayList<Representante> listarTodos(){
        return this.representanteDAO.listarTodos();
    }

}