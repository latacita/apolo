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

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Clase GhostGlassPane
 * Extiende a JPanel. Es el panel translucido donde se pintara
 * las diapositivas arrastradas.
 * Contiene un buffer en el que se guarda la imagen que sera
 * dibujada en x coordenadas conforme se va arrastrando la diapositiva
 *
 */
public class GhostGlassPane extends JPanel {
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private AlphaComposite composite;
    private BufferedImage dragged = null;
    private Point location = new Point(0, 0);
    
    /**
     * Constructor de la clase
     */
    public GhostGlassPane()
    {
        setOpaque(false);
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    }

    /**
     * Asigna una imagen en el buffer
     * @param dragged
     */
    public void setImage(BufferedImage dragged)
    {
        this.dragged = dragged;
    }

    /**
     * Asigna la posicion
     * @param location
     */
    public void setPoint(Point location)
    {
        this.location = location;
    }

    /**
     * Dibuja el buffer en la posicion.
     */
    public void paintComponent(Graphics g)
    {
        if (dragged == null)
            return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(composite);
        g2.drawImage(dragged,
                     (int) (location.getX() - (dragged.getWidth(this)  / 2)),
                     (int) (location.getY() - (dragged.getHeight(this) / 2)),
                     null);
    }
}