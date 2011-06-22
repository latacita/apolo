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

//Paquetes propios
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
