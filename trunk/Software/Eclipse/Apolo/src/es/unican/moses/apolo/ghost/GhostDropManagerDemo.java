package es.unican.moses.apolo.ghost;

import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Clase GhostDropManagerDemo
 * Clase de prueba que lanza un mensaje al comprobar que se solto
 * encima un componente.
 *
 */
public class GhostDropManagerDemo extends AbstractGhostDropManager {
    
	/**
	 * Componente a monitorizar
	 */
	//private JComponent target;

	/**
	 * Constructor de la clase
	 * @param target
	 */
    public GhostDropManagerDemo(JComponent target) {
        super(target);
    }

    /**
     * Metodo que muestra un mensaje si se detecta el se solto algo 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   String action = e.getAction();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p)) {
	       JOptionPane.showMessageDialog(this.component, "Diapositiva soltada dentro de la zona" + action);
	   }
	}
}