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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

//Paquetes Propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.dragAndDrop.GhostComponentAdapter;
import es.unican.moses.apolo.ui.dragAndDrop.GhostGlassPane;
import es.unican.moses.apolo.ui.dragAndDrop.GhostMotionAdapter;
import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIEntradaSalida;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIListaDiapositivas;
import es.unican.moses.apolo.ui.widgets.GUIMesa;


/**
 * Apolo programa principal
 * Programa para clasificar y ordenar fotografias basado en el funcionamiento
 * de un clasificador de diapositivas clasico.
 * @author Angel
 *
 */
public class Apolo {

	/** Atributos */
	private JFrame frmApolo;
	private GUIMesa gui_mesa;
	GUIEstanteria gui_estanteria;
	private GUIAlbum gui_album;
	//Panel de cristal
	private GhostGlassPane glassPane;
	//
	GhostComponentAdapter componentAdapter;
    GhostMotionAdapter motionAdapter;
	

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
		if (!System.getProperty("os.name").contains("Windows")){
			Constantes.rutaImagenes = Constantes.get() + "/" + Constantes.rutaImagenes;
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Cambio de apariencia por defecto a la apariencia Nimbus, si no esta disponible, no la cambia
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
		
		//Controlador de cierre de ventana
		frmApolo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmApolo.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {cerrar();}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		
		frmApolo.setIconImage(Toolkit.getDefaultToolkit().getImage(Constantes.RUTA_ICONO_APOLO));
		frmApolo.setTitle("Apolo");
		frmApolo.setSize(1024, 768);
		//Centrar
		frmApolo.setLocationRelativeTo(null);
		frmApolo.setMinimumSize(new Dimension(1024 , 768)); //Tamaño minimo de ventana
		
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
        componentAdapter = new GhostComponentAdapter(glassPane, "Accion");
        
        //********************************
        //**** PENDIENTE DE ELIMINAR *****
        //********************************
        //Indico que el que escucha donde se suelta es el deposito
        //GhostDropListener listener = new GhostDropManagerDemo(lblEstanterias);
        //Añado el listener, quiere estar al tanto del evento de soltar despues de arrastras
        //componentAdapter.addGhostDropListener(listener);
        
        //Creo un oyente de la accion de arrastr
        motionAdapter = new GhostMotionAdapter(glassPane);
		
		gui_album = new GUIAlbum(this,componentAdapter,motionAdapter);
		GridBagConstraints gbc_album = new GridBagConstraints();
		gbc_album.insets = new Insets(0, 0, 5, 0);
		gbc_album.fill = GridBagConstraints.BOTH;
		gbc_album.gridx = 0;
		gbc_album.gridy = 0;
		frmApolo.getContentPane().add(gui_album, gbc_album);
		
		gui_estanteria = new GUIEstanteria(componentAdapter, gui_album);
		GridBagConstraints gbc_estanterias = new GridBagConstraints();
		gbc_estanterias.insets = new Insets(0, 0, 5, 0);
		gbc_estanterias.fill = GridBagConstraints.BOTH;
		gbc_estanterias.gridx = 0;
		gbc_estanterias.gridy = 1;
		frmApolo.getContentPane().add(gui_estanteria, gbc_estanterias);
		
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
		menuBar.setMargin(new Insets(0, 2, 0, 2));
		frmApolo.setJMenuBar(menuBar);
		
		JMenu mnAbrir = new JMenu("Archivo");
		mnAbrir.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnAbrir);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmCerrar.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_CERRAR));
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
		
		JMenuItem mntmAbrir = new JMenuItem("Cargar");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargar();
			}
		});
		mntmAbrir.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmAbrir.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_ABRIR));
		mnAbrir.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		mntmGuardar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmGuardar.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_GUARDAR));
		mnAbrir.add(mntmGuardar);
		
		JSeparator separator_1 = new JSeparator();
		mnAbrir.add(separator_1);
		mnAbrir.add(mntmCerrar);
		
		JMenu mnImportarexportar = new JMenu("Importar/Exportar");
		mnImportarexportar.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnImportarexportar);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mntmImportar.setFont(new Font("Dialog", Font.PLAIN, 14));
		mntmImportar.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_IMPORTAR));
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importar();
			}
		});
		mnImportarexportar.add(mntmImportar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mntmExportar.setFont(new Font("Dialog", Font.PLAIN, 14));
		mntmExportar.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_EXPORTAR));
		mntmExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportar();
			}
		});
		mnImportarexportar.add(mntmExportar);
		
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		mnAcercaDe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File(Constantes.RUTA_MANUAL_APOLO));
				} catch (IOException e){
					JOptionPane.showMessageDialog(null, "Error, no se encuentra el manual o el programa compatible con su visualizacion", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmManual.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmManual.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_MANUAL));
		mnAcercaDe.add(mntmManual);
		
		JMenuItem mntmWeb = new JMenuItem("Web Apolo");
		mntmWeb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI(Constantes.RUTA_WEB_APOLO));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error, no se encuentra el programa navegador de internet", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (URISyntaxException e) {
					JOptionPane.showMessageDialog(null, "Error, url incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmWeb.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmWeb.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_WEB));
		mnAcercaDe.add(mntmWeb);
		
		JSeparator separator = new JSeparator();
		mnAcercaDe.add(separator);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de Apolo");
		mntmAcercaDe.setFont(new Font("SansSerif", Font.BOLD, 14));
		mntmAcercaDe.setIcon(new ImageIcon(Constantes.RUTA_ICONO_MENU_ACERCADE));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DialogoAcercaDe(frmApolo);
			}
		});
		mnAcercaDe.add(mntmAcercaDe);
	}
	
	/**
	 * Importa fotografias a la aplicacion
	 */
	public void importar(){
		gui_mesa.addDiapositiva(GUIEntradaSalida.importar(frmApolo));
	}
	
	/**
	 * Exporta fotografias de la aplicacion
	 */
	public void exportar(){
		LinkedList<GUIListaDiapositivas> lista = gui_album.getListaGUIListaDiapositivas();
		if (lista == null || lista.size() == 0){
			JOptionPane.showMessageDialog(null, "Debe tener al menos una balda añadida al Album", "Sin fotos que exportar", JOptionPane.INFORMATION_MESSAGE);
		}else{
			GUIEntradaSalida.exportar(lista);
		}
	}
	
	/**
	 * Carga un estado
	 */
	public void cargar(){
		new DialogoCargarEstado(frmApolo, gui_album, gui_estanteria, gui_mesa, componentAdapter, motionAdapter);
	}
	
	/**
	 * Guarda un estado
	 */
	public void guardar(){
		new DialogoGuardarEstado(frmApolo, gui_album, gui_estanteria, gui_mesa);
	}
	
	
	/**
	 * Cerrar aplicacion preguntando si asi lo desea
	 */
	private void cerrar(){
		int respuesta;
		respuesta=JOptionPane.showConfirmDialog(frmApolo,"¿Desea salir de la aplicacion? ","Confirmacion de salida",JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	

	public GUIMesa getGui_mesa() {
		return gui_mesa;
	}

	public GUIEstanteria getGui_estanterias() {
		return gui_estanteria;
	}

	public GUIAlbum getGui_album() {
		return gui_album;
	}
}