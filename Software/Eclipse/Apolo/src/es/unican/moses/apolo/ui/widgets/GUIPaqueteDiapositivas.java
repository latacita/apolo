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

package es.unican.moses.apolo.ui.widgets;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

//Paquetes propios
import es.unican.moses.apolo.logic.Album;
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.logic.Diapositiva;


/**
 * Clase GUIListaDiapositivas
 * Representa a una serie de Diapositivas
 * Extiende a JPanel
 * En su interior contiene unos iconos que simbolizan una carpeta.
 * Se puestran fotos dinamicamente, dependiendo de las que contenga.
 * 
 * @author Angel
 *
 */
public class GUIPaqueteDiapositivas extends JPanel{

	/** AUTOGERENDADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<GUIDiapositiva> listaDiapositivas;
	private JLabel lbl_foto1;
	private JLabel lbl_foto2;
	private JLabel lbl_foto3 ;
	private JLabel lbl_contadorSecuencia;
	private JLabel lbl_descripcion;
	private JLabel label_back;
	private GUIAlbum padre;
	

	/**
	 * Create the panel.
	 */
	public GUIPaqueteDiapositivas() {
		inicializacion();
	}
	public GUIPaqueteDiapositivas(LinkedList<GUIDiapositiva> listaDiapositivas, GUIAlbum padre){
		this.listaDiapositivas = listaDiapositivas;
		this.padre = padre;
		inicializacion();
		cargaFotosDeLista();
	}
		
	/**
	 * Inicializacion de componentes
	 */
	private void inicializacion(){
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(null);
		
		JLabel lbl_subsecuencia = new JLabel("Secuencia");
		lbl_subsecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subsecuencia.setBounds(20, 20, 80, 16);
		add(lbl_subsecuencia);
		
		lbl_contadorSecuencia = new JLabel("0");
		lbl_contadorSecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_contadorSecuencia.setBounds(20, 40, 80, 16);
		add(lbl_contadorSecuencia);
		
		lbl_descripcion = new JLabel("");
		lbl_descripcion.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbl_descripcion.setForeground(new Color(0, 100, 0));
		lbl_descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_descripcion.setBounds(20, 66, 80, 54);
		add(lbl_descripcion);
		
		JLabel label_front = new JLabel("");
		label_front.setIcon(new ImageIcon(Constantes.RUTA_ICONO_ALBUM_FRONT));
		label_front.setBounds(6, 5, 105, 128);
		add(label_front);
		
		lbl_foto1 = new JLabel("");
		lbl_foto1.setBounds(73, 10, 80, 53);
		add(lbl_foto1);
		
		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(73, 48, 80, 53);
		add(lbl_foto2);
		
		lbl_foto3 = new JLabel("");
		lbl_foto3.setBounds(73, 75, 80, 53);
		add(lbl_foto3);
		setPreferredSize(new Dimension(170, 145));
		
		label_back = new JLabel("");
		label_back.setIcon(new ImageIcon(Constantes.RUTA_ICONO_ALBUM_BACK));
		label_back.setBounds(70, 5, 95, 128);
		add(label_back);
		
		cargarPopups();

	}
	
	/** Carga los Popups que tendran las diapositivas */
    private void cargarPopups(){
    	JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		JMenuItem mntmDesespaquetar = new JMenuItem("Desempaquetar");
		mntmDesespaquetar.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MEN_LISTAGUID_DESEMPA));
		mntmDesespaquetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desempaquetar();
			}
		});
		
		JMenuItem mntmCambiarDescripcion = new JMenuItem("Cambiar Descripcion");
		mntmCambiarDescripcion.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MEN_LISTAGUID_CAMDES));
		mntmCambiarDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String respuesta = JOptionPane.showInputDialog(null, " \u00BFQue descripcion desea?", "Descripcion", JOptionPane.QUESTION_MESSAGE);
				if (respuesta != ""){
					lbl_descripcion.setText(respuesta);
				}
			}
		});
		mntmCambiarDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupMenu.add(mntmCambiarDescripcion);
		mntmDesespaquetar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupMenu.add(mntmDesespaquetar);
    }
    
    /**
     * Añade a las diapositivas un menu estilo POPUP
     * @param component
     * @param popup
     */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	/**
	 * Metodo privado que carga las fotos dinamicamente.
	 */
	private void cargaFotosDeLista(){
		if (listaDiapositivas!=null){
			switch (listaDiapositivas.size()) {
			case 1:
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				break;
			default:
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				break;
			}
		}
	}
	
	/**
	 * Metodo que indica el numero de secuencia.
	 * @param numero
	 */
	public void setNumeroSecuencia(int numero){
		lbl_contadorSecuencia.setText(String.valueOf(numero));
	}
	
	/**
	 * Metodo que coloca una descripcion 
	 * @param text
	 */
	public void setDescripcion(String text){
		lbl_descripcion.setText(text);
	}
	
	/** 
	 * Metodo que devuelve la descripcion
	 */
	public String getDescripcion(){
		return lbl_descripcion.getText();
	}
	
	/**
	 * Metodo que devuelve la lista de diapositivas que contiene
	 * @return
	 */
	public LinkedList<GUIDiapositiva> getLisDiapositivas(){
		return listaDiapositivas;
	}
	
	public Album getAlbum(){
		LinkedList<Diapositiva> lista = new LinkedList<Diapositiva>();
		for (GUIDiapositiva gui_diapo : getLisDiapositivas()){
			lista.add(gui_diapo.getDiapositiva());
		}
		return new Album(lista, lbl_descripcion.getText());
	}
	
	/**
	 * metodo privado que deshace el album y lo combierte en estanteria
	 */
	private void desempaquetar(){
		int res = JOptionPane.showConfirmDialog(null, " \u00BFDesea desempaquetar las fotos? Apareceran desglosadas en una nueva balda", "Desempaquetar las fotos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.OK_OPTION) padre.desempaquetar(this);
	}
}
