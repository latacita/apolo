package es.unican.moses.apolo.ui.widgets;

import java.io.File;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//Paquetes Propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.logic.Mesa;
import es.unican.moses.apolo.logic.ThreadAddDiapositiva;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.windows.VentanaDeEspera;


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
public class GUIMesa extends JPanel implements ComponentListener{
	
	/** Atributos*/
	private Mesa mesa;
	private static final long serialVersionUID = 1L;
	private GhostComponentAdapter componentAdapter;
	private GhostMotionAdapter motionAdapter;
	
	/** Componentes internos */
	private JScrollPane scrollPane;
	private JPanel panel_diapositivas;
	
	/**
	 * Constructor
	 */
	public GUIMesa(Mesa mesa, GhostComponentAdapter componentAdapter, GhostMotionAdapter motionAdapter) {
		this.mesa = mesa;
		this.componentAdapter = componentAdapter;
		this.motionAdapter = motionAdapter;
		inicializacionGUIMesa();
	}
	public GUIMesa(GhostComponentAdapter componentAdapter, GhostMotionAdapter motionAdapter){
		this.mesa = new Mesa();
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
		VentanaDeEspera VentanaEspera = new VentanaDeEspera(this);
		VentanaEspera.setVisible(true);
		ThreadAddDiapositiva hilo = new ThreadAddDiapositiva(this, files, VentanaEspera);
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
		
		//Añadir a la logica y el componente
		mesa.addDiapositiva(gui_diapositiva.getDiapositiva());
		panel_diapositivas.add(gui_diapositiva);
		//Actualizar vista
		validate();
	}
	
	/** Elimina una diapositiva*/
	public void removeDiapositiva(GUIDiapositiva diapo){
		//Eliminar de la logica
		mesa.removeDiapositiva(diapo.getDiapositiva());
		//Eliminar visualmente
		panel_diapositivas.remove(diapo);
		renumerar();
		validate();
	}
	
	/**
	 * Devuelve el numero de diapositivas que hay en la GUI_Mesa
	 * @return
	 */
	public int getNumeroDeapositivas(){
		return mesa.getNumeroDiapositivas();
	}
	
	/**
	 * Renumera las diapositivas de la mesa
	 */
	private void renumerar(){
		for (Component componente : panel_diapositivas.getComponents()){
			if (componente instanceof GUIDiapositiva){
				((GUIDiapositiva) componente).setNumeroGui(mesa.getIndexOf(((GUIDiapositiva) componente).getDiapositiva())+1);
			}
		}
	}
	
	/**
	 * Devuelve la lista de diapositivas que hay en la mesa
	 * @return
	 */
	public Mesa getMesa(){
		return mesa;
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
		validate();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
