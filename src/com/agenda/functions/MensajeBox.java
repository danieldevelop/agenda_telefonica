/**
 * En este paquete se crea la funciones generales que el proyecto requiera
 * para los formularios del proyecto, entre otros. 
 */
package com.agenda.functions;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 * Clase para mostrar mensaje personalizado genericos
 * 
 * Valores predeterminados iconos JOptionPane
 * 	JOPtionPane.PLAIN_MESSAGE = -1
 * 	JOPtionPane.ERROR_MESSAGE = 0
 * 	JOPtionPane.INFORMATION_MESSAGE = 1
 * 	JOPtionPane.WARNING_MESSAGE = 2
 * 	JOPtionPane.QUESTION_MESSAGE = 3
 * 
 * @author Daniel Gómez
 */
public class MensajeBox {
	
	private static final String TITULO = "Agenda Telefonica"; 	
	
	public void showMessageDialog(JRootPane contentPane, String mensaje, Integer icono) {
		JOptionPane.showMessageDialog(contentPane, mensaje, TITULO, icono);
	}
	
	public String showInputDialog(JRootPane contentPane, String mensaje) {
		return (String) JOptionPane.showInputDialog(contentPane, mensaje, TITULO, 3);
	}
	
	public int showConfirmDialog(JRootPane contentPane, String mensaje) {
		return (int) JOptionPane.showConfirmDialog(contentPane, mensaje, TITULO, JOptionPane.OK_OPTION);
	}
}
