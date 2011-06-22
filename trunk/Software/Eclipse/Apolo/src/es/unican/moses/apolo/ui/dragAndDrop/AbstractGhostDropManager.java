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
		return bounds.contains(point); 
		//Antes de funcionar el bounds
        /*boolean  booleano = false;
        if (point.getX()> 0 && point.getX()< bounds.getWidth() && point.getY() > 0 && point.getY() < bounds.getHeight()){
        	booleano = true;
        }
        return booleano;*/
	}

	/**
	 * Metodo que se llamara cuando algo se suelte. 
	 */
	public void ghostDropped(GhostDropEvent e) {
	}
}