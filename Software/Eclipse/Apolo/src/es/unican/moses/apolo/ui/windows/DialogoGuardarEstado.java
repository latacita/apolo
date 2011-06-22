/*
Apolo is a digital photographs classification system inspired on 
traditional lightened classification boxes for slide classification.

Copyright (C) 2011  Angel Tezanos Iba�ez

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;

//Paquetes propios
import es.unican.moses.apolo.logic.ThreadGuardarEstado;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIMesa;


/**
 * Clase DialogoGuardarEstado
 * Muestra un dialogo modal en el que se indica que 
 * partes de la aplicacion desean ser guardadas.
 * Asi como la necesidad de seleccionar un fichero donde guardar.
 * @author Angel
 *
 */
public class DialogoGuardarEstado extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1083703956465044089L;
	/** Atributos */
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JLabel lbl_directorio;
	private JButton guardarButton;
	private JFrame padre;
	private JProgressBar progreso;
	private JCheckBox chckbxAlbums;
	private JCheckBox chckbxEstanteria;
	private JCheckBox chckbxMesa;
	private GUIAlbum gui_album;
	private GUIEstanteria gui_estanteria;
	private GUIMesa gui_mesa;

	/**
	 * Create the dialog.
	 */
	public DialogoGuardarEstado() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inicializar();
	}
	public DialogoGuardarEstado(JFrame padre, GUIAlbum gui_album, GUIEstanteria gui_estanteria, GUIMesa gui_mesa) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.padre = padre;
		this.gui_album = gui_album;
		this.gui_estanteria = gui_estanteria;
		this.gui_mesa = gui_mesa;
		inicializar(); 
	}
		
	/**
	 * Metodo privado que inicializa los componentes
	 */
	private void inicializar(){
		setTitle("Guardar");
		setModal(true);
		setBounds(100, 100, 430, 227);
		setLocationRelativeTo(padre);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{320, 110, 0};
		gbl_contentPanel.rowHeights = new int[]{96, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
			panel.setLayout(null);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			
			JLabel lblGuardarEn = new JLabel("Guardar en:");
			lblGuardarEn.setBounds(20, 67, 76, 16);
			panel.add(lblGuardarEn);
			
			JButton btnSeleccionarDirectorio = new JButton("Seleccionar Directorio");
			btnSeleccionarDirectorio.setToolTipText("Seleccionar directorio");
			btnSeleccionarDirectorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accionBotonSeleccionarDirectorio();
				}
			});
			btnSeleccionarDirectorio.setBounds(96, 61, 177, 28);
			panel.add(btnSeleccionarDirectorio);
			
			lbl_directorio = new JLabel("");
			lbl_directorio.setToolTipText("No seleccionado");
			lbl_directorio.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_directorio.setBounds(20, 91, 253, 28);
			lbl_directorio.setText("No seleccionado");
			panel.add(lbl_directorio);
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(20, 32, 55, 16);
				panel.add(lblNombre);
			}
			
			txtnombre = new JTextField();
			txtnombre.setToolTipText("Nombre del fichero de estado");
			txtnombre.setHorizontalAlignment(SwingConstants.RIGHT);
			txtnombre.setText("Nombre_fichero");
			txtnombre.setBounds(96, 26, 136, 28);
			panel.add(txtnombre);
			txtnombre.setColumns(10);
			
			JLabel lblapl = new JLabel(".apl");
			lblapl.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblapl.setBounds(233, 32, 55, 16);
			panel.add(lblapl);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Elementos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				chckbxAlbums = new JCheckBox("Albums");
				chckbxAlbums.setToolTipText("Album de la aplicacion, parte superior de la misma");
				chckbxAlbums.setSelected(true);
				panel.add(chckbxAlbums);
			}
			{
				chckbxEstanteria = new JCheckBox("Baldas");
				chckbxEstanteria.setToolTipText("Baldas de la aplicacion, parte centrar de la misma");
				chckbxEstanteria.setSelected(true);
				panel.add(chckbxEstanteria);
			}
			{
				chckbxMesa = new JCheckBox("Mesa");
				chckbxMesa.setToolTipText("Mesa de la aplicacion, parte baja de la misma");
				chckbxMesa.setSelected(true);
				panel.add(chckbxMesa);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				guardarButton = new JButton("Guardar");
				guardarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						guardar();
					}
				});
				guardarButton.setToolTipText("Guardar el estado del programa");
				guardarButton.setEnabled(false);
				guardarButton.setActionCommand("OK");
				buttonPane.add(guardarButton, BorderLayout.CENTER);
				getRootPane().setDefaultButton(guardarButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				cancelButton.setToolTipText("Cancelar la accion");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, BorderLayout.EAST);
			}
			{
				progreso = new JProgressBar();
				buttonPane.add(progreso, BorderLayout.SOUTH);
			}
		}
		setVisible(true);
		
	}
	
	/**
	 * Metodo privado que realiza la accion al pulsar el 
	 * boton de seleccion de directorio
	 */
	private void accionBotonSeleccionarDirectorio(){
		String ruta = seleccionarDirectorio();
		if (ruta==""){
			JOptionPane.showMessageDialog(padre, "Nombre y ruta no validos" , "Error", JOptionPane.INFORMATION_MESSAGE);
			lbl_directorio.setText("No seleccionado");
			lbl_directorio.setToolTipText("No seleccionado");
			guardarButton.setEnabled(false);
		}else{
			guardarButton.setEnabled(true);
			lbl_directorio.setText(ruta);
			lbl_directorio.setToolTipText(ruta);
		}
			
	}
	
	/**
	 * Metodo privado que muestra un dialogo para
	 * seleccionar un directorio donde guardar el estado
	 * @return
	 */
	private String seleccionarDirectorio(){
		String ruta="";
		//Lanzar seleccionador de carpeta
		JFileChooser seleccionador = new JFileChooser(); 
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ruta = ""; 
		try{ 
			if(seleccionador.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){ 
				ruta = seleccionador.getSelectedFile().getAbsolutePath(); 
			}
		}catch (Exception ex){ 
			System.out.println("Error al seleccionar fichero para guardar el estado"); 
		} 
		return ruta;
	}
	
	/**
	 * Accion al pulsar el boton cancelar
	 */
	public void cancelar(){
		setVisible(false);
		dispose();
	}
	
	/**
	 * Accion al pulsar el boton guardar
	 */
	private void guardar(){
		if (txtnombre.getText().length()!=0){
			String rutaFicheroSave = lbl_directorio.getText() + "/" + txtnombre.getText() + ".apl";
			GUIMesa m = null;
			GUIEstanteria e = null;
			GUIAlbum a = null;
			if (chckbxMesa.isSelected())
				m=gui_mesa;
			if (chckbxEstanteria.isSelected())
				e = gui_estanteria;
			if (chckbxAlbums.isSelected())
				a = gui_album;
			if (m == null && a == null && e == null){
				JOptionPane.showMessageDialog(this, "Error debe seleccionar almenos una opcion que guardar", "Error no hay nada que guardar", JOptionPane.ERROR_MESSAGE);
			}else{
			//Lanzar el threadguardarestado
				ThreadGuardarEstado threadGuarda = new ThreadGuardarEstado(rutaFicheroSave, m, e, a, this);
				threadGuarda.execute();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Error debe indicar un nombre para el fichero", "Error nombre incorrecto", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Devuelve un progras bar
	 * @return
	 */
	public JProgressBar getJProgressBar(){
		return progreso;
	}
}
