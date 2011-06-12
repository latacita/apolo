package es.unican.moses.apolo.ui.widgets;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//Paquetes Propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.logic.ThreadAddDiapositiva;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;



/**
 * Clase GUIMEsa
 * Representa visualmente a la clase Mesa
 * GUIMesa extiende a la clase Jpanel, contiene un JScrollPane un Jpanel interno
 * al JScrollPane donde se van situando las GUIDiapositivas para su organizacion y seleccion.
 * 
 * Implementa la interfaz ComponentListener, para redimensionar los paneles internos y 
 * reorganizar las diapositivas que se encuentren en la mesa.
 * 
 * @author Angel
 */
public class GUIMesa extends JPanel implements ComponentListener, Serializable{
	
	/** Atributos*/
	//private Mesa mesa;
	private static final long serialVersionUID = 1L;
	private GhostComponentAdapter componentAdapter;
	private GhostMotionAdapter motionAdapter;
	
	/** Componentes internos */
	private JScrollPane scrollPane;
	private JPanel panel_diapositivas;
	private JProgressBar barraProgreso;
	
	/**
	 * Constructor
	 */
	public GUIMesa(GhostComponentAdapter componentAdapter, GhostMotionAdapter motionAdapter){
		this.componentAdapter = componentAdapter;
		this.motionAdapter = motionAdapter;
		inicializacionGUIMesa();
	}
	
	/**
     * Metodo privado que inicializa la GUIMesa
     */
	private void inicializacionGUIMesa(){
		//Tamaño inicial
		setSize(800, 600);
		setLayout(new BorderLayout(0, 0));
		
		//Panel interno sobre el que se pitnaran las diapositivas
		panel_diapositivas = new JPanel();
		panel_diapositivas.setSize(800, 600);
		panel_diapositivas.setLayout(new GridLayout(0, panel_diapositivas.getWidth() / Constantes.TAM_DIAPOSITIVA, 5, 5));

		//Scroll
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(panel_diapositivas);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.addComponentListener(this);
		
		JPanel panel_bajo = new JPanel();
		add(panel_bajo, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_bajo = new GridBagLayout();
		gbl_panel_bajo.columnWidths = new int[]{51, 146, 0};
		gbl_panel_bajo.rowHeights = new int[]{16, 0};
		gbl_panel_bajo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_bajo.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_bajo.setLayout(gbl_panel_bajo);
		
		JLabel lblProgreso = new JLabel("Progreso:");
		GridBagConstraints gbc_lblProgreso = new GridBagConstraints();
		gbc_lblProgreso.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblProgreso.insets = new Insets(0, 0, 0, 5);
		gbc_lblProgreso.gridx = 0;
		gbc_lblProgreso.gridy = 0;
		panel_bajo.add(lblProgreso, gbc_lblProgreso);
		
		barraProgreso = new JProgressBar();
		barraProgreso.setValue(100);
		GridBagConstraints gbc_barraProgreso = new GridBagConstraints();
		gbc_barraProgreso.fill = GridBagConstraints.HORIZONTAL;
		gbc_barraProgreso.gridx = 1;
		gbc_barraProgreso.gridy = 0;
		panel_bajo.add(barraProgreso, gbc_barraProgreso);
		
		
		//HABRA QUE HACER OTRO CONTROLADOR APARTE.
		/*if(componentAdapter!=null){
			//Añadir controladores de evento de arrastre
			GhostDropListener listener = new GhostDropManagerMesa(panel_diapositivas, this);
			componentAdapter.addGhostDropListener(listener);
		}*/
		
		
	}
	
	/**
	 * Añade una serie de imagenes a la mesa
	 * Utiliza un swingworker (Hilo) para no paralizar la aplicacion
	 * @param files Array de ficheros imagen a añadir
	 */
	public void addDiapositiva(File[] files){
		barraProgreso.setValue(0);
		ThreadAddDiapositiva hilo = new ThreadAddDiapositiva(this, files, barraProgreso);
		hilo.execute();
	}
	
	/**
	 * Añade una GUIDiapositiva a la GUIMesa
	 * @param gui_diapositiva
	 */
	public void addGUIDiapositivia(GUIDiapositiva gui_diapositiva){
		//Añado a las diapositiva los eventos del raton
        gui_diapositiva.addMouseListener(componentAdapter);
        gui_diapositiva.addMouseMotionListener(motionAdapter);
		
		//Añadir el componente
		panel_diapositivas.add(gui_diapositiva);
		//Actualizar vista
		validate();
	}
	
	/** Elimina una diapositiva*/
	public void removeDiapositiva(GUIDiapositiva diapo){
		//Eliminar
		panel_diapositivas.remove(diapo);
		//renumerar();
		panel_diapositivas.validate();
		panel_diapositivas.repaint();
	}
	
	/**
	 * Devuelve el numero de diapositivas que hay en la GUI_Mesa
	 * @return
	 */
	public int getNumeroDeapositivas(){
		int numDiapositivas=0;
		for (int i=0 ; i<panel_diapositivas.getComponents().length; i++ ){
			if (panel_diapositivas.getComponents()[i] instanceof GUIDiapositiva){
				numDiapositivas++;
			}
		}
		return numDiapositivas;
	}
	
	/**
	 * Devuelve una lista de GUIDiapositivas que hay en la mesa
	 */
	public LinkedList<GUIDiapositiva> getlistaGUIDiapositivas(){
		LinkedList<GUIDiapositiva> lista = new LinkedList<GUIDiapositiva>();
		for (int i=0 ; i<panel_diapositivas.getComponents().length; i++ ){
			if (panel_diapositivas.getComponents()[i] instanceof GUIDiapositiva){
				lista.add((GUIDiapositiva)panel_diapositivas.getComponents()[i]);
			}
		}
		return lista;
	}
	
	/**
	 * Renumera las diapositivas de la mesa
	 */
	/*private void renumerar(){
		int num =1;
		for (Component componente : panel_diapositivas.getComponents()){
			if (componente instanceof GUIDiapositiva){
				((GUIDiapositiva) componente).setNumeroGui(num);
				num++;
			}
		}
	}*/
	
	public JProgressBar getBarraProgreso(){
		return barraProgreso;
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel getPanelDiapositivas(){
		return panel_diapositivas;
	}
	
	/* METODOS QUE OBLIGA A IMPLEMENTAR ComponentListener*/

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/** Al redimensionar, calcula el nuevo numero de columnas */ //Hay que depurar un poco esto.
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getComponent().getWidth() > Constantes.TAM_DIAPOSITIVA)
            ((java.awt.GridLayout)panel_diapositivas.getLayout()).setColumns(
                    e.getComponent().getWidth() / (Constantes.TAM_DIAPOSITIVA + 10));
		panel_diapositivas.doLayout();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
