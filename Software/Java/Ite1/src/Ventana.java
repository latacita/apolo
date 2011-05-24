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
import java.io.IOException;
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
		mnArchivo.add(mntmSalir);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
		
		JScrollPane Scroll = new JScrollPane();
		Scroll.setBounds(0, 205, 1274, 770);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		PanelPrincipal.add(Scroll);
		
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
				for (File f: af.getListaFotos()){
					//Cargamos Marco
					Diapositiva diapo = new Diapositiva(new File ("HDiapositiva.png"));
					try {
						diapo.setFoto(f);
						} catch (IOException e) {e.printStackTrace();}					
					diapo.setBounds(posicionX, posicionY, dimensionDiapo, dimensionDiapo);
					PanelMesa.add(diapo);
					posicionX+=separacionEntreDiapo + dimensionDiapo;
					if (posicionX + dimensionDiapo > PanelMesa.getWidth()){
						posicionX=separacionEntreDiapo;
						posicionY+=separacionEntreDiapo + dimensionDiapo;
					}
				}
				//Importante, hacer el repaint...
				PanelMesa.repaint();
			}
		});
		
	}
}
