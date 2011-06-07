package es.unican.moses.apolo.logic;

import java.io.File;
import javax.swing.ImageIcon;

/**
 * Clase Diapositiva
 * Representa una diapositiva
 * Se almacenan:
 * -Fichero que representa
 * -Nombre
 * -Numero
 * -Anchura
 * -Altura
 * 
 * @author Angel
 *
 */
public class Diapositiva {
	
	/** Atributos */
	private File fichero;
	private String nombre;
	private int numero;
	private int anchura;
	private int altura;
	
	/**
	 * Contructores de la clase
	 */
	public Diapositiva(File fichero){
		this(fichero, 0);
	}
	public Diapositiva(File fichero, int numero){
		this.fichero = fichero;
		this.numero = numero;
		this.nombre = fichero.getName();
		//Cargamos la imagen para obtener su altura y anchura. Solo conservamos estos valores.
		ImageIcon diapositiva = new ImageIcon(fichero.getAbsolutePath());
		anchura = diapositiva.getIconWidth();
		altura = diapositiva.getIconHeight();
	}

	/*Getters and setters */
	
	/**
	 * Devuelve el fichero que representa
	 * @return fichero
	 */
	public File getFichero() {
		return fichero;
	}

	/**
	 * Devuelve el nombre de la diapositiva
	 * @return nombre del fichero
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve el numero de la diapositiva
	 * @return Numero de la diapositiva
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Asigna en numero de la diapositiva
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Devuelve la anchura de la fotografia
	 * @return anchura
	 */
	public int getAnchura() {
		return anchura;
	}

	/**
	 * Devuelve la altura de la diapositiva
	 * @return altura
	 */
	public int getAltura() {
		return altura;
	}
}
