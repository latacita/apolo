import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;

/**
 * Thread que a�ade fotos a la mesa
 * Incrementa un progress bar
 * @author Angel
 * Void -> Por que no devuelve la tarea ningun resultado
 * Integer -> Es lo que publica de vez en cuando el hilo en background
 */
public class ThreadAddDiapositiva extends SwingWorker<Void, Integer>{

	Mesa mesa;
	File[] files;
	VentanaDeEspera ventana;
	LinkedList<Diapositiva> lista;
	 
	/**
	 * Constructor
	 * @wbp.parser.entryPoint
	 */
	public ThreadAddDiapositiva(Mesa m, File[] f, VentanaDeEspera v){
		mesa = m;
		files = f;
		ventana = v;
		lista = mesa.getListaDiapositivas();
	}
	
	/**
	 * Ejecuta este metodo en otro hilo.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		//Calculo del paso del progressbar
		float paso = 100f/files.length;
		float total=0;
		for (int i = 0; i< files.length; i++){
			//Creamos la diapositiva y anadir a lista
			Diapositiva diapo = new Diapositiva(mesa, files[i]);
			lista.add(diapo);
			//Colocar nombre y Numero
			diapo.setNumeroFoto(String.valueOf(lista.size()));
			diapo.setNombreFoto(files[i].getName());
			//Anadir
			mesa.getPanelDiapositivas().add(diapo);
			//Actualizar visualizacion.
			//mesa.getPanelDiapositivas().updateUI();
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
    protected void process(List<Integer> chunks) {
		ventana.getJProgressBar().setValue(chunks.get(0));
    }

}