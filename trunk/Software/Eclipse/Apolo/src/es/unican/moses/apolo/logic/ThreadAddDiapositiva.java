package es.unican.moses.apolo.logic;

import java.io.File;
import java.util.List;
import javax.swing.SwingWorker;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;
import es.unican.moses.apolo.ui.widgets.GUIMesa;
import es.unican.moses.apolo.ui.windows.VentanaDeEspera;



/**
 * Thread que a�ade fotos a la GUIMesa
 * Incrementa un progress bar
 * @author Angel
 * Void -> Por que no devuelve la tarea ningun resultado
 * Integer -> Es lo que publica de vez en cuando el hilo en background
 */
public class ThreadAddDiapositiva extends SwingWorker<Void, Integer>{

	private GUIMesa gui_mesa;
	private File[] files;
	private VentanaDeEspera ventana;
	 
	/**
	 * Constructor
	 * @wbp.parser.entryPoint
	 */
	public ThreadAddDiapositiva(GUIMesa gui_mesa, File[] files, VentanaDeEspera ventana){
		this.gui_mesa = gui_mesa;
		this.files = files;
		this.ventana = ventana;
	}
	
	/**
	 * Ejecuta este metodo en otro hilo.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		//Variables para calculo del paso del progressbar
		float paso = 100f/files.length; 
		float total=0;
		
		for (int i = 0; i< files.length; i++){
			
			//Creamos la diapositiva
			GUIDiapositiva gui_diapositiva = new GUIDiapositiva(gui_mesa, files[i], gui_mesa.getNumeroDeapositivas()+1);
			//A�adimos la diapositiva a la mesa
			gui_mesa.addGUIDiapositivia(gui_diapositiva);
			
			//Calculo del paso del JProgressBar
			total += paso;
			if ((int) total > ventana.getJProgressBar().getValue()){
				publish((int)total);
			}
		}
		return null;
	}
	
	/**
	 * Ejecuta esto al finalizar el hilo de trabajo
	 */
	@Override
	protected void done() {
		ventana.setVisible(false);
	}
	
	/**
	 * Modifica este valor el hilo padre.
	 */
	@Override
    protected void process(List<Integer> valor) {
		ventana.getJProgressBar().setValue(valor.get(0));
    }

}
