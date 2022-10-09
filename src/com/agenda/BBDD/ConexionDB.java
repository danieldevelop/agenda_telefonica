package com.agenda.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.agenda.BBDD.utils.SettingsDB;

public class ConexionDB {

	Connection conexion;

	public ConexionDB() {
		SettingsDB setting = new SettingsDB();

		try {
			Class.forName(setting.CONTROLADOR);
			conexion = DriverManager.getConnection(setting.URL_JDBC, setting.USERNAME, setting.PASSWORD);

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("E-DG1: " + e);
		}

	}

	public Connection recuperarConexion() {
		return conexion;
	}
	
}
