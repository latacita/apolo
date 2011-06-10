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
	public static File FICHERO_MARCO = new File("img\\HDiapositiva.png");
	/** Diapositiva por defecto */
	public static File DIAPO_DEFAULT = new File("img\\Yo.jpg");
	/** Ruta Icono addAlbum */
	public static String RUTA_ICON_ADDALBUM = "img\\anadir.png";
	/** Ruta Icono removeAlbum */
	public static String RUTA_ICON_REMOVEALBUM = "img\\descartar.png";
	/** Ruta Icono moreActions */
	public static String RUTA_ICON_DESCRIPTION = "img\\description.png";
	/** Ruta Icono cargar */
	public static String RUTA_ICON_CARGAR = "img\\open.png";
	/** Ruta Icono guardar */
	public static String RUTA_ICON_GUARDAR = "img\\save.png";
	/** Ruta Icono importar */
	public static String RUTA_ICON_IMPORTAR = "img\\import.png";
	/** Ruta Icono exportar */
	public static String RUTA_ICON_EXPORTAR = "img\\export.png";
	/** Ruta Icono marco foto album */
	public static String RUTA_MARCO_ALBUM = "img\\marco.png";
}
