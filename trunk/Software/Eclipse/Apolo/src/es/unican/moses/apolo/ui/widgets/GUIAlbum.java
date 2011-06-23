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

package es.unican.moses.apolo.ui.widgets;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropListener;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropManagerAlbum;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.windows.Apolo;


/**
 * Clase GUIAlbum
 * Represena un album con las fotos ya organizadas y clasificadas por paquetes
 * Extiende a panel.
 * Internamente contiene un panel con scroll donde se muestran los paquetes de fotos
 * y unos botos que permiten controlar la aplicacion.
 * Guardar, cargar, exportar e importar
 * @author Angel
 *
 */
public class GUIAlbum extends JPanel implements Serializable{
	
	/** AUTOGENRERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private JPanel visor;
	private Apolo padre;
	private GhostComponentAdapter componentAdapter;
	private GhostDropListener listener;
	private GhostMotionAdapter motionAdapter;

	/**
	 * Create the panel.
	 */
	public GUIAlbum() {
		initialice();
	}
	public GUIAlbum(Apolo padre,  GhostComponentAdapter componentAdapter,  GhostMotionAdapter motionAdapter) {
		this.padre = padre;
		this.componentAdapter = componentAdapter;
		this.motionAdapter = motionAdapter;
		initialice();
	}
	
	/**
	 * Inicializacion de los componenetes
	 */
	private void initialice(){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		visor = new JPanel();
		FlowLayout flowLayout = (FlowLayout) visor.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane.setViewportView(visor);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.cargar();
			}
		});
		btnCargar.setIcon(new ImageIcon(Constantes.RUTA_ICON_CARGAR));
		btnCargar.setToolTipText("Cargar fichero");
		panel.add(btnCargar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.guardar();
			}
		});
		btnGuardar.setIcon(new ImageIcon(Constantes.RUTA_ICON_GUARDAR));
		btnGuardar.setToolTipText("Guardar en un fichero");
		panel.add(btnGuardar);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.importar();
			}
		});
		btnImportar.setIcon(new ImageIcon(Constantes.RUTA_ICON_IMPORTAR));
		btnImportar.setToolTipText("Importar fotografias");
		panel.add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.exportar();
			}
		});
		btnExportar.setIcon(new ImageIcon(Constantes.RUTA_ICON_EXPORTAR));
		btnExportar.setToolTipText("Exportar fotografias");
		panel.add(btnExportar);
		
		//A�adir controladores de evento de arrastre
		if(componentAdapter!=null){
			listener = new GhostDropManagerAlbum(visor, this);
			componentAdapter.addGhostDropListener(listener);
		}
	}
	
	/**
	 * A�ade un paquete de fotos al album
	 * @param listaDiapositivas
	 * @param descripcion
	 */
	public void addListaFotos(LinkedList<GUIDiapositiva> listaDiapositivas, String descripcion){
		GUIPaqueteDiapositivas pagAlbum = new GUIPaqueteDiapositivas(listaDiapositivas, this);
		pagAlbum.setDescripcion(descripcion);
		pagAlbum.addMouseListener(componentAdapter);
		pagAlbum.addMouseMotionListener(motionAdapter);
		visor.add(pagAlbum);
		renumerar();	
		visor.validate();
	}
	
	/**
	 * Crea un paquete con la lista de diapositivas pasada
	 * @param listadiapo
	 */
	public void addGUIListaDiapositivas(GUIPaqueteDiapositivas listadiapo){
		visor.add(listadiapo);
		renumerar();
	}
	
	/**
	 * Metodo privado que renumera los paquetes de datos
	 */
	private void renumerar(){
		for ( int i = 0 ; i < visor.getComponents().length; i++){
			if (visor.getComponent(i) instanceof GUIPaqueteDiapositivas){
				((GUIPaqueteDiapositivas)visor.getComponent(i)).setNumeroSecuencia(i+1);
			}
		}
	}
	
	/**
	 * Devuelve la lista de paquetes visuales llamadas GUIListaDiapositivas que contiene el album
	 * @return
	 */
	public LinkedList<GUIPaqueteDiapositivas> getListaGUIListaDiapositivas(){
		LinkedList<GUIPaqueteDiapositivas> lista = new LinkedList<GUIPaqueteDiapositivas>();
		for (Component c : visor.getComponents()){
			if (c instanceof GUIPaqueteDiapositivas)
				lista.add(((GUIPaqueteDiapositivas)c));
		}
		return lista;
	}
	
	/**
	 * Retorna el visor que contiene los paquetes de fotos
	 * @return
	 */
	public JPanel getVisor(){
		return visor;
	}
	
	/**
	 * Deshace un paquete de diapositivas
	 * Crea una balda con las fotos contenidas en el paquete, elimina el paquete
	 */
	public void desempaquetar(GUIPaqueteDiapositivas paquete){
		GUIBalda balda = (padre.getGui_estanterias().addGUIBalda());
		for (GUIDiapositiva diapo :paquete.getLisDiapositivas() ){
			diapo.cambiaPadre(balda);
			balda.getVisor().add(diapo);
		}
		balda.setDescripcion(paquete.getDescripcion());
		eliminaPaquete(paquete);
	}
	
	/**
	 * Elimina un paquete de diapositivas del album
	 * @param paquete
	 */
	public void eliminaPaquete(GUIPaqueteDiapositivas paquete){
		visor.remove(paquete);
		visor.validate();
		visor.repaint();
	}
}
