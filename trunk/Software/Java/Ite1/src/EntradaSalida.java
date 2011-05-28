import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Clase que ofrece un sistema para la seleccion de ficheros 
 * de entrada o salida.
 * @author Angel
 *
 */
public class EntradaSalida {
	
	/**
	 * Muestra un dialogo que ofrece la opcion de seleccionar una o varias fotos
	 * 
	 * Posibles mejoras, mostrar miniatura de cada foto
	 * @param componente
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

}
