import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


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
					new MensajeInformacion("<html>Bienvenido a Apolo, estamos siendo implementado. <br><center> Modo Beta</center></html>");
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
		frmApolo.setSize(820, 600);
		//Maximizar
		frmApolo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(820 , 600)); //Tama�o minimo de ventana
		frmApolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{807, 0};
		gridBagLayout.rowHeights = new int[]{285, 200, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frmApolo.getContentPane().setLayout(gridBagLayout);
		
		mesa = new Mesa();
		BorderLayout borderLayout = (BorderLayout) mesa.getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		GridBagConstraints gbc_mesa = new GridBagConstraints();
		gbc_mesa.fill = GridBagConstraints.BOTH;
		gbc_mesa.gridx = 0;
		gbc_mesa.gridy = 1;
		frmApolo.getContentPane().add(mesa, gbc_mesa);
		
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
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnAbrir.add(mntmCerrar);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de Apolo");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaAcercaDe();
			}
		});
		mnAcercaDe.add(mntmAcercaDe);
	}
}