package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

public class Representante extends Persona{

    private Integer idEmpresa;

    public Representante(Integer idPersona, String dni, String nombreCompleto, String telefono, String correo, String direccion, Integer idEmpresa) {
        super(idPersona, dni, nombreCompleto, telefono, correo, direccion);
        this.idEmpresa = idEmpresa;
    }

    // null
    public Representante() {
        this.idEmpresa = null;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

}
