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

/**
 * Clase PaqueteDatos
 * Contiene los datos que se almacenaron el en fichero
 * Utilizada como cabecera de los ficheros.
 * @author Angel
 *
 */
public class PaqueteDatos implements Serializable{
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private boolean mesa;
	private boolean estanteria;
	private boolean album;
	
	/**
	 * Constructor
	 */
	public PaqueteDatos(){
		mesa = false;
		estanteria = false;
		album = false;
	}
	
	/**
	 * Retorna se la mesa esta escrita
	 * @return
	 */
	public boolean isMesa() {
		return mesa;
	}
	
	/**
	 * Asigna si la mesa esta escrita 
	 * @param mesa
	 */
	public void setMesa(boolean mesa) {
		this.mesa = mesa;
	}
	
	/**
	 * Devuelve si la estanteria esta escrita
	 * @return
	 */
	public boolean isEstanteria() {
		return estanteria;
	}
	
	/**
	 * Asigna si la estanteria esta escrita
	 * @param estanteria
	 */
	public void setEstanteria(boolean estanteria) {
		this.estanteria = estanteria;
	}
	
	/**
	 * Devuelve si el albuum esta escrito
	 * @return
	 */
	public boolean isAlbum() {
		return album;
	}
	
	/**
	 * Asigna si el album esta escrito
	 * @param album
	 */
	public void setAlbum(boolean album) {
		this.album = album;
	}
}
