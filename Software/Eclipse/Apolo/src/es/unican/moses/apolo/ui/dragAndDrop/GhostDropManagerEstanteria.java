package es.unican.moses.apolo.ui.dragAndDrop;

import java.awt.Component;
import java.awt.Point;
import javax.swing.JComponent;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIBalda;
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;


/**
 * Clase GhostDropManagerDemo
 * Clase de prueba que lanza un mensaje al comprobar que se solto
 * encima un componente.
 *
 */
public class GhostDropManagerEstanteria extends AbstractGhostDropManager {
    
	/**
	 * Componente a monitorizar
	 */
	//private JComponent target;
	private GUIBalda gui_estanteria;

	/**
	 * Constructor de la clase
	 * @param target
	 */
    public GhostDropManagerEstanteria(JComponent target, GUIBalda gui_estanteria) {
        super(target);
        this.gui_estanteria = gui_estanteria;
    }

    /**
     * Metodo que muestra un mensaje si se detecta el se solto algo 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   Component componente = e.getComponente();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p)) {
	       gui_estanteria.addGUIDiapositiva((GUIDiapositiva) componente);
	       gui_estanteria.validate();
	   }
	}
}