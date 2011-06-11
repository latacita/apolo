package es.unican.moses.apolo.ui.widgets;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.unican.moses.apolo.ui.windows.VentanaSolicitaNombreONumeracion;


/**
 * Clase que ofrece una interfaz de usuario para la seleccion de ficheros 
 * de entrada o salida.
 * @author Angel
 *
 */
public class GUIEntradaSalida {
	
	/**
	 * Muestra un dialogo que ofrece la opcion de seleccionar una o varias fotos
	 * 
	 * ##Posibles Mejoras ## 
	 * - mostrar miniatura de cada foto
	 * 
	 * @param componente Componente que solicita los ficheros. (Para hacerlo Modal)
	 */
	public static File[] importar(Component componente){
		File[] files = null;
		//Seleccion de directorios o fotos
		JFileChooser seleccionador = new JFileChooser();
				//**Permite seleccionar directorios.
				//**seleccionador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//Seleccionar varios ficheros
		seleccionador.setMultiSelectionEnabled(true);
		//Filtro de archivos
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Y PNG", "jpg", "png");
		seleccionador.setFileFilter(filter);
		//Abrimos dialogo
		int estado = seleccionador.showOpenDialog(componente);
		
		//Opcion aceptada
		if (estado == JFileChooser.APPROVE_OPTION){
			files = new File[seleccionador.getSelectedFiles().length];
			int i = 0;
			for (File archivo : seleccionador.getSelectedFiles()){
				files[i]=archivo;
				i++;
			}
		}
		return files;
	}
	
	/**
	 * Guarda una lista de fotografias en un directorio solicitado
	 * @param lista
	 * @return
	 */
	public static String exportar(LinkedList<GUIListaDiapositivas> lista){	
		String ruta;
		int numero=0;
		DecimalFormat format;
		
		//Preguntar por nombre o simple numeracion
		String nombre;
		VentanaSolicitaNombreONumeracion solicitar = new VentanaSolicitaNombreONumeracion();
		nombre = solicitar.retorno();
		
		//Lanzar seleccionador de carpeta
		JFileChooser seleccionador = new JFileChooser(); 
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ruta = ""; 
		try{ 
			if(seleccionador.showDialog(null,"Exportar")==JFileChooser.APPROVE_OPTION){ 
				ruta = seleccionador.getSelectedFile().getAbsolutePath(); 
				LinkedList<File> listaFicheros = getRutasArchivos(lista);
				format = new DecimalFormat(obtenerMascaraFormato(lista.size()));
				File copia;
				for (File f : listaFicheros){
					if (nombre!=null)
						copia = new File(ruta + "\\" + nombre + format.format(numero) + f.getName().substring(f.getName().lastIndexOf(".")));
					else
						copia = new File(ruta + "\\" + format.format(numero) + f.getName().substring(f.getName().lastIndexOf(".")));
					copiar(f,copia);
					numero++;
				}
			} 
		}catch (Exception ex){ 
			ex.printStackTrace(); 
		} 
		
		return ruta;
	}
	
	/**
	 * Metodo privado que obtiene las rutas de las diapositivas
	 * @param lista
	 * @return
	 */
	private static LinkedList<File> getRutasArchivos(LinkedList<GUIListaDiapositivas> lista){
		LinkedList<File> listaFicheros = new LinkedList<File>();
		for (GUIListaDiapositivas gui_listaDiapositivas : lista){
			for (GUIDiapositiva gui_diapo : gui_listaDiapositivas.getLisDiapositivas()){
				listaFicheros.add(gui_diapo.getDiapositiva().getFichero());
			}
		}
		return listaFicheros;
	}
	
	/**
	 * Metodo privado que obtiene la mascara mas idonea para la creacion de nombres.
	 * Asi se ponene delante tantos 0 como sea necesario, pero no mas de la cuenta.
	 * @param cantidad
	 * @return
	 */
	private static String obtenerMascaraFormato(int cantidad){
		String mascara = "";
		for (int i = 0 ; i < String.valueOf(cantidad).length(); i++){
			mascara = mascara + "0";
		}
		return mascara;
	}
	
	/**
	 * Copia un archivo en otro
	 * @param copiado
	 * @param copia
	 * @throws IOException
	 */
	private static void copiar(File copiado, File copia) throws Exception {	
	    FileInputStream fis  = new FileInputStream(copiado);
	    FileOutputStream fos = new FileOutputStream(copia);
	    try {
	        byte[] buf = new byte[1024];
	        int i = 0;
	        while ((i = fis.read(buf)) != -1) {
	            fos.write(buf, 0, i);
	        }
	    } 
	    catch (Exception e) {
	        throw e;
	    }
	    finally {
	        if (fis != null) fis.close();
	        if (fos != null) fos.close();
	    }
	 }
}
