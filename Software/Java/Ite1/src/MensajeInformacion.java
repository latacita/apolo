import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * Mensaje que muestra informacion.
 * Es modal, y solo permite aceptar.
 * Se le debe pasar el texto que debe mostrar
 * @author Angel
 */
public class MensajeInformacion extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MensajeInformacion(String msj) {
		this(null, "Apolo Informa", msj);
	}
	public MensajeInformacion(Component padre, String msj) {
		this(padre, "Apolo Informa", msj);
	}
	public MensajeInformacion(Component padre, String msjTitulo, String msj) {
		setResizable(false);
		setModal(true);
		setTitle(msjTitulo);
		setLocationRelativeTo(padre);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		setBounds(screenSize.width/2-644/2, screenSize.height/2-500/2, 644, 201); //Centrar el mensaje Un poco mas arriba del centro
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("Info.png"));
		label.setBounds(26, 23, 107, 107);
		contentPanel.add(label);
		{
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			btnAceptar.setBounds(517, 130, 97, 25);
			contentPanel.add(btnAceptar);
		}
		
		JLabel label_1 = new JLabel(msj);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(174, 23, 440, 94);
		contentPanel.add(label_1);
		
		//Mostrar
		setVisible(true);
	}
}
