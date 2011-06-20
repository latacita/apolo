package es.unican.moses.apolo.logic;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
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
	public static String ruta = get() + "/recursos/";// + separador;
	/** DEBUG */
	public static boolean DEBUG = true;
	/** Tamaño de las diapositivas (Son Cuadradas) */
	public static int TAM_DIAPOSITIVA = 150;
	/** Cursor de arrastre*/
	public static Cursor CURSOR_ARRASTRE = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(ruta + "cursorDrag.png"), new Point(10,10),"hand2");
	/** Fichero que contiene el Marco */
	public static File FICHERO_MARCO = new File(ruta + "HDiapositiva.png");
	/** Diapositiva por defecto */
	public static File DIAPO_DEFAULT = new File(ruta + "Yo.jpg");
	/** Ruta Icono addAlbum */
	public static String RUTA_ICON_ADDALBUM = ruta + "anadir.png";
	/** Ruta Icono removeAlbum */
	public static String RUTA_ICON_REMOVEALBUM = ruta + "descartar.png";
	/** Ruta Icono moreActions */
	public static String RUTA_ICON_DESCRIPTION = ruta + "description.png";
	/** Ruta Icono cargar */
	public static String RUTA_ICON_CARGAR = ruta + "open.png";
	/** Ruta Icono guardar */
	public static String RUTA_ICON_GUARDAR = ruta + "save.png";
	/** Ruta Icono importar */
	public static String RUTA_ICON_IMPORTAR = ruta + "import.png";
	/** Ruta Icono exportar */
	public static String RUTA_ICON_EXPORTAR = ruta + "export.png";
	/** Ruta Icono album */
	public static String RUTA_ICONO_ALBUM_FRONT = ruta + "folderFront.png";
	public static String RUTA_ICONO_ALBUM_BACK = ruta + "folderBack.png";
	/** Ruta Iconos Menu */
	public static String RUTA_ICONO_MENU_ABRIR = ruta + "open_16.png";
	public static String RUTA_ICONO_MENU_GUARDAR = ruta + "save_16.png";
	public static String RUTA_ICONO_MENU_IMPORTAR = ruta + "import_16.png";
	public static String RUTA_ICONO_MENU_EXPORTAR = ruta + "export_16.png";
	public static String RUTA_ICONO_MENU_CERRAR = ruta + "close_16.png";
	public static String RUTA_ICONO_MENU_MANUAL = ruta + "book_16.png";
	public static String RUTA_ICONO_MENU_WEB = ruta + "web_16.png";
	public static String RUTA_ICONO_MENU_ACERCADE = ruta + "Apolo_16.png";
	/** URL del Apolo */
	public static String RUTA_WEB_APOLO = "http://www.angeltezanos.com/Apolo";
	/** Ruta manual Apolo */
	public static String RUTA_MANUAL_APOLO = ruta + "manual_apolo.pdf";
	/** Icono programa */
	public static String RUTA_ICONO_APOLO = ruta + "Apolo.png";
	/** Iconos menu GUIListaDiapositivas */
	public static String RUTA_ICONO_MEN_LISTAGUID_CAMDES = ruta + "camDescr_16.png";
	public static String RUTA_ICONO_MEN_LISTAGUID_DESEMPA = ruta + "desempaqueta_16.png";
	/** Iconos menu GUIDiapositivas */
	public static String RUTA_ICONO_MEN_GUIDIA_ELIMINAR = ruta + "delete_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_UPDATE = ruta + "update_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_PROPIEDADES = ruta + "propiedades_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_VIEW = ruta + "view_16.png";
	public static String RUTA_ICONO_MEN_GUIDIA_EDITAR = ruta + "edit_16.png";
	
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
