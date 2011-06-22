/*
Apolo is a digital photographs classification system inspired on 
traditional lightened classification boxes for slide classification.

Copyright (C) 2011  Angel Tezanos Ibañez

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
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Font;
import java.awt.Color;

//Paquetes propios
import es.unican.moses.apolo.logic.ThreadCargarEstado;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIMesa;



/**
 * Clase DialogoCargarEstado
 * Muestra un dialogo modal solicitando un archivo para
 * cargar el estado del programa
 * @author Angel
 *
 */
public class DialogoCargarEstado extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 8915839844016113774L;
	/** ATRIBUTOS */
	private JButton btnCancelar;
	private JButton btnCargar;
	private JButton btnSeleccionarFichero;
	private JLabel lbl_fichero;
	private JLabel lblFicheroACargar;
	private JFrame padre;
	private JProgressBar progreso;
	private GUIAlbum gui_album;
	private GUIEstanteria gui_estanteria;
	private GUIMesa gui_mesa;
	private String ruta;
	private GhostComponentAdapter componentAdapter = null;
	private GhostMotionAdapter motionAdapter = null;


	/**
	 * Create the dialog.
	 */
	public DialogoCargarEstado() {
		this.padre = null;
		inicializacion();
	}
	public DialogoCargarEstado(JFrame padre, GUIAlbum gui_album, GUIEstanteria gui_estanteria, GUIMesa gui_mesa, GhostComponentAdapter componentAdapter, GhostMotionAdapter motionAdapter) {
		this.padre = padre;
		this.gui_album = gui_album;
		this.gui_estanteria = gui_estanteria;
		this.gui_mesa = gui_mesa;
		this.componentAdapter = componentAdapter;
		this.motionAdapter = motionAdapter;
		inicializacion();
	}
		
	/**
	 * Iniciailizacion de componentes
	 */
	private void inicializacion(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Cargar fichero");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 259, 198);
		setLocationRelativeTo(padre);
		getContentPane().setLayout(new BorderLayout());
		{
			progreso = new JProgressBar();
			getContentPane().add(progreso, BorderLayout.SOUTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Seleccione el fichero a cargar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{81, 49, 77, 0};
			gbl_panel.rowHeights = new int[]{39, 16, 28, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			lblFicheroACargar = new JLabel("Fichero a cargar:");
			lblFicheroACargar.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lblFicheroACargar = new GridBagConstraints();
			gbc_lblFicheroACargar.fill = GridBagConstraints.VERTICAL;
			gbc_lblFicheroACargar.insets = new Insets(0, 0, 5, 5);
			gbc_lblFicheroACargar.gridx = 0;
			gbc_lblFicheroACargar.gridy = 0;
			panel.add(lblFicheroACargar, gbc_lblFicheroACargar);
			
			lbl_fichero = new JLabel("Seleccione un fichero");
			lbl_fichero.setForeground(new Color(0, 128, 0));
			lbl_fichero.setFont(new Font("SansSerif", Font.BOLD, 12));
			lbl_fichero.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lbl_fichero = new GridBagConstraints();
			gbc_lbl_fichero.gridwidth = 2;
			gbc_lbl_fichero.fill = GridBagConstraints.VERTICAL;
			gbc_lbl_fichero.insets = new Insets(0, 0, 5, 0);
			gbc_lbl_fichero.gridx = 1;
			gbc_lbl_fichero.gridy = 0;
			panel.add(lbl_fichero, gbc_lbl_fichero);
			
			btnSeleccionarFichero = new JButton("Seleccionar fichero");
			btnSeleccionarFichero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarFichero();
				}
			});
			GridBagConstraints gbc_btnSeleccionarFichero = new GridBagConstraints();
			gbc_btnSeleccionarFichero.fill = GridBagConstraints.BOTH;
			gbc_btnSeleccionarFichero.insets = new Insets(0, 0, 5, 0);
			gbc_btnSeleccionarFichero.gridwidth = 3;
			gbc_btnSeleccionarFichero.gridx = 0;
			gbc_btnSeleccionarFichero.gridy = 1;
			panel.add(btnSeleccionarFichero, gbc_btnSeleccionarFichero);
			
			btnCargar = new JButton("Cargar");
			btnCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargar();
				}
			});
			btnCargar.setEnabled(false);
			GridBagConstraints gbc_btnCargar = new GridBagConstraints();
			gbc_btnCargar.fill = GridBagConstraints.BOTH;
			gbc_btnCargar.gridwidth = 2;
			gbc_btnCargar.insets = new Insets(0, 0, 0, 5);
			gbc_btnCargar.gridx = 0;
			gbc_btnCargar.gridy = 2;
			panel.add(btnCargar, gbc_btnCargar);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.gridx = 2;
			gbc_btnCancelar.gridy = 2;
			panel.add(btnCancelar, gbc_btnCancelar);
		}
		setVisible(true);
	}
	
	/**
	 * Metodo privado que lanza un dialogo para seleccionar un fichero
	 */
	private void seleccionarFichero(){
		File file = null;
		//Seleccion de directorios o fotos
		JFileChooser seleccionador = new JFileChooser();
		//Filtro de archivos
		seleccionador.setFileSelectionMode(JFileChooser.FILES_ONLY);
		seleccionador.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo Apolo", "apl");
		seleccionador.setFileFilter(filter);
		//Abrimos dialogo
		int estado = seleccionador.showOpenDialog(this);
		//Opcion aceptada
		if (estado == JFileChooser.APPROVE_OPTION){
			file = seleccionador.getSelectedFile();
			ruta = file.getAbsolutePath();
			lbl_fichero.setText(file.getName());
			btnCargar.setEnabled(true);
		}else{
			btnCargar.setEnabled(false);
			lbl_fichero.setText("Seleccione un fichero");
		}
	}
	
	/**
	 * Metodo privado que lanza un thread para cargar el estado
	 */
	private void cargar(){
		ThreadCargarEstado thread = new ThreadCargarEstado(ruta, gui_mesa, gui_estanteria, gui_album, this); 
		thread.setControladores(componentAdapter, motionAdapter);
		thread.execute();
	}
	
	/**
	 * Retorna el progressbar
	 * @return
	 */
	public JProgressBar getProgreso(){
		return progreso;
	}
	
	/**
	 * Metodo que cambia dinamicamente la pantalla de carga, impidiendo que se cierre, 
	 * y mostrando la cantidad de fotos cargadas
	 */
	public void bloquearCancelar(){
		btnCancelar.setEnabled(false);
		btnCargar.setEnabled(false);
		btnSeleccionarFichero.setEnabled(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		lblFicheroACargar.setText("Fotos cargadas:");
		lbl_fichero.setText("0");
	}
	
	/**
	 * Obtiene el numero de fotogrfias cargas
	 * @param num
	 */
	public void setNumFicheros(int num){
		lbl_fichero.setText(String.valueOf(num));
	}
	
	/**
	 * Asigna el numero de fotografias cargadas
	 * @return
	 */
	public int getNumFicheros(){
		return Integer.parseInt(lbl_fichero.getText());
	}
}
