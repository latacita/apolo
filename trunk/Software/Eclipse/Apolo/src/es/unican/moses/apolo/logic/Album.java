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
