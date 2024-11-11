package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

public class PersonaBO {

  private PersonaDAO<Persona> personaDAO;

  public PersonaBO() {
    this.personaDAO = new PersonaDAO<>();
  }

  public Integer insertar(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion
  ) throws SQLException {
    Persona persona = new Persona();
    persona.setDni(dni);
    persona.setNombre(nombreCompleto);
    persona.setTelefono(telefono);
    persona.setCorreo(correo);
    persona.setDireccion(direccion);
    return this.personaDAO.insert(persona);
  }

  public Integer modificar(
    Integer id,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion
  ) throws SQLException {
    Persona persona = new Persona();
    persona.setId(id);
    persona.setDni(dni);
    persona.setNombre(nombreCompleto);
    persona.setTelefono(telefono);
    persona.setCorreo(correo);
    persona.setDireccion(direccion);

    return this.personaDAO.update(id, persona);
  }

  public Integer eliminar(Persona persona) throws SQLException {
    return personaDAO.delete(persona.getId());
  }

  public ArrayList<Persona> listarTodos() throws SQLException {
    return new ArrayList<>(personaDAO.findAll());
  }

  public Optional<Persona> obtenerPorId(Integer id) throws SQLException {
    return personaDAO.findById(id);
  }
}
