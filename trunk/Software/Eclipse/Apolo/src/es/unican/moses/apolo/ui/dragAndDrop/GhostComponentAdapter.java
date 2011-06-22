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
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;



/**
 * Clase GhostComponentAdapter
 * Extiende a GhostDropAdapter
 * Se expecializa en dibujar el componente que es arrastrado en el panel glass
 * Esta configurado para que se haga visible el panel glass
 * al presionar el boton izquierdo del raton. Y se oculte al dejar de presionar
 * el boton izquierdo del raton
 *
 */
public class GhostComponentAdapter extends GhostDropAdapter{

	/**
	 * Construcctor de la clase
	 * @param glassPane
	 * @param action
	 */
    public GhostComponentAdapter(GhostGlassPane glassPane, String action) {
        super(glassPane, action);
    }
    
    /**
     * Metodo que activa el panel glass
     * Dibuja el componente en la coordenada en la que se presiono.
     */
    public void mousePressed(MouseEvent e)
    {
    	if(!e.isMetaDown()){ //Solo boton izquierdo
    		
    		Component c = e.getComponent();
    		BufferedImage image;
    		if (c instanceof GUIDiapositiva){
    			image = new BufferedImage(Constantes.TAM_DIAPOSITIVA, Constantes.TAM_DIAPOSITIVA, BufferedImage.TYPE_INT_ARGB);
    		}else{
    			image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
    		}
    		Graphics g = image.getGraphics();
    		c.paint(g);

    		glassPane.setVisible(true);

    		Point p = (Point) e.getPoint().clone();
    		SwingUtilities.convertPointToScreen(p, c);
    		SwingUtilities.convertPointFromScreen(p, glassPane);

    		glassPane.setCursor(Constantes.CURSOR_ARRASTRE);
    		
    		glassPane.setPoint(p);
    		glassPane.setImage(image);//.getSubimage(0, 0, Constantes.TAM_DIAPOSITIVA, Constantes.TAM_DIAPOSITIVA));
    		glassPane.repaint();
    	}
    }

    /**
     * Metodo que desactiva el panel glass
     * Y llama a los eventos que estan escuchando el arrastre
     */
    public void mouseReleased(MouseEvent e)
    {
    	if(!e.isMetaDown()){ //Solo boton izquierdo

   			Component c = e.getComponent();

   			Point p = (Point) e.getPoint().clone();
   			SwingUtilities.convertPointToScreen(p, c);

   			Point eventPoint = (Point) p.clone();
   			SwingUtilities.convertPointFromScreen(p, glassPane);

   			glassPane.setPoint(p);
   			glassPane.setVisible(false);
   			glassPane.setImage(null);
   			
   			//Lanza eventos por si alguien se da por aludido
   			fireGhostDropEvent(new GhostDropEvent(c, eventPoint));
   		}
    }
}