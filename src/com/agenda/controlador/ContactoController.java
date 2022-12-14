package com.agenda.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.agenda.BBDD.ConexionDB;
import com.agenda.functions.MensajeBox;
import com.agenda.modelo.Contacto;

public class ContactoController {

	private int updateCount;
	MensajeBox alert = new MensajeBox();

	public Integer totalRegistros() {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;
		ResultSet resultado;

		Integer cantidad_registros = 0;

		if (con != null) {
			String sql = "SELECT count(*) as cantidad_registros FROM contacto c; ";
			try {
				statement = con.prepareStatement(sql);
				statement.execute();
				resultado = statement.getResultSet();

				if (resultado.next()) {
					cantidad_registros = resultado.getInt(1);
				}

				statement.close();
				resultado.close();
				con.close();

			} catch (SQLException e) {
				System.out.println("E-DG1: " + e.getMessage());
			}
		} else {
			alert.showMessageDialog(null, "E-DG2: Problema con el servidor MySQL", 0);
		}

		return cantidad_registros; // Si la consulta no esta se ejecuta devuelve 0 por defecto
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;
		ResultSet resultado;

		String sql = "SELECT c.id, c.nombre, c.apellidos, c.movil, c.trabajo, c.correo ";
		sql += "FROM contacto c; ";

		statement = con.prepareStatement(sql);
		statement.execute();
		resultado = statement.getResultSet();

		List<Map<String, String>> dato = new ArrayList<>();

		while (resultado.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(resultado.getInt(1)));
			fila.put("nombre", resultado.getString(2));
			fila.put("apellidos", resultado.getString(3));
			fila.put("movil", String.valueOf(resultado.getInt(4)));
			fila.put("trabajo", String.valueOf(resultado.getInt(5)));
			fila.put("correo", resultado.getString(6));

			dato.add(fila);
		}

		statement.close();
		resultado.close();
		con.close();

		return dato;
	}

	public void guardar(Contacto contacto) {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;
		ResultSet resultado;

		String sql = "INSERT INTO contacto(nombre, apellidos, movil, trabajo, correo) ";
		sql += "VALUES (?, ?, ?, ?, ?) ";

		try {
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, contacto.getNombre());
			statement.setString(2, contacto.getApellidos());
			statement.setInt(3, contacto.getMovil());
			statement.setInt(4, contacto.getTrabajo());
			statement.setString(5, contacto.getCorreo());
			statement.execute();

			resultado = statement.getGeneratedKeys();

			while (resultado.next()) {
				alert.showMessageDialog(null, "Contacto guardado exitosamente", 1);
//				System.out.println(String.format("Fue insertado el producto de ID %d", resultado.getInt(1)));
			}

			statement.close();
			resultado.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("E-DG3: " + e.getMessage());
		}
	}

	public int eliminar(int id) { // ? Se podra utilizar el objeto Contacto
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;

		String sql = "DELETE FROM contacto ";
		sql += "WHERE id = ?; ";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();

			updateCount = statement.getUpdateCount(); // Es importante, asignar el resultado a una variable int
			
			statement.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("E-DG4: " + e.getMessage());
		}
		
		return updateCount;
	}
	
	public boolean buscarContacto(int id) {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;
		ResultSet resultado;
		
		if (con != null) { // Si el servidor MySQL no esta only, mostrara el alert y dara una execption
			String sql = "SELECT c.id FROM contacto c ";
			sql += "WHERE c.id = ? ";
			
			try {
				statement = con.prepareStatement(sql);
				statement.setInt(1, id);
				statement.execute();
				
				resultado = statement.getResultSet();
				
				if (resultado.next()) {
					return true;
				} else {
					alert.showMessageDialog(null, "Contacto no encontrado o no existe", 2);
				}
				
				statement.close();
				resultado.close();
				con.close();
				
			} catch (Exception e) {
				System.out.println("E-DG6: " + e.getMessage());
			}
		} else {
			alert.showMessageDialog(null, "E-DG7: Problemas con el servidor", 0);
		}
		
		return false;
	}
	
	public int modificar(Contacto contacto) {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement;

		String sql = "UPDATE contacto ";
		sql += "SET nombre = ?, apellidos = ?, movil = ?, trabajo = ?, correo = ? ";
		sql += "WHERE id = ?; ";
		
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, contacto.getNombre());
			statement.setString(2, contacto.getApellidos());
			statement.setInt(3, contacto.getMovil());
			statement.setInt(4, contacto.getTrabajo());
			statement.setString(5, contacto.getCorreo());
			statement.setInt(6, contacto.getId());
			statement.execute();
			
			updateCount = statement.getUpdateCount();
			
			statement.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("E-DG8: " + e.getMessage());
		}
		
		return updateCount;
	}
}
