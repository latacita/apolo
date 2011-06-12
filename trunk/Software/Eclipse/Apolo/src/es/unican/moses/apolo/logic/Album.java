package es.unican.moses.apolo.logic;

import java.io.Serializable;
import java.util.LinkedList;

public class Album implements Serializable{
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	private LinkedList<Diapositiva> listaDiapositivas;
	private String descripion;
	
	public Album(LinkedList<Diapositiva> lista, String desc){
		listaDiapositivas = lista;
		descripion = desc;
	}

	public LinkedList<Diapositiva> getListaDiapositivas() {
		return listaDiapositivas;
	}

	public void setListaDiapositivas(LinkedList<Diapositiva> listaDiapositivas) {
		this.listaDiapositivas = listaDiapositivas;
	}

	public String getDescripion() {
		return descripion;
	}

	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}
	
	

}
