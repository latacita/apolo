package es.unican.moses.apolo.logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIBalda;
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIListaDiapositivas;
import es.unican.moses.apolo.ui.widgets.GUIMesa;
import es.unican.moses.apolo.ui.windows.DialogoGuardarEstado;



/**
 * Thread que guarda el estado en un fichero
 * Incrementa un progress bar
 * @author Angel
 * Void -> Por que no devuelve la tarea ningun resultado
 * Integer -> Es lo que publica de vez en cuando el hilo en background
 */
public class ThreadGuardarEstado extends SwingWorker<Void, Integer> {
	private GUIMesa gui_mesa;
	private GUIEstanteria gui_estanteria;
	private GUIAlbum gui_album;
	private DialogoGuardarEstado dialogo;
	private String ruta;
	 
	/**
	 * Constructor
	 * @wbp.parser.entryPoint
	 */
	public ThreadGuardarEstado(String ruta, GUIMesa gui_mesa, GUIEstanteria gui_estanteria, GUIAlbum gui_album, DialogoGuardarEstado dialogo){
		this.ruta = ruta;
		this.gui_mesa = gui_mesa;
		this.gui_estanteria = gui_estanteria;
		this.gui_album = gui_album;
		this.dialogo = dialogo;
	}
	
	/**
	 * Ejecuta este metodo en otro hilo.
	 */
	@Override
	protected Void doInBackground() {
		
		// 4 PASOS
		int paso = 100/4;
		
		//Grabacion fichero.
		FileOutputStream fich = null;
		ObjectOutputStream salida = null;
		try {
			fich = new FileOutputStream(ruta);
			salida = new ObjectOutputStream(fich);
			guardaPaquetesDatos(salida,gui_mesa, gui_estanteria, gui_album);
			publish((int)paso);
			if (gui_mesa != null) guardarMesa(salida, gui_mesa);
			publish((int)paso);
			if (gui_estanteria != null) guardarEstanteria(salida, gui_estanteria);
			publish((int)paso);
			if (gui_album != null) guardarAlbum(salida, gui_album);
			publish((int)paso);
			
		} catch (FileNotFoundException e1) {
			System.out.println("Error en el fichero");
		} catch (IOException e1) {
			System.out.println("Error al escribir datos");
		} finally{
			try {
				salida.close();
				fich.close();
			} catch (IOException e1) {
				System.out.println("Error cerrando fichero");
			}	
		}
		return null;
	}
	
	private void guardaPaquetesDatos(ObjectOutputStream salida, GUIMesa gui_mesa, GUIEstanteria gui_estanteria, GUIAlbum gui_album) throws IOException{
		PaqueteDatos paquete = new PaqueteDatos();
		if (gui_mesa != null) paquete.setMesa(true);
		if (gui_estanteria != null) paquete.setEstanteria(true);
		if (gui_album != null) paquete.setAlbum(true);
		salida.writeObject(paquete);
	}
	
	private void guardarMesa(ObjectOutputStream salida, GUIMesa mesa) throws IOException{
		LinkedList<Diapositiva> listaDiapositivasMesa = new LinkedList<Diapositiva>();
		for (GUIDiapositiva gui_diapo : mesa.getlistaGUIDiapositivas()){
			listaDiapositivasMesa.add(gui_diapo.getDiapositiva());
		}
		salida.writeObject(listaDiapositivasMesa);
	}
	
	private void guardarEstanteria(ObjectOutputStream salida, GUIEstanteria estanteria) throws IOException{
		LinkedList<Balda> listaEstanteria = new LinkedList<Balda>();
		for (GUIBalda balda : estanteria.getlistaGUIBaldas()){
			listaEstanteria.add(balda.getBalda());
		}
		salida.writeObject(listaEstanteria);
	}
	
	private void guardarAlbum(ObjectOutputStream salida, GUIAlbum album) throws IOException{
		LinkedList<Album> listaAlbum = new LinkedList<Album>();
		for(GUIListaDiapositivas gui_listadiapo : album.getListaGUIListaDiapositivas()){
			listaAlbum.add(gui_listadiapo.getAlbum());
		}
		salida.writeObject(listaAlbum);
	}
	
	/**
	 * Ejecuta esto al finalizar el hilo de trabajo
	 */
	@Override
	protected void done() {
		dialogo.cancelar();
	}
	
	/**
	 * Modifica este valor el hilo padre.
	 */
	@Override
    protected void process(List<Integer> valor) {
		dialogo.getJProgressBar().setValue(dialogo.getJProgressBar().getValue()+valor.get(0));
    }

}
