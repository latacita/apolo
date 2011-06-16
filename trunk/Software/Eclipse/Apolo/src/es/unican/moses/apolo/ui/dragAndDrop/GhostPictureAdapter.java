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