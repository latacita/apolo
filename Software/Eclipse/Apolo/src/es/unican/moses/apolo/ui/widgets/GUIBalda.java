package es.unican.moses.apolo.ui.widgets;

import javax.swing.JPanel;

import java.awt.Component;
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
import es.unican.moses.apolo.logic.Balda;
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.logic.Diapositiva;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropListener;
import es.unican.moses.apolo.ui.dragAndDrop.GhostDropManagerEstanteria;


/**
 * Clase GUIBalda
 * Clase que representa una balda donde se posaran las diapositivas.
 * Extiende a la clase JPanel.
 * Contiene internamente otro panel con scroll en el que se
 * muestran las diapositivas. 
 * A la izquiera dispone de 3 botones
 * Los cuales permiten validar la valda, eliminarla o añadir una descripcion
 * 
 * @author Angel
 *
 */
public class GUIBalda extends JPanel{

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
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
		
	
	/**
	 * Inicializacion de los componentes
	 */
	private void inicializacion(){
		
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
		//Accion validar valda.
		bt_anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (visor.getComponents().length !=0){
					anadirAAlbum();
				}else{
					JOptionPane.showMessageDialog(visor,"Antes de añadirla al album, debe colocar al menos una diapositiva");
				}
			}
		});
		bt_anadir.setIcon(new ImageIcon(Constantes.RUTA_ICON_ADDALBUM));
		Controles.add(bt_anadir);
		
		JButton bt_descartar = new JButton("");
		bt_descartar.setToolTipText("Eliminar Balda");
		//Accion eliminar balda
		bt_descartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preguntaEliminarBalda();
			}
		});
		bt_descartar.setIcon(new ImageIcon(Constantes.RUTA_ICON_REMOVEALBUM));
		Controles.add(bt_descartar);
		
		JButton bt_acciones = new JButton("");
		//Accion añadir descripcion
		bt_acciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String respuesta = JOptionPane.showInputDialog(visor,"¿Cual es la descripcion para la balda?","",JOptionPane.QUESTION_MESSAGE);
				if (respuesta != null){
					descripcion = respuesta;
					addDescripcionBalda();
				}
			}
		});
		bt_acciones.setToolTipText("A\u00F1adir Descripcion a la Balda");
		bt_acciones.setIcon(new ImageIcon(Constantes.RUTA_ICON_DESCRIPTION));
		Controles.add(bt_acciones);
		
		//Añadir controladores de evento de arrastre
		if(componentAdapter!=null){
			listener = new GhostDropManagerEstanteria(visor, this);
			componentAdapter.addGhostDropListener(listener);
		}

	}
	
	/**
	 * Devuelve el listener
	 */
	/*public GhostDropListener getGhostListener(){
		return listener;
	}*/
	
	/**
	 * Coloca una descripcion a la balda
	 * @param des
	 */
	public void setDescripcion(String des){
		descripcion = des;
		addDescripcionBalda();
	}
	
	/**
	 * Metodo privado que añade una descripcion a la balda
	 */
	private void addDescripcionBalda(){
		if (descripcion==null || descripcion.length()==0)
			setNumBalda(numerobalda);
		else
			setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda " + numerobalda + " - " + descripcion, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	}
	
	/**
	 * Metodo que renombra el numero de la balda y 
	 * si esta dispone de descripcion lo añade
	 * @param n
	 */
	public void setNumBalda(int n){
		numerobalda = n;
		if (descripcion==null || descripcion.length()==0)
			setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda " + numerobalda, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		else
			setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balda " + numerobalda + " - " + descripcion, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
	}
	
	/**
	 * Metodo que añade una GUIDiapositiva a la balda
	 * @param diapo
	 */
	public void addGUIDiapositiva(GUIDiapositiva diapo){
		visor.add(diapo);
	}
	
	/**
	 * Metodo privado que pregunta si se desea eliminar la balda.
	 */
	private void preguntaEliminarBalda(){
		int respuesta;
		respuesta=JOptionPane.showConfirmDialog(this,"¿Desea Eliminar la Balda? ","Confirmacion Eliminacion",JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			removeBalda();
		}
	}
	
	/**
	 * Metodo privado que elimina la balda
	 * La elimina tambien de los listeners de arrastre.
	 */
	private void removeBalda(){
		componentAdapter.removeGhostDropListener(listener);
		padre.removeBalda(this);
	}                                             
	
	/**
	 * Metodo publico que añade la balda al album
	 */
	public void anadirAAlbum(){
		LinkedList<GUIDiapositiva> lista = new LinkedList<GUIDiapositiva>();
		for(int i=0; i < visor.getComponents().length;i++){
			if (visor.getComponents()[i] instanceof GUIDiapositiva){
				lista.add((GUIDiapositiva)visor.getComponents()[i]);
			}
		}
		if(lista.size()>0){
			gui_album.addListaFotos(lista, descripcion);
			//preguntaEliminarBalda();
			removeBalda();
		}
	}
	
	/**
	 * Devuelve una balda logica con la informacion de la visual
	 * Usado para guardarlo en un fichero
	 * @return
	 */
	public Balda getBalda(){
		LinkedList<Diapositiva> lista = new LinkedList<Diapositiva>();
		for (Component c : visor.getComponents()){
			if (c instanceof GUIDiapositiva){
				lista.add(((GUIDiapositiva)c).getDiapositiva());
			}
		}
		return new Balda(lista, descripcion);
	}
	
	/**
	 * Elimina una diapositiva de la valda
	 */
	public void removeGUIDiapositiva(GUIDiapositiva diapo){
		visor.remove(diapo);
		visor.validate();
		visor.repaint();
	}
	
	/**
	 * Devuelve el visor donde estan las GUIDiapositiva
	 */
	public JPanel getVisor(){
		return visor;
	}
}
