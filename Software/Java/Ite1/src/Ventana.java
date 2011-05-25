import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;


public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
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
					new MensajeInformacion("<html>Bienvenido a Apolo, estamos siendo implementado. <br><center> Modo Beta</center></html>");
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
		setTitle("Apolo");
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
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		PanelPrincipal.setLayout(null);
		setContentPane(PanelPrincipal);
		
		//Panel Mesa
		PanelMesa = new JPanel();
		PanelMesa.setLayout(null);
		PanelMesa.setPreferredSize(new Dimension(screenSize.width-30, screenSize.height/2-62)); //-30 por scroll

		JScrollPane Scroll = new JScrollPane(PanelMesa);
		Scroll.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		//Scroll solo vertical
		Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Posicionamiento del PanelScroll
		//0 separacion de la derecha, mitad de altura que la ventana, achura del scroll, -55 tamano cabeza ventana
		Scroll.setBounds(0, screenSize.height/2, screenSize.width-5, screenSize.height/2-55);
		//Anadimos el Scroll a la ventana
		PanelPrincipal.add(Scroll);
		
		
		
		//Controlador Importar
		mntmImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Seleccion de directorios o fotos
				Mesa af = new Mesa();
				af.importar(Ventana.this);
				
				/*#*********************************************************
				?¿?¿?¿?¿Por que no puedo hacer esto desde un metodo?¿?¿???¿?
				Posibles mejoras, recentrar el espacio para que quede bonito (HECHO)
				Detectar si la fotografia esta en horizontal o vertical
				*********************************************************#*/
				//Calcular la separacion optima entra fotografias
				System.out.println("Anchura Restante: " + (PanelMesa.getWidth() % dimensionDiapo));
				System.out.println("Numero de diapo: " + (PanelMesa.getWidth() / dimensionDiapo));
				separacionEntreDiapo = (PanelMesa.getWidth() % dimensionDiapo) / (PanelMesa.getWidth() / dimensionDiapo);
				//Colocar fotografias 
				int posicionX=separacionEntreDiapo;
				int posicionY=separacionEntreDiapo;
				int numero = 0;
				for (File f: af.getListaFotos()){
					//Cargamos Marco
					Diapositiva diapo = new Diapositiva(f);
					//Colocar nombre y Numero
					diapo.setNumeroFoto(new Integer(numero).toString());
					diapo.setNombreFoto(f.getName());
					//Posicion y tamano
					diapo.setBounds(posicionX, posicionY, dimensionDiapo, dimensionDiapo);
					PanelMesa.add(diapo);
					posicionX+=separacionEntreDiapo + dimensionDiapo;
					if (posicionX + dimensionDiapo > PanelMesa.getWidth()){
						posicionX=separacionEntreDiapo;
						posicionY+=separacionEntreDiapo + dimensionDiapo;
						/*if(posicionY > PanelMesa.getHeight()){
							PanelMesa.setPreferredSize(new Dimension(PanelMesa.getWidth(), PanelMesa.getHeight()+dimensionDiapo));
							System.out.println(PanelMesa.getSize());
						}*/
					}
				}
				//Importante, hacer el repaint...
				PanelMesa.repaint();
			}
		});
		
	}
}
