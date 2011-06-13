package es.unican.moses.apolo.logic;

import java.io.File;
import java.io.Serializable;

/**
 * Clase con las contantes de la aplicacion Apolo
 * @author Angel
 *
 */
public class Constantes implements Serializable {
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
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
	/** Ruta Icono album */
	public static String RUTA_ICONO_ALBUM_FRONT = "img\\folderFront.png";
	public static String RUTA_ICONO_ALBUM_BACK = "img\\folderBack.png";
	/** Ruta Iconos Menu */
	public static String RUTA_ICONO_MENU_ABRIR = "img\\open_16.png";
	public static String RUTA_ICONO_MENU_GUARDAR = "img\\save_16.png";
	public static String RUTA_ICONO_MENU_IMPORTAR = "img\\import_16.png";
	public static String RUTA_ICONO_MENU_EXPORTAR = "img\\export_16.png";
	public static String RUTA_ICONO_MENU_CERRAR = "img\\close_16.png";
	public static String RUTA_ICONO_MENU_MANUAL = "img\\book_16.png";
	public static String RUTA_ICONO_MENU_WEB = "img\\web_16.png";
	public static String RUTA_ICONO_MENU_ACERCADE = "img\\Apolo_16.png";
	/** URL del Apolo */
	public static String RUTA_WEB_APOLO = "http://code.google.com/p/apolo/";
	/** Ruta manual Apolo */
	public static String RUTA_MANUAL_APOLO = "manual_apolo.pdf";
	/** Icono programa */
	public static String RUTA_ICONO_APOLO = "img\\Apolo.png";
	/** Iconos menu GUIListaDiapositivas */
	public static String RUTA_ICONO_MEN_LISTAGUID_CAMDES = "img\\camDescr_16.png";
	public static String RUTA_ICONO_MEN_LISTAGUID_DESEMPA = "img\\desempaqueta_16.png";
	/** Iconos menu GUIDiapositivas */
	public static String RUTA_ICONO_MEN_GUIDIA_ELIMINAR = "img\\delete_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_UPDATE = "img\\update_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_PROPIEDADES = "img\\propiedades_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_VIEW = "img\\view_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_EDITAR = "img\\edit_16.png";
}
