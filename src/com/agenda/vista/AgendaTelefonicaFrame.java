package com.agenda.vista;

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

import com.agenda.controlador.ContactoController;
import com.agenda.functions.MensajeBox;
import com.agenda.functions.Validar;

public class AgendaTelefonicaFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblListaContacto;
	private DefaultTableModel modelo;
	ContactoController cc = new ContactoController();
	MensajeBox alert = new MensajeBox();
	Validar valida = new Validar();

	/**
	 * Create the frame.
	 */
	public AgendaTelefonicaFrame() {
		setResizable(false);
		setTitle("Agenda Telefonica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 362);
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
		btnAgregarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				GuardarContactoFrame save = new GuardarContactoFrame();
				save.setVisible(true);
			}
		});
		btnAgregarContacto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregarContacto.setBounds(435, 24, 128, 29);
		contentPane.add(btnAgregarContacto);

		JLabel lblTotalRegistros = new JLabel("");
		lblTotalRegistros.setBounds(21, 51, 190, 14);
		contentPane.add(lblTotalRegistros);

		lblTotalRegistros.setText(String.format("Cantidad de contactos: %d", cc.totalRegistros()));

		JButton btnActualizarContacto = new JButton("Actualizar Contacto");
		btnActualizarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ActualizarContactoFrame update = new ActualizarContactoFrame();
				update.setVisible(true);
			}
		});
		btnActualizarContacto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnActualizarContacto.setBounds(21, 280, 158, 29);
		contentPane.add(btnActualizarContacto);

		JButton btnEliminarContacto = new JButton("Eliminar Contacto");
		btnEliminarContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idContacto = alert.showInputDialog(rootPane, "INGRESE EL ID DEL CONTACTO");

				// Revisar despues null, cuando se presiona el boton Cancel
				if (valida.estaVacio(idContacto)) {
					alert.showMessageDialog(rootPane, "Debe Ingresar un ID de Contacto", 0);
				} else {
					int confirmado = alert.showConfirmDialog(rootPane,
							String.format("Estás seguro de eliminar el contacto %s", idContacto));

					if (confirmado == 0) {
						try {
							int id = Integer.parseInt(idContacto.trim());
							int cantidadEliminada = cc.eliminar(id);

							if (cantidadEliminada == 1) {
								alert.showMessageDialog(null, "Contacto eliminado exitosamente", 1);
								limpiarJTable();
								cargaTabla();
								lblTotalRegistros.setText(String.format("Cantidad de contactos: %d", cc.totalRegistros()));
							} else {
								alert.showMessageDialog(null,
										"Error, el contacto con ID " + idContacto + " no fue eliminado ", 0);
							}

						} catch (Exception ex) {
							System.out.println("E-DG5: " + ex.getMessage());
						}
					}
				}
			}
		});
		btnEliminarContacto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminarContacto.setBounds(200, 280, 138, 29);
		contentPane.add(btnEliminarContacto);

		// Cargamos los datos a la tabla al iniciar la aplicacion
		cargaTabla();
	}

	private void cargaTabla() {
		modelo = (DefaultTableModel) tblListaContacto.getModel();
		try {
			var contactos = cc.listar();

			try {
				contactos.forEach(contacto -> modelo
						.addRow(new Object[] { contacto.get("id"), contacto.get("nombre"), contacto.get("apellidos"),
								contacto.get("movil"), contacto.get("trabajo"), contacto.get("correo") }));
			} catch (Exception e) {
				throw e;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	private void limpiarJTable() {
		modelo = (DefaultTableModel) tblListaContacto.getModel();
		int filas = tblListaContacto.getRowCount();

		for (int i = 0; filas > i; i++) {
			modelo.removeRow(0);
		}
	}
}
