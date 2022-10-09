package com.agenda.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.agenda.BBDD.ConexionDB;
import com.agenda.controlador.ContactoController;

public class AgendaTelefonicaFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblListaContacto;
	private DefaultTableModel modelo;
	ContactoController cc = new ContactoController();

	/**
	 * Create the frame.
	 */
	public AgendaTelefonicaFrame() {
		setResizable(false);
		setTitle("Agenda Telefonica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Todos los contactos");
		lblTitulo.setFont(new Font("Fira Code Light", Font.BOLD, 15));
		lblTitulo.setBounds(21, 11, 190, 29);
		contentPane.add(lblTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(128, 128, 128));
		scrollPane.setBounds(21, 76, 555, 193);
		contentPane.add(scrollPane);

		tblListaContacto = new JTable();
		tblListaContacto.setEnabled(false);
		tblListaContacto.setFillsViewportHeight(true);
		tblListaContacto.setGridColor(new Color(128, 128, 128));
		tblListaContacto.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "APELLIDOS", "MOVIL", "TRABAJO", "CORREO" }));
		scrollPane.setViewportView(tblListaContacto);

		JButton btnAgregarContacto = new JButton("Nuevo Contacto");
		btnAgregarContacto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregarContacto.setBounds(435, 24, 128, 29);
		contentPane.add(btnAgregarContacto);

		JLabel lblTotalRegistros = new JLabel("");
		lblTotalRegistros.setBounds(21, 51, 190, 14);
		contentPane.add(lblTotalRegistros);

		lblTotalRegistros.setText(String.format("Cantidad de contactos: %d", cc.totalRegistros()));
		
		// Cargamos los datos a la tabla al iniciar la aplicacion
		cargaTabla();
	}
	
	private void cargaTabla() {
		modelo = (DefaultTableModel) tblListaContacto.getModel();
		try {
			var contactos = cc.listar();
			
			try {
				contactos.forEach(contacto
						-> modelo.addRow(new Object[]{
								contacto.get("id"),
								contacto.get("nombre"),
								contacto.get("apellidos"),
								contacto.get("movil"),
								contacto.get("trabajo"),
								contacto.get("correo")
							})
						);
			} catch (Exception e) {
				throw e;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}


