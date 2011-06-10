package es.unican.moses.apolo.ui.windows;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

//Paquetes Propios
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostGlassPane;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIEntradaSalida;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIMesa;


public class Apolo {

	private JFrame frmApolo;
	private GUIMesa gui_mesa;
	//Panel de cristal
	private GhostGlassPane glassPane;

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
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		
		
		frmApolo = new JFrame();
		frmApolo.setTitle("Apolo");
		frmApolo.setSize(1024, 768);
		//Maximizar
		//frmApolo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(1024 , 768)); //Tamaño minimo de ventana
		frmApolo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{807, 0};
		gridBagLayout.rowHeights = new int[]{180, 229, 270, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmApolo.getContentPane().setLayout(gridBagLayout);
		
		//Asignacion del panel de crisal
		glassPane = new GhostGlassPane();
		frmApolo.setGlassPane(glassPane);
		
		//Le creo para indicar que el fantasma sera el propio componente y pintara en el glasspane
        GhostComponentAdapter componentAdapter = new GhostComponentAdapter(glassPane, "Accion");
        
        //Indico que el que escucha donde se suelta es el deposito
        //GhostDropListener listener = new GhostDropManagerDemo(lblEstanterias);
        //Añado el listener, quiere estar al tanto del evento de soltar despues de arrastras
        //componentAdapter.addGhostDropListener(listener);
        
        //Creo un oyente de la accion de arrastr
        GhostMotionAdapter motionAdapter = new GhostMotionAdapter(glassPane);
		
		GUIAlbum album = new GUIAlbum(this);
		GridBagConstraints gbc_album = new GridBagConstraints();
		gbc_album.insets = new Insets(0, 0, 5, 0);
		gbc_album.fill = GridBagConstraints.BOTH;
		gbc_album.gridx = 0;
		gbc_album.gridy = 0;
		frmApolo.getContentPane().add(album, gbc_album);
		
		GUIEstanteria estanterias = new GUIEstanteria(componentAdapter, album);
		GridBagConstraints gbc_estanterias = new GridBagConstraints();
		gbc_estanterias.insets = new Insets(0, 0, 5, 0);
		gbc_estanterias.fill = GridBagConstraints.BOTH;
		gbc_estanterias.gridx = 0;
		gbc_estanterias.gridy = 1;
		frmApolo.getContentPane().add(estanterias, gbc_estanterias);
		
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
				importar();
			}
		});
		mnAbrir.add(mntmImportar);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int respuesta;
				respuesta=JOptionPane.showConfirmDialog(frmApolo,"¿Desea salir de la aplicacion? ","Confirmacion de salida",JOptionPane.YES_NO_OPTION);
				if(respuesta==JOptionPane.YES_OPTION){
					System.exit(0);
				}
				
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
	
	public void importar(){
		gui_mesa.addDiapositiva(GUIEntradaSalida.importar(frmApolo));
	}
	
	public void exportar(){
		
	}
}