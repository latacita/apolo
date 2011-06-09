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

public class GUIEstanterias extends JPanel{

	/** ESTANTERIAS */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	/** Panel donde se van mostrando las distintas estanterias  */
	private JPanel Estanterias;
	/** Lista donde se almacenana las estanterias */
	private LinkedList<GUIEstanteria> listaGUIEstanterias;
	/** Componente mismo */
	GhostComponentAdapter componentAdapter;
	JScrollPane scrollPane;
	private GUIEstanteria estanteria_1;

	/**
	 * Create the panel.
	 */
	public GUIEstanterias() {
		this.componentAdapter = null;
		inicializacion();
	}
	public GUIEstanterias(GhostComponentAdapter componentAdapter) {
		this.componentAdapter = componentAdapter;
		inicializacion();
	}
	
	private void inicializacion(){
		
		listaGUIEstanterias = new LinkedList<GUIEstanteria>();
		setLayout(new BorderLayout(0, 0));
		
		//Scroll Para ver todas las posibles estanterias
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		//Creacion del panel
		Estanterias = new JPanel();
		scrollPane.setViewportView(Estanterias);
		GridBagLayout gbl_Estanterias = new GridBagLayout();
		gbl_Estanterias.columnWidths = new int[]{ 0};
		gbl_Estanterias.rowHeights = new int[]{0};
		gbl_Estanterias.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_Estanterias.rowWeights = new double[]{0};
		Estanterias.setLayout(gbl_Estanterias);
		
		addEstanteria();
		addEstanteria();

		
		//Boton Nueva estanteria
		JButton bt_nuevaEstanteria = new JButton("A\u00F1adir Nueva Estanteria");
		bt_nuevaEstanteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEstanteria();
			}
		});
		add(bt_nuevaEstanteria, BorderLayout.SOUTH);
		
	}
	
	private void addEstanteria(){
		//Añado una nueva fila al gridgab
		estanteria_1 = new GUIEstanteria((GhostComponentAdapter) componentAdapter);
		estanteria_1.setNumEstanteria(listaGUIEstanterias.size() +1);
		GridBagConstraints gbc_estanteria_1 = new GridBagConstraints();
		gbc_estanteria_1.fill = GridBagConstraints.BOTH;
		gbc_estanteria_1.gridx = 0;
		gbc_estanteria_1.gridy = listaGUIEstanterias.size();
		listaGUIEstanterias.add(estanteria_1);
		Estanterias.add(estanteria_1, gbc_estanteria_1);
		validate();
	}
	
	public LinkedList<GUIEstanteria> getlistaGUIEstanterias(){
		return listaGUIEstanterias;
	}
}
