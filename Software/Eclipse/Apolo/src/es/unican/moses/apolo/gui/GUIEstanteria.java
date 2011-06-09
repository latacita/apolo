package es.unican.moses.apolo.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

//Paquetes propios
import es.unican.moses.apolo.ghost.GhostComponentAdapter;
import java.awt.GridLayout;

public class GUIEstanteria extends JPanel{

	/** ESTANTERIAS */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	/** Panel donde se van mostrando las distintas estanterias  */
	private JPanel Estanteria;
	/** Lista donde se almacenana las estanterias */
	private LinkedList<GUIBalda> listaGUIBaldas;
	/** Componente mismo */
	GhostComponentAdapter componentAdapter;
	JScrollPane scrollPane;
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
	
	private void inicializacion(){
		
		listaGUIBaldas = new LinkedList<GUIBalda>();
		setLayout(new BorderLayout(0, 0));
		
		//Scroll Para ver todas las posibles estanterias
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		//Creacion del panel
		Estanteria = new JPanel();
		scrollPane.setViewportView(Estanteria);
		
		addGUIBalda();
		addGUIBalda();
		
		
		//Boton Nueva balda
		JButton bt_nuevaBalda = new JButton("A\u00F1adir Nueva Balda");
		bt_nuevaBalda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addGUIBalda();
			}
		});
		add(bt_nuevaBalda, BorderLayout.SOUTH);
		
	}
	
	private void addGUIBalda(){
		Estanteria.setLayout(new GridLayout(0, 1, 0, 0));
		//Añado una nueva fila al gridgab
		gui_balda = new GUIBalda(this, (GhostComponentAdapter) componentAdapter, gui_album);
		gui_balda.setNumBalda(listaGUIBaldas.size() +1);
		listaGUIBaldas.add(gui_balda);
		Estanteria.add(gui_balda);
		scrollPane.validate();
		validate();
	}
	
	public LinkedList<GUIBalda> getlistaGUIBaldas(){
		return listaGUIBaldas;
	}
	
	public void removeEstanteria(GUIBalda gui_balda){
		listaGUIBaldas.remove(gui_balda);
		Estanteria.remove(gui_balda);
		renumerarBaldas();
		scrollPane.validate();
		repaint();
	}
	
	private void renumerarBaldas(){
		for (int i = 0; i < listaGUIBaldas.size() ; i++){
			listaGUIBaldas.get(i).setNumBalda(i+1);
		}
	}
}
