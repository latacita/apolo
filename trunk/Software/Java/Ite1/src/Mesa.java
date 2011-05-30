import java.io.File;
import java.util.LinkedList;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Panel que representa la Mesa de la aplicacion
 * Donde se depositaran las diapositivas para su seleccion y ordenacion
 * @author Angel
 *
 */
public class Mesa extends JPanel implements ComponentListener{
	
	/** Autogenerado*/
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	private LinkedList<Diapositiva> listaDiapositivas;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	/**
	 * Mesa es un panel, que dentro contiene un scrollpane y dentro 
	 * de ese un panel donde van apareciendo las diapositivas.
	 */
	public Mesa() {
		//Tamaño inicial
		setSize(800, 600);
		setLayout(new BorderLayout(0, 0));
		
		//Scroll
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.addComponentListener(this);
		
		//Panel interno
		panel = new JPanel();
		panel.setSize(800, 600);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, panel.getWidth() / Diapositiva.tamDiapo, 5, 5));
		
		//Inicializacion de lista.
		listaDiapositivas = new LinkedList<Diapositiva>();
	}
	
	/**
	 * Anade la diapositiva al panel
	 * 
	 * Detectar si la fotografia esta en horizontal o vertical
	 *
	 * @param foto
	 */
	public void addDiapositiva(File foto){ 
		//Creamos la diapositiva y anadir a lista
		Diapositiva diapo = new Diapositiva(this, foto);
		listaDiapositivas.add(diapo);
		//Colocar nombre y Numero
		diapo.setNumeroFoto(String.valueOf(listaDiapositivas.size()));
		diapo.setNombreFoto(foto.getName());
		//Anadir
		panel.add(diapo);
	}
	
	/**
	 * Añade una serie de imagenes a la mesa
	 * Utiliza un swingworker (Hilo) para no paralizar la aplicacion
	 * @param files Array de ficheros imagen a añadir
	 */
	public void addDiapositiva(File[] files){
		VentanaDeEspera VentanaEspera = new VentanaDeEspera(this);
		VentanaEspera.setVisible(true);
		ThreadAddDiapositiva hilo = new ThreadAddDiapositiva(this, files, VentanaEspera);
		hilo.execute();
		repaint();
	}
	
	/** Elimina una diapositiva*/
	public void removeDiapositiva(Diapositiva diapo){
		panel.remove(diapo);
		listaDiapositivas.remove(diapo);
		renumerar();
		panel.repaint();
		panel.doLayout();
	}
	
	/**
	 * Renumera las diapositivas de la mesa
	 */
	private void renumerar(){
		for (Diapositiva diapo : listaDiapositivas){
			diapo.setNumeroFoto(this.getNumDiapositiva(diapo)+1); //Mas 1 para q no comience en 0
		}
	}
	
	/**
	 * Retorna el numero de la diapositiva en la lista
	 * @param diapo Diapositiva a localizar en la lista
	 * @return Posicion que ocupa la diapositiva en la lista
	 */
	public int getNumDiapositiva(Diapositiva diapo){
		return listaDiapositivas.indexOf(diapo);
	}
	
	/**
	 * Devuelve la lista de diapositivas que hay en la mesa
	 * @return
	 */
	public LinkedList<Diapositiva> getListaDiapositivas(){
		return listaDiapositivas;
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel getPanelDiapositivas(){
		return panel;
	}
	
	/* METODOS QUE OBLIGA A IMPLEMENTAR ComponentListener*/

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/** Al redimensionar, calcula el nuevo numero de columnas */ //Hay que depurar un poco esto.
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getComponent().getWidth() > Diapositiva.tamDiapo)
            ((java.awt.GridLayout)panel.getLayout()).setColumns(
                    e.getComponent().getWidth() / (Diapositiva.tamDiapo + 10));
        panel.doLayout();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
