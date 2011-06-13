package es.unican.moses.apolo.ui.dragAndDrop;

import java.awt.Component;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComponent;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.widgets.GUIBalda;
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;


/**
 * Clase GhostDropManagerDemo
 * Clase de prueba que lanza un mensaje al comprobar que se solto
 * encima un componente.
 *
 */
public class GhostDropManagerEstanteria extends AbstractGhostDropManager {
	
	/**
	 * Componente a monitorizar
	 */
	//private JComponent target;
	private GUIBalda gui_balda;

	/**
	 * Constructor de la clase
	 * @param target
	 */
    public GhostDropManagerEstanteria(JComponent target, GUIBalda gui_balda) {
        super(target);
        this.gui_balda = gui_balda;
    }

    /**
     * Metodo que muestra un mensaje si se detecta el se solto algo 
     * dentro de la localizacion del componente target
     */
	public void ghostDropped(GhostDropEvent e) {
	   Component componente = e.getComponente();
	   Point p = getTranslatedPoint(e.getDropLocation());

	   if (isInTarget(p)) {
		   //Obtencion de diapositiva
		   GUIDiapositiva gui_diapo = ((GUIDiapositiva)componente);
		   gui_diapo.cambiaPadre(gui_balda);
		   
		   //Se solto en la balda, ahora a averiguar el lugar donde se solto. Lo indica la X
		   int lugar = p.x;
		   
		   LinkedList<GUIDiapositiva> lista = new LinkedList<GUIDiapositiva>();
		   for (Component c : gui_balda.getVisor().getComponents()){
			   if (c instanceof GUIDiapositiva) lista.add((GUIDiapositiva)c);
		   }
		   gui_balda.getVisor().removeAll();
		   if (lista.size()==0){
			   gui_balda.addGUIDiapositiva((GUIDiapositiva) componente);
		   }else{ //Hay diapositivas
			   Iterator<GUIDiapositiva> iterador = lista.iterator();
			   int paso = Constantes.TAM_DIAPOSITIVA + 5;
			   int total= 80;
			   boolean insertado = false;
			   do{
				   if (lugar < total && !insertado){
					   gui_balda.addGUIDiapositiva((GUIDiapositiva) componente);
					   insertado = true;
				   }else{
					   GUIDiapositiva diapo = iterador.next();
					   if (diapo != (GUIDiapositiva) componente)
						   gui_balda.addGUIDiapositiva(diapo);
				   }
				   total = total + paso;
			   }while(iterador.hasNext());
			   if (!insertado){
				   gui_balda.addGUIDiapositiva((GUIDiapositiva) componente);
			   }
		   }   
		   
		   gui_balda.validate();
	   }
	}
}