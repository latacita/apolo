package es.unican.moses.apolo.logic;

import java.util.LinkedList;

/**
 * Clase Estanteria
 * Representa una estanteria donde se van depositando las diapositivas 
 * que se van ordenando o clasificando.
 * @author Angel
 *
 */
public class Estanteria {
	
	/** Lista donde se van añadiendo las diapositivas */
	LinkedList<Diapositiva> listaDiapositivasEstanteria;
	
	/**
	 * Constructor de la estanteria
	 */
	public Estanteria(){
		listaDiapositivasEstanteria = new LinkedList<Diapositiva>();
	}
	
	/**
	 * Añade una diapositiva a la estanteria
	 * @param diapo
	 */
	public void addDiapositiva(Diapositiva diapo){
		listaDiapositivasEstanteria.add(diapo);
	}
	
	/**
	 * Elimina una diapositiva de la estanteria
	 * @param diapo
	 */
	public void removeDiapositiva(Diapositiva diapo){
		listaDiapositivasEstanteria.remove(diapo);
	}
	
	/**
	 * Intercambia dos diapositivas de sitio
	 */
	public void intercambiarDiapositivas(){
		;
	}

}
