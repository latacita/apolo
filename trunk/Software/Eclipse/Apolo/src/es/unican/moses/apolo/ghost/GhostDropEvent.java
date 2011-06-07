package es.unican.moses.apolo.ghost;
import java.awt.Component;
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
	private Component componente;

	/**
	 * Constructor de la clase
	 * @param componente componente que se arrastro
	 * @param point punto
	 */
	public GhostDropEvent(Component componente, Point point) {
		this.componente = componente;
		this.point = point;
	}

	/**
	 * Devuelve el componente que se arrastro
	 * @return
	 */
	public Component getComponente() {
		return componente;
	}

	/**
	 * Devuelve la localizacion donde se solto
	 * @return
	 */
	public Point getDropLocation() {
		return point;
	}
}
