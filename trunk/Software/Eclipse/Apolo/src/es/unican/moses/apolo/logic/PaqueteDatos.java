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
