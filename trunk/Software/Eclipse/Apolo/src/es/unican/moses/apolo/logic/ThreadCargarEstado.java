package es.unican.moses.apolo.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

//Paquetes propios
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIBalda;
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIMesa;
import es.unican.moses.apolo.ui.windows.DialogoCargarEstado;



/**
 * Thread que carga el estado en un fichero
 * Incrementa un progress bar
 * @author Angel
 * Void -> Por que no devuelve la tarea ningun resultado
 * Integer -> Es lo que publica de vez en cuando el hilo en background
 */
public class ThreadCargarEstado extends SwingWorker<Void, Integer> {
	private GUIMesa gui_mesa;
	private GUIEstanteria gui_estanteria;
	private GUIAlbum gui_album;
	private DialogoCargarEstado dialogo;
	private String ruta;
	private PaqueteDatos paquete;
	private GhostComponentAdapter componentAdapter = null;
	private GhostMotionAdapter motionAdapter = null;
	private int contFallos = 0;
	 
	/**
	 * Constructor
	 * @wbp.parser.entryPoint
	 */
	public ThreadCargarEstado(String ruta, GUIMesa gui_mesa, GUIEstanteria gui_estanteria, GUIAlbum gui_album, DialogoCargarEstado dialogo){
		this.ruta = ruta;
		this.gui_mesa = gui_mesa;
		this.gui_estanteria = gui_estanteria;
		this.gui_album = gui_album;
		this.dialogo = dialogo;
	}
	
	/**
	 * Metodo que asigna los controladores del modo translúcido
	 * @param componentAdapter
	 * @param motionAdapter
	 */
	public void setControladores(GhostComponentAdapter componentAdapter, GhostMotionAdapter motionAdapter){
		this.componentAdapter = componentAdapter;
		this.motionAdapter = motionAdapter;
	}
	
	/**
	 * Ejecuta este metodo en otro hilo.
	 */
	@Override
	protected Void doInBackground() {
		dialogo.getProgreso().setIndeterminate(true);
		dialogo.bloquearCancelar();
		
		//Reiniciamos los componenetes de la aplicacion
		gui_album.getVisor().removeAll();
		gui_estanteria.reinicia();
		gui_mesa.getPanelDiapositivas().removeAll();
		
		//Lectura fichero.
		FileInputStream fich = null;
		ObjectInputStream entrada = null;
		try {
			fich = new FileInputStream(ruta);
			entrada = new ObjectInputStream(fich);
			cargaPaqueteDatos(entrada, paquete); 
			
		} catch (FileNotFoundException e1) {
			System.out.println("Error: fichero no encontrado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: Objeto leido incorrecto");
		}catch (IOException e1) {
			System.out.println("Error al escribir datos");
		} finally{
			try {
				entrada.close();
				fich.close();
			} catch (IOException e1) {
				System.out.println("Error cerrando fichero");
			}	
		}
		return null;
	}
	
	/**
	 * Metodo privado que lee el paquete de datos y carga o no los datos almacenados en el
	 * @param entrada
	 * @param paquete
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void cargaPaqueteDatos(ObjectInputStream entrada, PaqueteDatos paquete) throws IOException, ClassNotFoundException{
		paquete = (PaqueteDatos)entrada.readObject();
		if (paquete.isMesa()){ cargaMesa(entrada);}
		if (paquete.isEstanteria()){ cargaEstanteria(entrada);}
		if (paquete.isAlbum()){ cargaAlbum(entrada);}
	}
	
	/**
	 * Metodo privado, carga los datos de la mesa
	 * @param entrada
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void cargaMesa(ObjectInputStream entrada) throws IOException, ClassNotFoundException{
		LinkedList<Diapositiva> listaDiapositivasMesa = (LinkedList<Diapositiva>)entrada.readObject();
		for (Diapositiva diapo : listaDiapositivasMesa){
			if (diapo.getFichero().exists()){
				GUIDiapositiva gd = new GUIDiapositiva(gui_mesa, diapo);
				gui_mesa.addGUIDiapositivia(gd);
				publish(1);
			}else{
				contFallos++;
			}
		}
	}
	
	/**
	 * Metodo privado, carga los datos de la estanteria
	 * @param entrada
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void cargaEstanteria(ObjectInputStream entrada) throws IOException, ClassNotFoundException{
		LinkedList<Balda> listaEstanteria = (LinkedList<Balda>) entrada.readObject();
		for (Balda balda : listaEstanteria){
			GUIBalda b = gui_estanteria.addGUIBalda();
			for (Diapositiva diapo : balda.getListaDiapositivas()){
				if (diapo.getFichero().exists()){
					GUIDiapositiva gd = new GUIDiapositiva(b, diapo);
					gd.addMouseListener(componentAdapter);
					gd.addMouseMotionListener(motionAdapter);
					b.addGUIDiapositiva(gd);
					publish(1);
				}else{
					contFallos++;
				}
			}
			b.setDescripcion(balda.getDescripcion());
		}
	}
	
	/**
	 * Metodo privado, carga los datos del album
	 * @param entrada
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void cargaAlbum(ObjectInputStream entrada) throws IOException, ClassNotFoundException{
		LinkedList<Album> listaAlbum = (LinkedList<Album>) entrada.readObject();
		for(Album album : listaAlbum){
			LinkedList<GUIDiapositiva> lista_gui_diapo = new LinkedList<GUIDiapositiva>();
			for (Diapositiva diapo : album.getListaDiapositivas()){
				if (diapo.getFichero().exists()){
					GUIDiapositiva diapositiva = new GUIDiapositiva(gui_mesa, diapo);
					diapositiva.addMouseListener(componentAdapter);
					diapositiva.addMouseMotionListener(motionAdapter);
					lista_gui_diapo.add(diapositiva);
					publish(1);
				}else{
					contFallos++;
				}
			}
			gui_album.addListaFotos(lista_gui_diapo, album.getDescripion());
		}

	}
	
	
	/**
	 * Ejecuta esto al finalizar el hilo de trabajo
	 */
	@Override
	protected void done() {
		
		if (contFallos != 0){
			JOptionPane.showMessageDialog(null, "No se encontraron: " + contFallos + " fotografias." , "Fotografias perdidas", JOptionPane.WARNING_MESSAGE);
		}
		gui_mesa.validate();
		gui_mesa.repaint();
		gui_estanteria.renumerarBaldas();
		gui_estanteria.validate();
		gui_estanteria.repaint();
		gui_album.validate();
		gui_album.repaint();
		
		dialogo.dispose();
	}
	

	/**
	 * Modifica este valor el hilo padre.
	 */
	@Override
    protected void process(List<Integer> valor) {
		dialogo.setNumFicheros(dialogo.getNumFicheros() + valor.get(0));
    }

}
