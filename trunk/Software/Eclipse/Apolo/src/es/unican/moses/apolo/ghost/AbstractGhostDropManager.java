package es.unican.moses.apolo.ghost;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;



/**
 * Clase AbstractGhostDropManager
 * Clase absatracta que implementa GhostDropListener
 * Servira para controlar los objetos sobre los que se suelta algo.
 *
 */
public abstract class AbstractGhostDropManager implements GhostDropListener {
	
	/**
	 * Objeto que se monitoriza el soltar algo en el
	 */
	protected JComponent component;

	
	/**
	 * Constructor de la clase nulo
	 */
	public AbstractGhostDropManager() {
		this(null);
	}
	
	/**
	 * Constructor de la clase
	 * Se le pasa el componente que se debe monitorizar
	 * @param component
	 */
	public AbstractGhostDropManager(JComponent component) {
		this.component = component;
	}

	/**
	 * Convierte el punto dado al sistema de coordenadas del componente monitorizado
	 * @param point
	 * @return
	 */
	protected Point getTranslatedPoint(Point point) {
        Point p = (Point) point.clone();
        SwingUtilities.convertPointFromScreen(p, component);
		return p;
	}

	/**
	 * Devuelve true si el punto esta contenido dentro del espacio que utiliza el componente
	 * @param point
	 * @return
	 */
	protected boolean isInTarget(Point point) {
		Rectangle bounds = component.getBounds();
		//return bounds.contains(point); No funciona, por q?¿
        boolean  booleano = false;
        if (point.getX()> 0 && point.getX()< bounds.getWidth() && point.getY() > 0 && point.getY() < bounds.getHeight()){
        	booleano = true;
        }
        return booleano;
	}

	/**
	 * Metodo que se llamara cuando algo se suelte. 
	 */
	public void ghostDropped(GhostDropEvent e) {
	}
}