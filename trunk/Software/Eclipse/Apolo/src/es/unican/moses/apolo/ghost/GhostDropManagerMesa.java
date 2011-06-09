package es.unican.moses.apolo.ghost;

import java.awt.Component;
import java.awt.Point;
import javax.swing.JComponent;

//Paquetes propios
import es.unican.moses.apolo.gui.GUIDiapositiva;
import es.unican.moses.apolo.gui.GUIMesa;


/**
 * Clase GhostDropManagerDemo
 * Clase de prueba que lanza un mensaje al comprobar que se solto
 * encima un componente.
 *
 */
public class GhostDropManagerMesa extends AbstractGhostDropManager {
    
	/**
	 * Componente a monitorizar
	 */
	//private JComponent target;
	private GUIMesa gui_mesa;

	/**
	 * Constructor de la clase
	 * @param target
	 */
    public GhostDropManagerMesa(JComponent target, GUIMesa gui_mesa) {
        super(target);
        this.gui_mesa = gui_mesa;
    }

    /**
     * Metodo que muestra un mensaje si se detecta el se solto algo 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   Component componente = e.getComponente();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p)) {
		   gui_mesa.addGUIDiapositivia((GUIDiapositiva) componente);
		   gui_mesa.repaint();
	   }
	}
}