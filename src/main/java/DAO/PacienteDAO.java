package DAO;

import TransferObject.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luis_cvqz
 */
public class PacienteDAO {

	public boolean credencialesValidas(String correo, String contrasena) throws SQLException{
		boolean sonValidas;

		String consultaSQL = "SELECT * FROM Paciente WHERE correo = ? AND contrasena=(SHA2(?,256))";

		PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
		consulta.setString(1, correo);
		consulta.setString(2, contrasena);

		ResultSet resultado = consulta.executeQuery();
		sonValidas = resultado.next();

		consulta.close();
		resultado.close();
		ConexionBaseDatos.desconectar();

		return sonValidas;
	}
    
	/**
	 * Obtiene todos los pacientes de la base de datos
	 * @return lista de todos los pacientes registrados en la base de datos
	 * @throws SQLException en caso de que falle la conexión con la base de datos
	 */
	public ArrayList<Paciente> obtenerPacientes() throws SQLException {
		ArrayList<Paciente> listaPacientes = new ArrayList<>();
		String consultaSQL = "SELECT id, nombre, apellido, genero, fecha_nacimiento, correo FROM Paciente;";

		PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
		ResultSet resultado = consulta.executeQuery();
		while (resultado.next()) {
			listaPacientes.add(convertirResultSetPaciente(resultado));
		}

        consulta.close();
        resultado.close();
        ConexionBaseDatos.desconectar();

		return listaPacientes;
	}

	/**
	 * Obtiene un paciente por su correo electrónico
	 * @param correo del paciente que se va a obtener
	 * @return objeto Paciente con los datos del paciente
	 * @throws SQLException en caso de que falle la conexión con la base de datos
	 */
	public Paciente obtenerPacientePorCorreo(String correo) throws SQLException {
		Paciente paciente = new Paciente();
		String consultaSQL = "SELECT id, nombre, apellido, genero, fecha_nacimiento, correo FROM Paciente WHERE correo = ?;";

		PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
		consulta.setString(1, correo);
		ResultSet resultado = consulta.executeQuery();
		if (resultado.next()) {
			paciente = convertirResultSetPaciente(resultado);
		}

		consulta.close();
		resultado.close();
		ConexionBaseDatos.desconectar();

		return paciente;
	}

	/**
	 * Agrega un paciente a la base de datos
	 * @param paciente objeto Paciente con los datos del paciente que se va a agregar
	 * @return 1 si se agrega correctamente, 0 si no se agrega
	 * @throws SQLException en caso de que falle la conexión con la base de datos
	 */
	public int agregarPaciente(Paciente paciente) throws SQLException {
		int resultado = 0;
		String consultaSQL = "INSERT INTO paciente (nombre, apellido, genero, fecha_nacimiento, correo, contrasena) VALUES (?, ?, ?, ?, ?, SHA2(?,256));";

		PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
		consulta.setString(1, paciente.getNombre());
		consulta.setString(2, paciente.getApellido());
		consulta.setString(3, paciente.getGenero());
		consulta.setString(4, paciente.getFechaNacimiento().toString());
		consulta.setString(5, paciente.getCorreo());
		consulta.setString(6, paciente.getContrasena());

		resultado = consulta.executeUpdate();
		consulta.close();
		ConexionBaseDatos.desconectar();

		return resultado;
	}

	/**
	 * Modifica los datos personales de un paciente
	 * @param paciente objeto Paciente con los datos del paciente que se van a modificar
	 * @return 1 si se modifica correctamente, 0 si no se modifica
	 * @throws SQLException en caso de que falle la conexión con la base de datos
	 */
	public int modificarDatosPersonalesPaciente(Paciente paciente) throws SQLException {
		int resultado = 0;
		String consultaSQL = "UPDATE paciente SET nombre = ?, apellido = ?, genero = ?, fecha_nacimiento = ? WHERE correo = ?;";

		PreparedStatement consulta = ConexionBaseDatos.getInstancia().prepareStatement(consultaSQL);
		consulta.setString(1, paciente.getNombre());
		consulta.setString(2, paciente.getApellido());
		consulta.setString(3, paciente.getGenero());
		consulta.setString(4, paciente.getFechaNacimiento().toString());
		consulta.setString(5, paciente.getCorreo());

		resultado = consulta.executeUpdate();
		consulta.close();
		ConexionBaseDatos.desconectar();

		return resultado;
	}

	/**
	 * Convierte un ResultSet en un objeto Paciente
	 * @param resultado ResultSet con los datos del paciente
	 * @return objeto Paciente con los datos del ResultSet
	 * @throws SQLException en caso de que falle la conexión con la base de datos
	 */
	public Paciente convertirResultSetPaciente(ResultSet resultado) throws SQLException {
		Paciente paciente = new Paciente();

		paciente.setId(resultado.getInt("id"));
		paciente.setNombre(resultado.getString("nombre"));
		paciente.setApellido(resultado.getString("apellido"));
		paciente.setFechaNacimiento(resultado.getDate("fecha_nacimiento"));
		paciente.setCorreo(resultado.getString("correo"));

		return paciente;
	}
}
