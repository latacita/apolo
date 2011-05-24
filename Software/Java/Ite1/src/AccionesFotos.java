import java.awt.Component;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class AccionesFotos {
	
	private LinkedList<File> listaFotos = new LinkedList<File>();
	
	/**
	 * Muestra un dialogo que ofrece la opcion de seleccionar una o varias fotos
	 * @param componente
	 */
	public void Importar(Component componente){
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
			for (File archivo : seleccionador.getSelectedFiles()){
				listaFotos.add(archivo);
			}
		}
	}
	
	/**
	 * Retorna la lista de archivos seleccionados
	 * @return
	 */
	public LinkedList<File> getListaFotos(){
		return listaFotos;
	}
	
	/**
	 * Añade a componente una diapositiva por cada foto que 
	 * haya en la lista.
	 * @param listaFotos
	 */
	public void anadirDiapositivas(Component mesa, List<File> listaFotos){
		Diapositiva diapo = new Diapositiva(listaFotos.get(0));
		
	}
}
