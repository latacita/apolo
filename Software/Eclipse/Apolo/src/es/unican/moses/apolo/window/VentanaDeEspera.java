package es.unican.moses.apolo.window;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Clase que dibuja una ventana que se utiliza cuando se
 * realizan procesos que requieran un cierto tiempo.
 * Indica al usuario el progreso de la tarea.
 * @author Angel
 *
 */
public class VentanaDeEspera extends JDialog{

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	JProgressBar progressBar;
	
	/**
	 * Create the dialog.
	 */
	public VentanaDeEspera(){
		this(null);
	}
	public VentanaDeEspera(Component padre) {
		
		getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		setBackground(Color.GRAY);
		setUndecorated(true); 
		setResizable(false);
		setAlwaysOnTop(true);
		setSize(529, 131);
		setLocationRelativeTo(padre);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 519, 121);
		getContentPane().add(panel);
		panel.setLayout(null);
		{
			JLabel lblPorFavorEspere = new JLabel("<HTML><b>Por favor, espere mientras se realizan las operaciones solicitadas.</b></HTML>");
			lblPorFavorEspere.setBounds(12, 36, 499, 16);
			panel.add(lblPorFavorEspere);
			lblPorFavorEspere.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			progressBar = new JProgressBar();
			progressBar.setBounds(12, 65, 495, 20);
			panel.add(progressBar);
			//progressBar.setIndeterminate(true);
		}
	}
	
	public JProgressBar getJProgressBar(){
		return progressBar;
	}
}
