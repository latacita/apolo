/*
Apolo is a digital photographs classification system inspired on 
traditional lightened classification boxes for slide classification.

Copyright (C) 2011  Angel Tezanos Ibañez

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package es.unican.moses.apolo.ui.dragAndDrop;

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
