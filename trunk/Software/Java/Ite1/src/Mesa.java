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
	private LinkedList<Diapositiva> diapositivasEnLaMesa;
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
		diapositivasEnLaMesa = new LinkedList<Diapositiva>();
	}
	
	/**
	 * Anade la diapositiva al panel
	 * 
	 * Posibles mejoras, recentrar el espacio para que quede bonito
	 * Detectar si la fotografia esta en horizontal o vertical
	 * Utilizar un worker! Mirar web apuntada. SwingWorker
	 *
	 * @param foto
	 */
	public void addDiapositiva(File foto){ 
		
		//Creamos la diapositiva y anadir a lista
		Diapositiva diapo = new Diapositiva(foto);
		diapositivasEnLaMesa.add(diapo);
		//Colocar nombre y Numero
		diapo.setNumeroFoto(String.valueOf(diapositivasEnLaMesa.size()));
		diapo.setNombreFoto(foto.getName());
		//Anadir
		panel.add(diapo);
		//Actualizar visualizacion.
		panel.updateUI();
	}
	
	/** Anade una serie de diapositivas paradas como array de Files*/
	public boolean addDiapositiva(File[] files){
		if (files != null){
			for(File f: files){
				addDiapositiva(f);
			}
		}else{
			return false;
		}
		return true;
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
