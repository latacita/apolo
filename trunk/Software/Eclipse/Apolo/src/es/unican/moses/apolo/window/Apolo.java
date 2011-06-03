package es.unican.moses.apolo.window;

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
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

//Paquetes Propios
import es.unican.moses.apolo.ghost.GhostComponentAdapter;
import es.unican.moses.apolo.ghost.GhostDropListener;
import es.unican.moses.apolo.ghost.GhostDropManagerDemo;
import es.unican.moses.apolo.ghost.GhostGlassPane;
import es.unican.moses.apolo.ghost.GhostMotionAdapter;
import es.unican.moses.apolo.gui.GUIEntradaSalida;
import es.unican.moses.apolo.gui.GUIMesa;


public class Apolo {

	private JFrame frmApolo;
	private GUIMesa gui_mesa;
	//Panel de cristal
	private GhostGlassPane glassPane;
	
	JLabel lblAlbum;
	JLabel lblEstanterias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apolo window = new Apolo();
					//new VentanaMensajeInformacion("<html>Bienvenido a Apolo, estamos siendo implementado. <br><center> Modo Beta</center></html>");
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
		//frmApolo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(820 , 600)); //Tamaño minimo de ventana
		frmApolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{807, 0};
		gridBagLayout.rowHeights = new int[]{127, 300, 200, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 2.0, Double.MIN_VALUE};
		frmApolo.getContentPane().setLayout(gridBagLayout);
		
		JPanel album = new JPanel();
		album.setBackground(Color.YELLOW);
		GridBagConstraints gbc_album = new GridBagConstraints();
		gbc_album.insets = new Insets(0, 0, 5, 0);
		gbc_album.fill = GridBagConstraints.BOTH;
		gbc_album.gridx = 0;
		gbc_album.gridy = 0;
		frmApolo.getContentPane().add(album, gbc_album);
		
		lblAlbum = new JLabel("Album");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 59));
		album.add(lblAlbum);
		
		JPanel estanterias = new JPanel();
		estanterias.setBackground(Color.ORANGE);
		GridBagConstraints gbc_estanterias = new GridBagConstraints();
		gbc_estanterias.insets = new Insets(0, 0, 5, 0);
		gbc_estanterias.fill = GridBagConstraints.BOTH;
		gbc_estanterias.gridx = 0;
		gbc_estanterias.gridy = 1;
		frmApolo.getContentPane().add(estanterias, gbc_estanterias);
		
		lblEstanterias = new JLabel("Estanterias");
		lblEstanterias.setFont(new Font("Tahoma", Font.PLAIN, 53));
		estanterias.add(lblEstanterias);
		
		//Asignacion del panel de crisal
		glassPane = new GhostGlassPane();
		frmApolo.setGlassPane(glassPane);
		
		//Le creo para indicar que el fantasma sera el propio componente y pintara en el glasspane
        GhostComponentAdapter componentAdapter = new GhostComponentAdapter(glassPane, "Accion");
        
		//Indico que el que escucha donde se suelta es el deposito
        GhostDropListener listener = new GhostDropManagerDemo(lblEstanterias);
        
        //Añado el listener, quiere estar al tanto del evento de soltar despues de arrastras
        componentAdapter.addGhostDropListener(listener);
        
        //Creo un oyente de la accion de arrastr
        GhostMotionAdapter motionAdapter = new GhostMotionAdapter(glassPane);
		
		gui_mesa = new GUIMesa(componentAdapter, motionAdapter);
		BorderLayout borderLayout = (BorderLayout) gui_mesa.getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		GridBagConstraints gbc_mesa = new GridBagConstraints();
		gbc_mesa.fill = GridBagConstraints.BOTH;
		gbc_mesa.gridx = 0;
		gbc_mesa.gridy = 2;
		frmApolo.getContentPane().add(gui_mesa, gbc_mesa);
		
		JMenuBar menuBar = new JMenuBar();
		frmApolo.setJMenuBar(menuBar);
		
		JMenu mnAbrir = new JMenu("Archivo");
		menuBar.add(mnAbrir);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui_mesa.addDiapositiva(GUIEntradaSalida.importar(frmApolo));
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