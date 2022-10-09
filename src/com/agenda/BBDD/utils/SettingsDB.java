package com.agenda.BBDD.utils;

public class SettingsDB {

//	Configuracion para el entorno de desarrollo
	private static final String HOST = "localhost";
	private static final Integer PORT = 3306;
	private static final String DATABASE = "agenda_telefonica";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";

	public static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	public static final String URL_JDBC = String.format("jdbc:mysql://%s:%d/%s?useTimeZone=true&serverTimeZone=UTC", HOST,
			PORT, DATABASE);
}
