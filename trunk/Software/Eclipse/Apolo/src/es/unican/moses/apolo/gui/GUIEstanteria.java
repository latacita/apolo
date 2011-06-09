package es.unican.moses.apolo.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//Paquetes propios
import es.unican.moses.apolo.ghost.GhostComponentAdapter;

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

	/**
	 * Create the panel.
	 */
	public GUIEstanteria() {
		this.componentAdapter = null;
		inicializacion();
	}
	public GUIEstanteria(GhostComponentAdapter componentAdapter) {
		this.componentAdapter = componentAdapter;
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
		GridBagLayout gbl_Estanteria = new GridBagLayout();
		gbl_Estanteria.columnWidths = new int[]{ 0};
		gbl_Estanteria.rowHeights = new int[]{0};
		gbl_Estanteria.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_Estanteria.rowWeights = new double[]{0};
		Estanteria.setLayout(gbl_Estanteria);
		
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
		//Añado una nueva fila al gridgab
		gui_balda = new GUIBalda(this, (GhostComponentAdapter) componentAdapter);
		gui_balda.setNumEstanteria(listaGUIBaldas.size() +1);
		GridBagConstraints gbc_estanteria_1 = new GridBagConstraints();
		gbc_estanteria_1.fill = GridBagConstraints.BOTH;
		gbc_estanteria_1.gridx = 0;
		gbc_estanteria_1.gridy = listaGUIBaldas.size();
		listaGUIBaldas.add(gui_balda);
		Estanteria.add(gui_balda, gbc_estanteria_1);
		validate();
	}
	
	public LinkedList<GUIBalda> getlistaGUIBaldas(){
		return listaGUIBaldas;
	}
	
	public void removeEstanteria(GUIBalda gui_balda){
		listaGUIBaldas.remove(gui_balda);
		Estanteria.remove(gui_balda);
		scrollPane.validate();
		repaint();
	}
}
