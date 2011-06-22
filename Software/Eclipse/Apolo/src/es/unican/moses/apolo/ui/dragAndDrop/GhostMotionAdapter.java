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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingUtilities;

/**
 * Clase GhostMotionAdapter
 * Extiende a MouseMotionAdapter. Captura el evento del raton
 * que corresponde al arrastre de este mientras esta pulsado.
 * Cuando es arrastrado posiciona una nueva localizacion en el 
 * panel glass.
 *
 */
public class GhostMotionAdapter extends MouseMotionAdapter {
	/** Panel Glass */
    private GhostGlassPane glassPane;

    /**
     * Constructor de la clase
     * @param glassPane Panel glass
     */
	public GhostMotionAdapter(GhostGlassPane glassPane) {
		this.glassPane = glassPane;
	}

	/**
	 * Metodo que se ejecuta cuando el raton se mueve
	 * Posiciona unas coordenadas en el glasspanel 
	 * y le repinta.
	 */
	public void mouseDragged(MouseEvent e)
    {
        Component c = e.getComponent();

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, glassPane);
        glassPane.setPoint(p);

        glassPane.repaint();
    }
}