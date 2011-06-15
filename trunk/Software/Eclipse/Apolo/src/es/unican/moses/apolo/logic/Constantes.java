package es.unican.moses.apolo.logic;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

/**
 * Clase con las contantes de la aplicacion Apolo
 * @author Angel
 *
 */
public class Constantes implements Serializable {
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	/** Separador del sistema */// No hace falta, 
	//public static String separador = System.getProperty("file.separator");
	/** RUTA IMAGENES */
	public static String rutaImagenes = "img/";// + separador;
	/** DEBUG */
	public static boolean DEBUG = true;
	/** Tamaño de las diapositivas (Son Cuadradas) */
	public static int TAM_DIAPOSITIVA = 150;
	/** Fichero que contiene el Marco */
	public static File FICHERO_MARCO = new File(rutaImagenes + "HDiapositiva.png");
	/** Diapositiva por defecto */
	public static File DIAPO_DEFAULT = new File(rutaImagenes + "Yo.jpg");
	/** Ruta Icono addAlbum */
	public static String RUTA_ICON_ADDALBUM = rutaImagenes + "anadir.png";
	/** Ruta Icono removeAlbum */
	public static String RUTA_ICON_REMOVEALBUM = rutaImagenes + "descartar.png";
	/** Ruta Icono moreActions */
	public static String RUTA_ICON_DESCRIPTION = rutaImagenes + "description.png";
	/** Ruta Icono cargar */
	public static String RUTA_ICON_CARGAR = rutaImagenes + "open.png";
	/** Ruta Icono guardar */
	public static String RUTA_ICON_GUARDAR = rutaImagenes + "save.png";
	/** Ruta Icono importar */
	public static String RUTA_ICON_IMPORTAR = rutaImagenes + "import.png";
	/** Ruta Icono exportar */
	public static String RUTA_ICON_EXPORTAR = rutaImagenes + "export.png";
	/** Ruta Icono album */
	public static String RUTA_ICONO_ALBUM_FRONT = rutaImagenes + "folderFront.png";
	public static String RUTA_ICONO_ALBUM_BACK = rutaImagenes + "folderBack.png";
	/** Ruta Iconos Menu */
	public static String RUTA_ICONO_MENU_ABRIR = rutaImagenes + "open_16.png";
	public static String RUTA_ICONO_MENU_GUARDAR = rutaImagenes + "save_16.png";
	public static String RUTA_ICONO_MENU_IMPORTAR = rutaImagenes + "import_16.png";
	public static String RUTA_ICONO_MENU_EXPORTAR = rutaImagenes + "export_16.png";
	public static String RUTA_ICONO_MENU_CERRAR = rutaImagenes + "close_16.png";
	public static String RUTA_ICONO_MENU_MANUAL = rutaImagenes + "book_16.png";
	public static String RUTA_ICONO_MENU_WEB = rutaImagenes + "web_16.png";
	public static String RUTA_ICONO_MENU_ACERCADE = rutaImagenes + "Apolo_16.png";
	/** URL del Apolo */
	public static String RUTA_WEB_APOLO = "http://code.google.com/p/apolo/";
	/** Ruta manual Apolo */
	public static String RUTA_MANUAL_APOLO = "manual_apolo.pdf";
	/** Icono programa */
	public static String RUTA_ICONO_APOLO = rutaImagenes + "Apolo.png";
	/** Iconos menu GUIListaDiapositivas */
	public static String RUTA_ICONO_MEN_LISTAGUID_CAMDES = rutaImagenes + "camDescr_16.png";
	public static String RUTA_ICONO_MEN_LISTAGUID_DESEMPA = rutaImagenes + "desempaqueta_16.png";
	/** Iconos menu GUIDiapositivas */
	public static String RUTA_ICONO_MEN_GUIDIA_ELIMINAR = rutaImagenes + "delete_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_UPDATE = rutaImagenes + "update_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_PROPIEDADES = rutaImagenes + "propiedades_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_VIEW = rutaImagenes + "view_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_EDITAR = rutaImagenes + "edit_16.png";
	
	private static File WORKING_DIRECTORY;
	public static File get() {
	    String Recurso = Constantes.class.getSimpleName() + ".class";
	    if (WORKING_DIRECTORY == null) {
	        try {
	            URL url = Constantes.class.getResource(Recurso);
	            if (url.getProtocol().equals("file")) {
	                File f = new File(url.toURI());
	                do {
	                    f = f.getParentFile();
	                } while (!f.isDirectory());
	                WORKING_DIRECTORY = f;
	            } else if (url.getProtocol().equals("jar")) {
	                String expected = "!/" + Recurso;
	                String s = url.toString();
	                s = s.substring(4);
	                s = s.substring(0, s.length() - expected.length());
	                File f = new File(new URL(s).toURI());
	                do {
	                    f = f.getParentFile();
	                } while (!f.isDirectory());
	                WORKING_DIRECTORY = f;
	            }
	        } catch (Exception e) {
	            WORKING_DIRECTORY = new File(".");
	        }
	    }
	    return WORKING_DIRECTORY;
	}
	
}
