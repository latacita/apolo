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