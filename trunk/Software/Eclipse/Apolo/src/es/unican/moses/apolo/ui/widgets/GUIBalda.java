package es.unican.moses.apolo.ui.widgets;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.FlowLayout;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropListener;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropManagerEstanteria;




public class GUIBalda extends JPanel {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<GUIDiapositiva> listaGUIDiapositivas;
	private JPanel visor;
	private GhostComponentAdapter componentAdapter;
	private GhostDropListener listener;
	private JScrollPane scrollPane;
	private GUIEstanteria padre;
	private GUIAlbum gui_album;
	private String descripcion="";
	private int numerobalda;

	/**
	 * Create the panel.
	 */
	public GUIBalda() {
		inicializacion();
	}
	public GUIBalda(GUIEstanteria padre, GhostComponentAdapter componentAdapter, GUIAlbum gui_album) {
		this.padre = padre;
		this.componentAdapter = componentAdapter;
		this.gui_album = gui_album;
		inicializacion();
	}
		
		
	private void inicializacion(){
		
		listaGUIDiapositivas= new LinkedList<GUIDiapositiva>();
		
		setPreferredSize(new Dimension(200, 222)); //Mirarlo un poco mas De momento funciona
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda x", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{180, 0};
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
		bt_anadir.setToolTipText("A\u00F1adir Secuencia");
		bt_anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anadirAAlbum();
			}
		});
		bt_anadir.setIcon(new ImageIcon(Constantes.RUTA_ICON_ADDALBUM));
		Controles.add(bt_anadir);
		
		JButton bt_descartar = new JButton("");
		bt_descartar.setToolTipText("Eliminar Balda");
		bt_descartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preguntaEliminarBalda();
			}
		});
		bt_descartar.setIcon(new ImageIcon(Constantes.RUTA_ICON_REMOVEALBUM));
		Controles.add(bt_descartar);
		
		JButton bt_acciones = new JButton("");
		bt_acciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String respuesta = JOptionPane.showInputDialog(visor,"¿Cual es la descripcion para la balda?","",JOptionPane.QUESTION_MESSAGE);
				descripcion = respuesta;
				addDescripcionBalda();
			}
		});
		bt_acciones.setToolTipText("A\u00F1adir Descripcion a la Balda");
		bt_acciones.setIcon(new ImageIcon(Constantes.RUTA_ICON_DESCRIPTION));
		Controles.add(bt_acciones);
		
		if(componentAdapter!=null){
		//Añadir controladores de evento de arrastre
		listener = new GhostDropManagerEstanteria(visor, this);
		componentAdapter.addGhostDropListener(listener);
		}

	}
	
	private void addDescripcionBalda(){
		if (descripcion!="")
			setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda " + numerobalda + " - " + descripcion, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		else
			setNumBalda(numerobalda);
	}
	
	public void setNumBalda(int n){
		numerobalda = n;
		if (descripcion!="")
			addDescripcionBalda();
		else
			setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda " + numerobalda, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	}
	
	public void addGUIDiapositiva(GUIDiapositiva diapo){
		listaGUIDiapositivas.add(diapo);
		visor.add(diapo);
	}
	
	private void preguntaEliminarBalda(){
		int respuesta;
		respuesta=JOptionPane.showConfirmDialog(this,"¿Desea Eliminar la Estanteria? ","Confirmacion Eliminacion",JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			removeBalda();
		}
	}
	
	private void removeBalda(){
		componentAdapter.removeGhostDropListener(listener);
		padre.removeEstanteria(this);
	}                                             
	
	public void anadirAAlbum(){
		LinkedList<GUIDiapositiva> lista = new LinkedList<GUIDiapositiva>();
		for(int i=0; i < visor.getComponents().length;i++){
			if (visor.getComponents()[i] instanceof GUIDiapositiva){
				lista.add((GUIDiapositiva)visor.getComponents()[i]);
			}
		}
		if(lista.size()>0){
			gui_album.addListaFotos(lista);
			preguntaEliminarBalda();
		}
	}
}
