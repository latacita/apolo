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
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 * Clase GhostPictureAdapter
 * Extiende a GhostDropAdapter
 * Se expecializa en dibujar una imagen en el panel glass al arrastrar un componente 
 * Esta configurado para que se haga visible el panel glass
 * al presionar el boton izquierdo del raton. Y se oculte al dejar de presionar
 * el boton izquierdo del raton
 *
 */
public class GhostPictureAdapter extends GhostDropAdapter {
	private BufferedImage image;

	/**
	 * Constructor de la clase
	 * @param glassPane panel
	 * @param action accion
	 * @param picture imagen
	 */
	public GhostPictureAdapter(GhostGlassPane glassPane, String action, String picture) {
	   super(glassPane, action);
	   try {
	       this.image = ImageIO.read(new BufferedInputStream(GhostPictureAdapter.class.getResourceAsStream(picture)));
	   } catch (MalformedURLException mue) {
	       throw new IllegalStateException("Invalid picture URL.");
	   } catch (IOException ioe) {
           throw new IllegalStateException("Invalid picture or picture URL.");
       }
	}

	/**
     * Metodo que activa el panel glass
     * Dibuja la imagen en la coordenada en la que se presiono.
     */
    public void mousePressed(MouseEvent e)
    {
        Component c = e.getComponent();

        glassPane.setVisible(true);

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setImage(image);
        glassPane.repaint();
    }

    /**
     * Metodo que desactiva el panel glass
     * Y llama a los eventos que estan escuchando el arrastre
     */
    public void mouseReleased(MouseEvent e)
    {
        Component c = e.getComponent();

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);

        Point eventPoint = (Point) p.clone();
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setVisible(false);
        glassPane.setImage(null);

        fireGhostDropEvent(new GhostDropEvent(c, eventPoint));
    }
}