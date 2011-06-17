package es.unican.moses.apolo.logic;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * clase Album
 * Representa el estado de un album
 * Usada para almacenarla en un fichero
 * @author Angel
 *
 */
public class Album implements Serializable{
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<Diapositiva> listaDiapositivas;
	private String descripion;
	
	/**
	 * Constructor que recibe la lista de fotos
	 * @param lista
	 * @param desc
	 */
	public Album(LinkedList<Diapositiva> lista, String desc){
		listaDiapositivas = lista;
		descripion = desc;
	}
	
	/**
	 * Devuelve la lista de diapositivas
	 * @return
	 */
	public LinkedList<Diapositiva> getListaDiapositivas() {
		return listaDiapositivas;
	}
	
	/**
	 * Asigna la lista de diapositivas
	 * @param listaDiapositivas
	 */
	public void setListaDiapositivas(LinkedList<Diapositiva> listaDiapositivas) {
		this.listaDiapositivas = listaDiapositivas;
	}

	/**
	 * Devuelve la descricion del album
	 * @return
	 */
	public String getDescripion() {
		return descripion;
	}
	
	/**
	 * Asigna una descripcion al album
	 * @param descripion
	 */
	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}
	
	

}
