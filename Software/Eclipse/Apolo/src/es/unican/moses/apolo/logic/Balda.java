package es.unican.moses.apolo.logic;

import java.io.Serializable;
import java.util.LinkedList;

import es.unican.moses.apolo.logic.Diapositiva;

public class Balda implements Serializable{
	
	/** AUTOGENERATE */
	private static final long serialVersionUID = 1L;
	private LinkedList<Diapositiva> listaDiapositivas;
	private String descripcion;
	
	public Balda(LinkedList<Diapositiva> lista){
		listaDiapositivas = lista;
	}
	
	public Balda(LinkedList<Diapositiva> lista, String descr){
		listaDiapositivas = lista;
		descripcion = descr;
	}

	public LinkedList<Diapositiva> getListaDiapositivas() {
		return listaDiapositivas;
	}

	public void setListaDiapositivas(LinkedList<Diapositiva> listaDiapositivas) {
		this.listaDiapositivas = listaDiapositivas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
