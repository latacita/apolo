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

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.GridLayout;
import java.io.Serializable;

//Paquetes propios
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;


/**
 * Clase GUIEstanteria 
 * Representa una estanteria, la cual puede albergar una serie de baldas.
 * Estiende a JPanel
 * Dispone internamente de un boton que va añadiendo baldas
 *  
 * @author Angel
 *
 */
public class GUIEstanteria extends JPanel implements Serializable{

	/** ESTANTERIAS */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	/** Panel donde se van mostrando las distintas estanterias  */
	private JPanel estanteria;
	/** Lista donde se almacenana las estanterias */
	private LinkedList<GUIBalda> listaGUIBaldas;
	/** Componente mismo */
	private GhostComponentAdapter componentAdapter;
	private JScrollPane scrollPane;
	private GUIBalda gui_balda;
	private GUIAlbum gui_album;

	/**
	 * Create the panel.
	 */
	public GUIEstanteria() {
		this.componentAdapter = null;
		inicializacion();
	}
	public GUIEstanteria(GhostComponentAdapter componentAdapter, GUIAlbum gui_album) {
		this.componentAdapter = componentAdapter;
		this.gui_album = gui_album;
		inicializacion();
	}
	
	/**
	 * Inicializacion de componentes
	 */
	private void inicializacion(){
		
		listaGUIBaldas = new LinkedList<GUIBalda>();
		setLayout(new BorderLayout(0, 0));
		
		//Scroll Para ver todas las posibles estanterias
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		//Creacion del panel
		estanteria = new JPanel();
		scrollPane.setViewportView(estanteria);
		
		addGUIBalda();
		addGUIBalda();
		
		
		//Boton Nueva balda
		JButton bt_nuevaBalda = new JButton("<html><b>Crear Nueva Balda</b></html>");
		bt_nuevaBalda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addGUIBalda();
			}
		});
		add(bt_nuevaBalda, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Metodo que añade una balda a la estanteria
	 */
	public GUIBalda addGUIBalda(){
		estanteria.setLayout(new GridLayout(0, 1, 0, 0));
		//Añado una nueva fila al gridgab
		gui_balda = new GUIBalda(this, componentAdapter, gui_album);
		gui_balda.setNumBalda(listaGUIBaldas.size() +1);
		listaGUIBaldas.add(gui_balda);
		estanteria.add(gui_balda);
		scrollPane.validate();
		validate();
		return gui_balda;
	}
	
	/**
	 * Metodo que retorna las baldas existentes en la estanteria
	 * @return
	 */
	public LinkedList<GUIBalda> getlistaGUIBaldas(){
		return listaGUIBaldas;
	}
	
	/**
	 * Metodo que elimina una balda de la estanteria
	 * @param gui_balda
	 */
	public void removeBalda(GUIBalda gui_balda){
		listaGUIBaldas.remove(gui_balda);
		estanteria.remove(gui_balda);
		//componentAdapter.removeGhostDropListener(gui_balda.getGhostListener());
		renumerarBaldas();
		if (estanteria.getComponents().length < 2){
			addGUIBalda();
		}
		scrollPane.validate();
		repaint();
	}
	
	/**
	 * Metodo que renumera las baldas
	 */
	public void renumerarBaldas(){
		for (int i = 0; i < listaGUIBaldas.size() ; i++){
			listaGUIBaldas.get(i).setNumBalda(i+1);
		}
	}
	
	/**
	 * Devuelve el panel estanteria, donde estan ubicadas las baldas
	 * @return
	 */
	public JPanel getEstanteria(){
		return estanteria;
	}
	
	/**
	 * reinicia el componente eliminandoo todas las baldas
	 */
	public void reinicia(){
		listaGUIBaldas = new LinkedList<GUIBalda>();
		getEstanteria().removeAll();
		componentAdapter.removeAllBaldas();
		estanteria.repaint();
	}
}
