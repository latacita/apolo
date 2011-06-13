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
		JButton bt_nuevaBalda = new JButton("<html><b>A\u00F1adir Nueva Balda</b></html>");
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
		gui_balda = new GUIBalda(this, (GhostComponentAdapter) componentAdapter, gui_album);
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
	public void removeEstanteria(GUIBalda gui_balda){
		listaGUIBaldas.remove(gui_balda);
		estanteria.remove(gui_balda);
		renumerarBaldas();
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
	}
}
