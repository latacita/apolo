package es.unican.moses.apolo.logic;

import java.util.LinkedList;


/**
 * Clase Mesa
 * Representa la mesa donde se depositan diapositivas
 * para luego ser ordenadas y clasificadas.
 * Contiene una lista donde se almacenan.
 * @author Angel
 *
 */
public class Mesa{
	
	/** */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributos de la clase
	 */
	private LinkedList<Diapositiva> listaDiapositivas;
	
	
	/**
	 * Constructor
	 */
	public Mesa(){
		listaDiapositivas = new LinkedList<Diapositiva>();
	}
	
	/**
	 * Añade una diapositiva
	 * @param diapo Diapositiva a añadir
	 */
	public void addDiapositiva(Diapositiva diapo){
		listaDiapositivas.add(diapo);
	}
	
	/**
	 * Elimina una diapositiva
	 * @param diapo Diapositiva a eliminar
	 */
	public void removeDiapositiva(Diapositiva diapo){
		listaDiapositivas.remove(diapo);
	}
	
	/**
	 * Devuelve el numero de diapositivas que tiene la mesa
	 * @return Numero de diapositivas
	 */
	public int getNumeroDiapositivas(){
		return listaDiapositivas.size();
	}
	
	/**
	 * Retorna la posicion que ocupa diapo
	 * @param diapo Diapositiva a localizar
	 * @return posicion
	 */
	public int getIndexOf(Diapositiva diapo){
		return listaDiapositivas.indexOf(diapo);
	}
	
	/**
	 * Devuelve la diapositiva posicionada en index
	 * @param index
	 * @return
	 */
	public Diapositiva getDiapositiva(int index){
		return listaDiapositivas.get(index);
	}
}
