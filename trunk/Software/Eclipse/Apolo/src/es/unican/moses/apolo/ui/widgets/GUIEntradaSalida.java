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

//Paquetes propios
import es.unican.moses.apolo.ui.windows.DialogoSolicitaNombreONumeracion;


/**
 * Clase que ofrece una interfaz de usuario para la seleccion de ficheros 
 * de entrada o salida.
 * @author Angel
 *
 */
public class GUIEntradaSalida{
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	
    /** Sirve para tener acceso al ultimo directorio usado */
    private static File ultimoDirectorio = null;
	public static File getUltimoDirectorio() {
		return ultimoDirectorio;
	}
	public static void setUltimoDirectorio(File ultimoDire) {
		ultimoDirectorio = ultimoDire;
	}

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
		seleccionador.setCurrentDirectory(ultimoDirectorio);
		seleccionador.setAcceptAllFileFilterUsed(false);
		//Seleccionar varios ficheros
		seleccionador.setMultiSelectionEnabled(true);
		//Filtro de archivos
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Y PNG", "jpg", "png");
		seleccionador.setFileFilter(filter);
		//Abrimos dialogo
		int estado = seleccionador.showOpenDialog(componente);
		
		//Opcion aceptada
		if (estado == JFileChooser.APPROVE_OPTION){
			setUltimoDirectorio(seleccionador.getCurrentDirectory());
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
	public static String exportar(LinkedList<GUIPaqueteDiapositivas> lista){	
		String ruta;
		int numero=0;
		DecimalFormat format;
		
		//Preguntar por nombre o simple numeracion
		String nombre;
		DialogoSolicitaNombreONumeracion solicitar = new DialogoSolicitaNombreONumeracion();
		nombre = solicitar.retorno();
		
		//Lanzar seleccionador de carpeta
		JFileChooser seleccionador = new JFileChooser(); 
		seleccionador.setCurrentDirectory(ultimoDirectorio);
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ruta = ""; 
		try{ 
			if(seleccionador.showDialog(null,"Exportar")==JFileChooser.APPROVE_OPTION){
				setUltimoDirectorio(seleccionador.getCurrentDirectory());
				ruta = seleccionador.getSelectedFile().getAbsolutePath(); 
				LinkedList<File> listaFicheros = getRutasArchivos(lista);
				format = new DecimalFormat(obtenerMascaraFormato(listaFicheros.size()));
				File copia;
				for (File f : listaFicheros){
					if (nombre!=null)
						copia = new File(ruta + "/" + nombre + format.format(numero) + f.getName().substring(f.getName().lastIndexOf(".")));
					else
						copia = new File(ruta + "/" + format.format(numero) + f.getName().substring(f.getName().lastIndexOf(".")));
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
	private static LinkedList<File> getRutasArchivos(LinkedList<GUIPaqueteDiapositivas> lista){
		LinkedList<File> listaFicheros = new LinkedList<File>();
		for (GUIPaqueteDiapositivas gui_listaDiapositivas : lista){
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
