/**
 * En este paquete se crea la funciones generales que el proyecto requiera
 * para los formularios del proyecto, entre otros. 
 */
package com.agenda.functions;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 * Clase que se encargara de validar los JTextFields de los formularios.
 * Validara textos vacios, tipos de datos, eventos del teclado, entre otros
 * 
 * @author Daniel Gómez
 * @see Regex <a href=
 *      "https://www.aluracursos.com/blog/regex-en-java-validando-datos-con-expresiones-regulares">Expresión
 *      Regulares</a>
 */
public class Validar {

	private String regexTexto = "[a-z ]+";
	private String regexCorreo = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	public boolean estaVacio(String... campos) {
		for (String campo : campos) {
			if (campo.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que recibe una tecla del teclado presionada por el usuario only y
	 * valida si dicha tecla corresponde a un numero utilizado getKeyChar().Si la
	 * tecla presionada es una letra se ejecuta el evento consume();
	 * 
	 * @param tecla
	 * @return void
	 * @see InputEvent
	 */
	public void esNumero(KeyEvent tecla) {
		if (tecla.getKeyChar() < '0' || tecla.getKeyChar() > '9') {
			tecla.consume(); // elimina la tecla insertada
		}
	}

	public boolean esTexto(String cadena) {
		Pattern pattern = Pattern.compile(regexTexto, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher match = pattern.matcher(cadena);
		
		if (match.matches()) {
			return true;
		}
		return false;
	}

	public boolean esEmail(String correo) {
		Pattern pattern = Pattern.compile(regexCorreo, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher match = pattern.matcher(correo);

		if (match.find()) {
			return true;
		}
		return false;
	}

}
