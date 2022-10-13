package com.agenda.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.agenda.BBDD.ConexionDB;
import com.agenda.controlador.ContactoController;
import com.agenda.functions.MensajeBox;
import com.agenda.functions.Validar;
import com.agenda.modelo.Contacto;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

public class ActualizarContactoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombreContacto;
	private JTextField txtApellidosContacto;
	Validar valida = new Validar();
	MensajeBox alert = new MensajeBox();
	ContactoController cc = new ContactoController();
	private JTextField txtMovilContacto;
	private JTextField txtTrabajoContacto;
	private JTextField txtCorreoContacto;

	/**
	 * Create the frame.
	 */
	public ActualizarContactoFrame() {
		setResizable(false);
		setTitle("Agenda Telefonica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 292);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Actualizar Contacto");
		lblTitulo.setFont(new Font("Fira Code Light", Font.BOLD, 15));
		lblTitulo.setBounds(10, 11, 197, 25);
		contentPane.add(lblTitulo);
		
		JLabel lblID = new JLabel("* Identificador:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(175, 50, 117, 14);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				valida.esNumero(e);
			}
		});
		txtID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtID.setBounds(272, 45, 53, 25);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombreContacto = new JLabel("Nombre");
		lblNombreContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreContacto.setBounds(10, 85, 78, 14);
		contentPane.add(lblNombreContacto);
		
		txtNombreContacto = new JTextField();
		txtNombreContacto.setEnabled(false);
		txtNombreContacto.setEditable(false);
		txtNombreContacto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNombreContacto.setBounds(73, 81, 117, 25);
		contentPane.add(txtNombreContacto);
		txtNombreContacto.setColumns(10);
		
		JLabel lblApellidoContacto = new JLabel("Apellidos");
		lblApellidoContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidoContacto.setBounds(200, 81, 78, 25);
		contentPane.add(lblApellidoContacto);
		
		txtApellidosContacto = new JTextField();
		txtApellidosContacto.setEnabled(false);
		txtApellidosContacto.setEditable(false);
		txtApellidosContacto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtApellidosContacto.setBounds(263, 81, 149, 25);
		contentPane.add(txtApellidosContacto);
		txtApellidosContacto.setColumns(10);
		
		JButton btnBuscarContacto = new JButton("Buscar");
		btnBuscarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (valida.estaVacio(txtID.getText())) {
					alert.showMessageDialog(rootPane, "Debe ingresar un ID", 0);
				} else {
					int codigo = Integer.parseInt(txtID.getText());
					
					if (cc.buscarContacto(codigo)) {
						try {
							Connection con = new ConexionDB().recuperarConexion();
							PreparedStatement statement;
							ResultSet resultado;
							
							String sql = "SELECT c.nombre, c.apellidos, c.movil, c.trabajo, c.correo ";
							sql += "FROM contacto c ";
							sql += "WHERE id = ? ";
							
							statement = con.prepareStatement(sql);
							statement.setInt(1, codigo);
							statement.execute();
							
							resultado = statement.getResultSet();
							
							if (resultado.next()) {
								habilitarCampos();
								txtNombreContacto.setText(resultado.getString(1));
								txtApellidosContacto.setText(resultado.getString(2));
								txtMovilContacto.setText(resultado.getString(3));
								txtTrabajoContacto.setText(resultado.getString(4));
								txtCorreoContacto.setText(resultado.getString(5));
							}
							
							statement.close();
							resultado.close();
							con.close();
							
						} catch (Exception ex) {
							System.out.println("E-DG7: " + ex.getMessage());
						}
					} else {
						limpiarCampos();
						
						txtNombreContacto.setEnabled(false);
						txtNombreContacto.setEditable(false);
						
						txtApellidosContacto.setEnabled(false);
						txtApellidosContacto.setEditable(false);
						
						txtMovilContacto.setEnabled(false);
						txtMovilContacto.setEditable(false);
						
						txtTrabajoContacto.setEnabled(false);
						txtTrabajoContacto.setEditable(false);
						
						txtCorreoContacto.setEnabled(false);
						txtCorreoContacto.setEditable(false);
					}
				}
			}
		});
		btnBuscarContacto.setContentAreaFilled(false);
		btnBuscarContacto.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscarContacto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarContacto.setBounds(328, 45, 84, 24);
		contentPane.add(btnBuscarContacto);
		
		JLabel lblMovilContacto = new JLabel("Movil");
		lblMovilContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMovilContacto.setBounds(10, 128, 46, 19);
		contentPane.add(lblMovilContacto);
		
		JLabel lblTrabajoContacto = new JLabel("Trabajo");
		lblTrabajoContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrabajoContacto.setBounds(200, 128, 63, 19);
		contentPane.add(lblTrabajoContacto);
		
		JLabel lblCorreoContacto = new JLabel("Correo");
		lblCorreoContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoContacto.setBounds(10, 170, 53, 14);
		contentPane.add(lblCorreoContacto);
		
		txtMovilContacto = new JTextField();
		txtMovilContacto.setEnabled(false);
		txtMovilContacto.setEditable(false);
		txtMovilContacto.setBounds(52, 125, 138, 25);
		contentPane.add(txtMovilContacto);
		txtMovilContacto.setColumns(10);
		
		txtTrabajoContacto = new JTextField();
		txtTrabajoContacto.setEnabled(false);
		txtTrabajoContacto.setEditable(false);
		txtTrabajoContacto.setBounds(263, 125, 149, 25);
		contentPane.add(txtTrabajoContacto);
		txtTrabajoContacto.setColumns(10);
		
		txtCorreoContacto = new JTextField();
		txtCorreoContacto.setEnabled(false);
		txtCorreoContacto.setEditable(false);
		txtCorreoContacto.setBounds(62, 167, 350, 25);
		contentPane.add(txtCorreoContacto);
		txtCorreoContacto.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmado = alert.showConfirmDialog(rootPane, "¿Estas Seguro?");
				
				if (confirmado == 0) {
					try {
						Contacto contact = new Contacto();
						
						contact.setId(Integer.parseInt(txtID.getText()));
						contact.setNombre(txtNombreContacto.getText().trim());
						contact.setApellidos(txtApellidosContacto.getText().trim());
						contact.setMovil(Integer.parseInt(txtMovilContacto.getText().trim()));
						contact.setTrabajo((txtTrabajoContacto.getText().isEmpty()) ? 0 : Integer.parseInt(txtTrabajoContacto.getText()));
						contact.setCorreo(txtCorreoContacto.getText().trim());
						
						int cantidadActualizada = cc.modificar(contact);
						
						if (cantidadActualizada == 1) {
							alert.showMessageDialog(rootPane, "Contacto actualizado correctamente", 1);
						} else {
							alert.showMessageDialog(rootPane, 
									"Error, el contacto con ID " + txtID.getText() + "no fue actualizado", 0);
						}
						
					} catch (Exception ex) {
						System.out.println("E-DG9: " + ex.getMessage());
					}
				}
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnActualizar.setBounds(10, 215, 94, 29);
		contentPane.add(btnActualizar);
		
		JButton btnRegresarHome = new JButton("Regresar");
		btnRegresarHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				AgendaTelefonicaFrame agendaT = new AgendaTelefonicaFrame();
				agendaT.setVisible(true);
			}
		});
		btnRegresarHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegresarHome.setBounds(125, 215, 99, 29);
		contentPane.add(btnRegresarHome);
	}
	
	private void habilitarCampos() {
		this.txtNombreContacto.setEnabled(true);
		this.txtNombreContacto.setEditable(true);
		
		this.txtApellidosContacto.setEnabled(true);
		this.txtApellidosContacto.setEditable(true);
		
		this.txtMovilContacto.setEnabled(true);
		this.txtMovilContacto.setEditable(true);
		
		this.txtTrabajoContacto.setEnabled(true);
		this.txtTrabajoContacto.setEditable(true);
		
		this.txtCorreoContacto.setEnabled(true);
		this.txtCorreoContacto.setEditable(true);
	}
	
	private void limpiarCampos() {
		this.txtNombreContacto.setText("");
		this.txtApellidosContacto.setText("");
		this.txtMovilContacto.setText("");
		this.txtTrabajoContacto.setText("");
		this.txtCorreoContacto.setText("");
	}
}
