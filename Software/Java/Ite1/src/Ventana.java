import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;


public class Ventana extends JFrame {

	private JPanel PanelPrincipal;
	private JPanel PanelMesa;
	
	//Variables
	private int separacionEntreDiapo = 12;
	private int dimensionDiapo = 150; //pixeles diapositivas cuadradas

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		//Creacion de la ventana
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Maximizar y posicionar ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mnArchivo.add(mntmImportar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		PanelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(PanelPrincipal);
		
		JScrollPane Scroll = new JScrollPane();
		PanelPrincipal.add(Scroll, BorderLayout.CENTER);
		
		PanelMesa = new JPanel();
		PanelMesa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Scroll.setViewportView(PanelMesa);
		PanelMesa.setLayout(null);
		
		
		//Controlador Importar
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Seleccion de directorios o fotos
				AccionesFotos af = new AccionesFotos();
				af.Importar(Ventana.this);
				
				/*#*********************************************************
				?¿?¿?¿?¿Por que no puedo hacer esto desde un metodo?¿?¿???¿?
				Posibles mejoras, recentrar el espacio para que quede bonito
				*********************************************************#*/
				//Colocar fotografias 
				int posicionX=separacionEntreDiapo;
				int posicionY=separacionEntreDiapo;
				for (File f: af.getListaFotos()){
					//Cargamos Marco
					Diapositiva diapo = new Diapositiva(new File ("C:/Users/Angel/workspace/Proyecto/src/images/HDiapositiva.png"));
					try {
						diapo.setFoto(f);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					diapo.setBounds(posicionX, posicionY, dimensionDiapo, dimensionDiapo);
					PanelMesa.add(diapo);
					posicionX+=separacionEntreDiapo + dimensionDiapo;
					if (posicionX + dimensionDiapo > Toolkit.getDefaultToolkit().getScreenSize().width){
						posicionX=12;
						posicionY+=separacionEntreDiapo + dimensionDiapo;
					}
				}
				//Importante, hacer el repaint...
				PanelMesa.repaint();
			}
		});
		
	}
}
