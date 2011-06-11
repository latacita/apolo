package es.unican.moses.apolo.ui.dragAndDrop;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Clase GhostDropAdapter
 * Extiende a MouseAdapter
 * @author Angel
 *
 */
public class GhostDropAdapter extends MouseAdapter {
    protected GhostGlassPane glassPane;
	protected String action;

	private List<GhostDropListener> listeners;

	/**
	 * Constructor de la clase 
	 */
	public GhostDropAdapter(GhostGlassPane glassPane, String action) {
        this.glassPane = glassPane;
        this.action = action;
        this.listeners = new ArrayList<GhostDropListener>();
    }

    /**
     * Metodo que añade un oyente
     * @param listener
     */
	public void addGhostDropListener(GhostDropListener listener) {
        if (listener != null)
            listeners.add(listener);
    }

    /**
     * Metodo que elimina un oyente
     * @param listener
     */
    public void removeGhostDropListener(GhostDropListener listener) {
    	if (listener != null)
            listeners.remove(listener);
    }

    /**
     * Forward the provided drop event to all registered listeners
     * Creo que lanza eventos por si alguien se da por aludido
     * @param evt
     */
    protected void fireGhostDropEvent(GhostDropEvent evt) {
        @SuppressWarnings("rawtypes")
		Iterator it = listeners.iterator();
        while (it.hasNext()) {
        	((GhostDropListener) it.next()).ghostDropped(evt);
        }
    }
}