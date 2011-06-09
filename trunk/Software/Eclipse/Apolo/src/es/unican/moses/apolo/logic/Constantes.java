package es.unican.moses.apolo.logic;

import java.io.File;

/**
 * Clase con las contantes de la aplicacion Apolo
 * @author Angel
 *
 */
public class Constantes {
	/** DEBUG */
	public static boolean DEBUG = true;
	/** Tamaño de las diapositivas (Son Cuadradas) */
	public static int TAM_DIAPOSITIVA = 150;
	/** Fichero que contiene el Marco */
	public static File FICHERO_MARCO = new File("img/HDiapositiva.png");
	/** Diapositiva por defecto */
	public static File DIAPO_DEFAULT = new File("img/Yo.jpg");
	/** Ruta Icono addAlbum */
	public static String RUTA_ICON_ADDALBUM = "C:\\Users\\Angel\\workspace\\pruebas\\img\\anadir.png";
	/** Ruta Icono removeAlbum */
	public static String RUTA_ICON_REMOVEALBUM = "C:\\Users\\Angel\\workspace\\pruebas\\img\\descartar.png";
	/** Ruta Icono moreActions */
	public static String RUTA_ICON_MOREACTIONS = "C:\\Users\\Angel\\workspace\\pruebas\\img\\masacciones.png";
}
