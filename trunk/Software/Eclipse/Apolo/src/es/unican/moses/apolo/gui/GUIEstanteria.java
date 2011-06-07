package es.unican.moses.apolo.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.FlowLayout;

//Paquetes propios
import es.unican.moses.apolo.ghost.GhostComponentAdapter;
import es.unican.moses.apolo.ghost.GhostDropListener;
import es.unican.moses.apolo.ghost.GhostDropManagerDemo;



public class GUIEstanteria extends JPanel {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<GUIDiapositiva> listaGUIDiapositivas;
	private JPanel visor;
	private GhostComponentAdapter componentAdapter;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public GUIEstanteria() {
		inicializacion();
	}
	public GUIEstanteria(GhostComponentAdapter componentAdapter) {
		this.componentAdapter = componentAdapter;
		inicializacion();
	}
		
		
	private void inicializacion(){
		
		listaGUIDiapositivas= new LinkedList<GUIDiapositiva>();
		
		setPreferredSize(new Dimension(200,200)); //Mirarlo un poco mas De momento funciona
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estanteria x", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		visor = new JPanel();
		scrollPane.setViewportView(visor);
		visor.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel Controles = new JPanel();
		GridBagConstraints gbc_Controles = new GridBagConstraints();
		gbc_Controles.fill = GridBagConstraints.BOTH;
		gbc_Controles.gridx = 1;
		gbc_Controles.gridy = 0;
		add(Controles, gbc_Controles);
		Controles.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton bt_anadir = new JButton("");
		bt_anadir.setIcon(new ImageIcon("C:\\Users\\Angel\\workspace\\pruebas\\img\\anadir.png"));
		Controles.add(bt_anadir);
		
		JButton bt_descartar = new JButton("");
		bt_descartar.setIcon(new ImageIcon("C:\\Users\\Angel\\workspace\\pruebas\\img\\descartar.png"));
		Controles.add(bt_descartar);
		
		JButton bt_acciones = new JButton("");
		bt_acciones.setIcon(new ImageIcon("C:\\Users\\Angel\\workspace\\pruebas\\img\\masacciones.png"));
		Controles.add(bt_acciones);
		
		if(componentAdapter!=null){
		//Añadir controladores de evento de arrastre
		GhostDropListener listener = new GhostDropManagerDemo(visor, this);
		componentAdapter.addGhostDropListener(listener);
		}

	}
	
	public void setNumEstanteria(int n){
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estanteria " + n, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	}
	
	public void addGUIDiapositiva(GUIDiapositiva diapo){
		listaGUIDiapositivas.add(diapo);
		visor.add(diapo);
	}
}
