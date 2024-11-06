package pe.edu.pucp.comerzia.gestioncomercial.bo;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.daoImp.DocumentoDAOImpl;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

public class DocumentoBO {
    private DocumentoDAO documentoDAO;
    
    public DocumentoBO(){
        this.documentoDAO = new DocumentoDAOImpl();
    }
    
    public Integer insertar(Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen){
        Documento documento = new Documento(idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        return documentoDAO.insertar(documento);
    }

    public Integer modificar(Integer idDocumento, Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen){
        Documento lineaDocumento = new Documento(idDocumento, idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        return documentoDAO.modificar(lineaDocumento);
    }
    
    public Integer eliminar(Integer idDocumento){
        Documento documento = new Documento();
        documento.setIdDocumento(idDocumento);
        return documentoDAO.eliminar(documento);
    }
    
    public ArrayList<Documento> listarTodos() {
        return this.documentoDAO.listarTodos();
    }
    
    public Documento obtenerPorId(Integer idDocumento){
        return this.documentoDAO.obtenerPorId(idDocumento);
    }
    
    public Boolean existeDocumento(Integer idEmpresa, Estado estado, Tipo tipo,Integer idVendedor, Integer idAdministrador,Integer idTrabajadorDeAlmacen){
        Documento documento = new Documento();
        documento.setIdEmpresa(idEmpresa);
        documento.setEstado(estado);
        documento.setTipo(tipo);
        documento.setIdVendedor(idVendedor);
        documento.setIdAdministrador(idAdministrador);
        documento.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
        return this.documentoDAO.existeDocumento(documento);
    }
    
    public ArrayList<Documento> listarPorEmpresa(Integer idEmpresa){
        return this.documentoDAO.listarPorEmpresa(idEmpresa);
    }
}
