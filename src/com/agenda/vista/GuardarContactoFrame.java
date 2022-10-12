package com.agenda.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.agenda.controlador.ContactoController;
import com.agenda.functions.MensajeBox;
import com.agenda.functions.Validar;
import com.agenda.modelo.Contacto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class GuardarContactoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtMovil;
	private JTextField txtTrabajo;
	private JTextField txtCorreo;
	ContactoController cc = new ContactoController();
	Validar valida = new Validar();
	MensajeBox alert = new MensajeBox();

	/**
	 * Create the frame.
	 */
	public GuardarContactoFrame() {
		setResizable(false);
		setTitle("Agenda Telefonica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 296);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Registrar Contacto");
		lblTitulo.setFont(new Font("Fira Code Light", Font.BOLD, 15));
		lblTitulo.setBounds(10, 11, 300, 35);
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("* Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(10, 64, 78, 14);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("* Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(211, 64, 78, 14);
		contentPane.add(lblApellidos);

		JLabel lblMovil = new JLabel("* Movil:");
		lblMovil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMovil.setBounds(10, 114, 55, 14);
		contentPane.add(lblMovil);

		JLabel lblTrabajo = new JLabel("Trabajo:");
		lblTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrabajo.setBounds(211, 111, 78, 18);
		contentPane.add(lblTrabajo);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(10, 154, 132, 14);
		contentPane.add(lblCorreoElectronico);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNombre.setBounds(84, 60, 108, 25);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtApellidos.setBounds(286, 60, 127, 25);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtMovil = new JTextField();
		txtMovil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				valida.esNumero(e);
			}
		});
		txtMovil.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMovil.setBounds(72, 107, 120, 25);
		contentPane.add(txtMovil);
		txtMovil.setColumns(10);

		txtTrabajo = new JTextField();
		txtTrabajo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				valida.esNumero(e);
			}
		});
		txtTrabajo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTrabajo.setBounds(274, 107, 139, 25);
		contentPane.add(txtTrabajo);
		txtTrabajo.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCorreo.setBounds(140, 150, 273, 25);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		JButton btnGuardarContacto = new JButton("Guardar");
		btnGuardarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (valida.estaVacio(txtNombre.getText(), txtApellidos.getText(), txtMovil.getText())) {
					alert.showMessageDialog(rootPane, "Los campos [ NOMBRE, APELLIDOS y MOVIL ] son obligatorios", 0);
				} else if (!valida.esTexto(txtNombre.getText()) || !valida.esTexto(txtApellidos.getText())) {
					alert.showMessageDialog(rootPane, "Los campos [ NOMBRE, APELLIDOS ] no corresponde a un texto", 0);
				} else if (!valida.esEmail(txtCorreo.getText())) {
					alert.showMessageDialog(rootPane, "El [ CORREO ] ingresado no es valido", 0);
				} else {
					Contacto contact = new Contacto();

					contact.setNombre(txtNombre.getText().trim());
					contact.setApellidos(txtApellidos.getText().trim());
					contact.setMovil(Integer.parseInt(txtMovil.getText().trim()));
					contact.setTrabajo((txtTrabajo.getText().isEmpty()) ? 0 : Integer.parseInt(txtTrabajo.getText()));
					contact.setCorreo(txtCorreo.getText().trim());

					cc.guardar(contact);
				}
			}
		});
		btnGuardarContacto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardarContacto.setBounds(10, 218, 106, 29);
		contentPane.add(btnGuardarContacto);

		JLabel lblInfo = new JLabel("* Datos Obligatorios");
		lblInfo.setForeground(Color.RED);
		lblInfo.setFont(new Font("Fira Code", Font.BOLD, 11));
		lblInfo.setBounds(10, 193, 149, 14);
		contentPane.add(lblInfo);

		JButton btnVerContactos = new JButton("Ver Contactos");
		btnVerContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AgendaTelefonicaFrame agendaT = new AgendaTelefonicaFrame();
				agendaT.setVisible(true);
			}
		});
		btnVerContactos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVerContactos.setBounds(281, 218, 132, 29);
		contentPane.add(btnVerContactos);
	}
}
