package es.unican.moses.apolo.logic;

import java.io.Serializable;
import java.util.LinkedList;

import es.unican.moses.apolo.logic.Diapositiva;

/**
 * Clase Balda
 * Representa el comportamiento interno de una balda
 * Usada para almacenar en fichero
 * @author Angel
 *
 */
public class Balda implements Serializable{
	
	/** AUTOGENERATE */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<Diapositiva> listaDiapositivas;
	private String descripcion;
	
	/**
	 * Constructor
	 * @param lista
	 */
	public Balda(LinkedList<Diapositiva> lista){
		listaDiapositivas = lista;
	}
	
	/**
	 * Constructor
	 * @param lista
	 * @param descr
	 */
	public Balda(LinkedList<Diapositiva> lista, String descr){
		listaDiapositivas = lista;
		descripcion = descr;
	}
	
	/**
	 * Devuelve una lista de diapositivas
	 * @return
	 */
	public LinkedList<Diapositiva> getListaDiapositivas() {
		return listaDiapositivas;
	}

	/**
	 * Asigna una lista de diapositivas
	 * @param listaDiapositivas
	 */
	public void setListaDiapositivas(LinkedList<Diapositiva> listaDiapositivas) {
		this.listaDiapositivas = listaDiapositivas;
	}
	
	/**
	 * Devuelve la descripcion
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/** 
	 * Asigna una descripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
