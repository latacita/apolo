import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JPanel;

import transparentWindows.AWTUtilitiesWrapper;

public class VentanaDeEspera extends JDialog{

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public VentanaDeEspera(){
		this(null);
	}
	public VentanaDeEspera(Component padre) {
        // le asignamos la opacidad de 0.9f 
        AWTUtilitiesWrapper.setWindowOpacity(this, 0.9f);
		getContentPane().setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		setUndecorated(true); 
		setResizable(false);
		setModal(true);
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
			JProgressBar progressBar = new JProgressBar();
			progressBar.setBounds(12, 65, 495, 20);
			panel.add(progressBar);
			progressBar.setIndeterminate(true);
		}
	}
}
