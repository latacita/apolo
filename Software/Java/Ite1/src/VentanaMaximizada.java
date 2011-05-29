import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaMaximizada {

	private JFrame frmVentana;
	private Mesa mesa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMaximizada window = new VentanaMaximizada();
					new MensajeInformacion("<html>Bienvenido a Apolo, estamos siendo implementado. <br><center> Modo Beta</center></html>");
					window.frmVentana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMaximizada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentana = new JFrame();
		frmVentana.setResizable(false);
		frmVentana.setTitle("Apolo");
		frmVentana.setSize(800, 600);
		frmVentana.setUndecorated(true);
		//Maximizar
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frmVentana.setSize(screen);

		frmVentana.setMinimumSize(new Dimension(Diapositiva.tamDiapo +50 , Diapositiva.tamDiapo+55));
		frmVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentana.getContentPane().setLayout(null);
		
		mesa = new Mesa();
		mesa.setBounds(12, (int) screen.getHeight() / 2, (int) screen.getWidth()-24, (int) (screen.getHeight()/2) - 35);
		frmVentana.getContentPane().add(mesa, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmVentana.setJMenuBar(menuBar);
		
		JMenu mnAbrir = new JMenu("Archivo");
		menuBar.add(mnAbrir);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesa.addDiapositiva(EntradaSalida.importar(frmVentana));
			}
		});
		mnAbrir.add(mntmImportar);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnAbrir.add(mntmCerrar);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de Apolo");
		mnAcercaDe.add(mntmAcercaDe);
	}
}