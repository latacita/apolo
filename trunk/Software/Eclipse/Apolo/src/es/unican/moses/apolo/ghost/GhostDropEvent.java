package es.unican.moses.apolo.ghost;
import java.awt.Point;

/**
 * Clase GhostDropEvent.
 * Representa el evento que se desencadena al soltar un componente 
 * despues de haber sido arrastrado.
 *
 */
public class GhostDropEvent {
	/**
	 * Punto donde se suelta
	 */
	private Point point;
	/**
	 * Accion
	 */
	private String action;

	/**
	 * Constructor de la clase
	 * @param action accion
	 * @param point punto
	 */
	public GhostDropEvent(String action, Point point) {
		this.action = action;
		this.point = point;
	}

	/**
	 * Devuelve la accion que se hizo
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Devuelve la localizacion donde se solto
	 * @return
	 */
	public Point getDropLocation() {
		return point;
	}
}
