import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Apolo {

	private JFrame frmApolo;
	private Mesa mesa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apolo window = new Apolo();
					window.frmApolo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Apolo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApolo = new JFrame();
		frmApolo.setTitle("Apolo");
		frmApolo.setSize(800, 600);
		//Maximizar
		//frmApolo.setExtendedState(frmApolo.MAXIMIZED_BOTH);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(Diapositiva.tamDiapo +50 , Diapositiva.tamDiapo+55));
		frmApolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApolo.getContentPane().setLayout(new BorderLayout(0, 0));
		
		mesa = new Mesa();
		frmApolo.getContentPane().add(mesa, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmApolo.setJMenuBar(menuBar);
		
		JMenu mnAbrir = new JMenu("Archivo");
		menuBar.add(mnAbrir);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesa.addDiapositiva(EntradaSalida.importar(frmApolo));
			}
		});
		mnAbrir.add(mntmImportar);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mnAbrir.add(mntmCerrar);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de Apolo");
		mnAcercaDe.add(mntmAcercaDe);
	}
}