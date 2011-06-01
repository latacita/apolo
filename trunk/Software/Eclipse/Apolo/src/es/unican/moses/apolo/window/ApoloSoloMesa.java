package es.unican.moses.apolo.window;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import es.unican.moses.apolo.gui.GUIEntradaSalida;
import es.unican.moses.apolo.gui.GUIMesa;
import es.unican.moses.apolo.logic.Constantes;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ApoloSoloMesa {

	private JFrame frmApolo;
	private GUIMesa mesa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApoloSoloMesa window = new ApoloSoloMesa();
					//new MensajeInformacion("<html>Bienvenido a Apolo, estamos siendo implementado. <br><center> Modo Beta</center></html>");
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
	public ApoloSoloMesa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApolo = new JFrame();
		frmApolo.setTitle("Apolo");
		frmApolo.setSize(825, 600);
		//Maximizar
		//frmApolo.setExtendedState(frmApolo.MAXIMIZED_BOTH);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(Constantes.TAM_DIAPOSITIVA + 40 , Constantes.TAM_DIAPOSITIVA +70));
		frmApolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApolo.getContentPane().setLayout(new BorderLayout(5, 5));
		
		mesa = new GUIMesa();
		BorderLayout borderLayout = (BorderLayout) mesa.getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		frmApolo.getContentPane().add(mesa, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmApolo.setJMenuBar(menuBar);
		
		JMenu mnAbrir = new JMenu("Archivo");
		menuBar.add(mnAbrir);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesa.addDiapositiva(GUIEntradaSalida.importar(frmApolo));
			}
		});
		mnAbrir.add(mntmImportar);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mnAbrir.add(mntmCerrar);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de Apolo");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new VentanaAcercaDe();
			}
		});
		mnAcercaDe.add(mntmAcercaDe);
	}
}