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
public class GhostDropAdapter extends MouseAdapter{
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
     * Metodo que devuelve el numero de oyentes
     */
    public int size(){
    	return listeners.size();
    }
    
   /**
    * Metodo que elimina todas las baldas oyentes,
    * Dejando solo el album
    */
    public void removeAllBaldas(){
    	List<GhostDropListener> listenersEstan = new ArrayList<GhostDropListener>();
    	for (GhostDropListener g : listeners){
    		if(g instanceof GhostDropManagerAlbum){ 
    			listenersEstan.add(g);
    		}
    	}
    	listeners = listenersEstan;
    }
    
    /**
     * Devuelve la lista de listeners
     * @return
     */
    public List<GhostDropListener> getli(){
    	return listeners;
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