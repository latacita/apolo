package es.unican.moses.apolo.ui.dragAndDrop;

import java.awt.Component;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComponent;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIListaDiapositivas;


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
     * Metodo que muestra un mensaje si se detecta el se solto algo 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   Component componente = e.getComponente();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p) && componente instanceof GUIListaDiapositivas) {
		   //Obtencion de diapositiva

		   //Se solto en la balda, ahora a averiguar el lugar donde se solto. Lo indica la X
		   int lugar = p.x;
		   
		   LinkedList<GUIListaDiapositivas> lista = new LinkedList<GUIListaDiapositivas>();
		   for (Component c : gui_album.getVisor().getComponents()){
			   if (c instanceof GUIListaDiapositivas) lista.add((GUIListaDiapositivas)c);
		   }
		   gui_album.getVisor().removeAll();
		   if (lista.size()!=0){
			   Iterator<GUIListaDiapositivas> iterador = lista.iterator();
			   int paso = Constantes.TAM_DIAPOSITIVA + 5;
			   int total= 80;
			   boolean insertado = false;
			   do{
				   if (lugar < total && !insertado){
					   gui_album.addGUIListaDiapositivas((GUIListaDiapositivas) componente);
					   insertado = true;
				   }else{
					   GUIListaDiapositivas listaDiapo = iterador.next();
					   if (listaDiapo != (GUIListaDiapositivas) componente)
						   gui_album.addGUIListaDiapositivas(listaDiapo);
				   }
				   total = total + paso;
			   }while(iterador.hasNext());
			   if (!insertado){
				   gui_album.addGUIListaDiapositivas((GUIListaDiapositivas) componente);
			   }
		   }   
		   
		   gui_album.validate();
	   }
	}
}