package pe.edu.pucp.comerzia.modules.recursos_humanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Persona;

public class PersonaBO {

  private PersonaDAO<Persona> personaDAO;

  public PersonaBO() {
    this.personaDAO = PersonaDAO.getPersonaInstance();
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
