package com.agenda.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.agenda.BBDD.ConexionDB;

public class ContactoController {

	public Integer totalRegistros() {
		Connection con = new ConexionDB().recuperarConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;

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
				JOptionPane.showMessageDialog(null, "E-DG1: " + e.getMessage(), "Agenda Telefonica",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "E-DG2: Problema con el servidor MySQL", "Agenda Telefonica",
					JOptionPane.ERROR_MESSAGE);
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
}
