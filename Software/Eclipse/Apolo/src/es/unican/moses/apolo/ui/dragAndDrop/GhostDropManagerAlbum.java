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
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JComponent;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIPaqueteDiapositivas;


/**
 * Clase GhostDropManagerDemo
 * Clase de prueba que lanza un mensaje al comprobar que se solto
 * encima un componente.
 *
 */
public class GhostDropManagerAlbum extends AbstractGhostDropManager{
	/**
	 * Componente a monitorizar
	 */
	//private JComponent target;
	private GUIAlbum gui_album;

	/**
	 * Constructor de la clase
	 * @param target
	 */
    public GhostDropManagerAlbum(JComponent target, GUIAlbum gui_album) {
        super(target);
        this.gui_album = gui_album;
    }

    /**
     * Metodo que calcula la zona donde se deposito el objeto y lo coloca en el sitio que le corresponde 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   Component componente = e.getComponente();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p) && componente instanceof GUIPaqueteDiapositivas) {
		   //Obtencion de diapositiva

		   //Se solto en la balda, ahora a averiguar el lugar donde se solto. Lo indica la X
		   int lugar = p.x;
		   
		   LinkedList<GUIPaqueteDiapositivas> lista = new LinkedList<GUIPaqueteDiapositivas>();
		   for (Component c : gui_album.getVisor().getComponents()){
			   if (c instanceof GUIPaqueteDiapositivas) lista.add((GUIPaqueteDiapositivas)c);
		   }
		   gui_album.getVisor().removeAll();
		   if (lista.size()!=0){
			   Iterator<GUIPaqueteDiapositivas> iterador = lista.iterator();
			   int paso = 170 + 5;
			   int total= 90;
			   boolean insertado = false;
			   do{
				   if (lugar < total && !insertado){
					   gui_album.addGUIListaDiapositivas((GUIPaqueteDiapositivas) componente);
					   insertado = true;
				   }else{
					   GUIPaqueteDiapositivas listaDiapo = iterador.next();
					   if (listaDiapo != (GUIPaqueteDiapositivas) componente)
						   gui_album.addGUIListaDiapositivas(listaDiapo);
				   }
				   total = total + paso;
			   }while(iterador.hasNext());
			   if (!insertado){
				   gui_album.addGUIListaDiapositivas((GUIPaqueteDiapositivas) componente);
			   }
		   }   
		   
		   gui_album.validate();
	   }
	}
}