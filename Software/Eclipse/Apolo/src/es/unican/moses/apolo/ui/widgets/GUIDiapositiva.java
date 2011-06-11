package es.unican.moses.apolo.ui.widgets;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Paquetes Propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.logic.Diapositiva;
import es.unican.moses.apolo.ui.windows.VentanaVisorImagenes;

/**
 * Clase GUIDiapositiva.
 * Representa visualmente la clase Diapositiva
 * Extiende a la clase JPanel, contiene 3 JLabel
 * Muestra:
 * -La diapositiva
 * -Nombre
 * -Numero
 * 
 * ##Posibles Mejoras ## 
 * -Detectar que la imagen esta en vertical u horizontal (Carga siempre en horizontal)
 * 
 * @author Angel Tezanos Ibañez
 *
 */
 public class GUIDiapositiva extends JPanel {

	 /** Atributos de la clase */
	 private final GUIDiapositiva yo;
	 private GUIMesa gui_mesa;
	 private Diapositiva diapositiva;
	 //private VisorDeImagenes visor;
	 private static final long serialVersionUID = -3488034684833395929L;

	 
	 /** Componentes internos de la clase */
	 Image marco=null;
	 JLabel label_foto;
	 JLabel label_numero;
	 JLabel label_nombre;
	 
	/**
	 * Constructor al que se le pasa la mesa y la diapositiva
	 * @param mesa
	 * @param diapo
	 * @wbp.parser.constructor
	 */
    public GUIDiapositiva(GUIMesa gui_mesa, Diapositiva diapo){
    	this.gui_mesa = gui_mesa;
        diapositiva = diapo;
        yo = this;
        inicializacionGUIDiapositiva();
    }
    public GUIDiapositiva(GUIMesa gui_mesa, File fichero, int numero){
    	this.gui_mesa = gui_mesa;
    	diapositiva = new Diapositiva(fichero, numero);
    	inicializacionGUIDiapositiva();
    	yo = this;
    }
    public GUIDiapositiva(){
    	this.gui_mesa = new GUIMesa(null, null);
        diapositiva = new Diapositiva(Constantes.DIAPO_DEFAULT);
        yo = this;
        inicializacionGUIDiapositiva();
    }
    
    /**
     * Asigna un numero a la GUIDiapositiva
     * @param n Numero
     */
    public void setNumeroGui(int n){
    	diapositiva.setNumero(n);
    	label_numero.setText(String.valueOf(n));
    }
    
    /**
     * Obtiene el numero de la diapositiva
     * @return
     */
    public int getNumero(){
    	return diapositiva.getNumero();
    }
    
    /**
     * Devuelve la logica de la GUIDiapositiva
     * @return Diapositiva
     */
    public Diapositiva getDiapositiva(){
    	return diapositiva;
    }
    
    /**
     * Sobre escribimos el metodo de redibujar el paner para poner un fondo.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
        if (marco!=null)
            g.drawImage(marco, 0, 0, null);
        Component c;
        for (int i = 0; i < getComponentCount(); i++) {
            c = getComponent(i);
            g.translate(c.getX(), c.getY());
            c.print(g);
            g.translate(-c.getX(), -c.getY());
        }
    }
    
    /**
     * Metodo privado que inicializa la GUIDiapositiva
     */
    private void inicializacionGUIDiapositiva(){
    	setLayout(null);
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(Constantes.TAM_DIAPOSITIVA,Constantes.TAM_DIAPOSITIVA));
    	
    	//cargar marco
        cargaMarco(Constantes.FICHERO_MARCO);

		//Cargamos la imagen
        cargaImagen(diapositiva);
        
		//Cargamos label que indica numero
        cargaNumeroDiapo();
		
		//Cargar label nombre foto
        cargarNombreFoto();
				
        cargarPopups();
        
        cargarControlesRaton(this);
    }
    
    /** Inicializa el Nombre en la GUIDiapositiva*/
    private void cargarNombreFoto(){
		label_nombre = new JLabel(diapositiva.getNombre());
		label_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		label_nombre.setBounds(6, 121, 141, 16);
		add(label_nombre);
    }
    
    /** Inicializa el Numero en la GUIDiapositiva*/
    private void cargaNumeroDiapo(){
    	label_numero = new JLabel(String.valueOf(diapositiva.getNumero()));
    	label_numero.setHorizontalAlignment(SwingConstants.CENTER);
    	label_numero.setBounds(87, 11, 53, 19);
		add(label_numero);
    }
    
    /** Carga la IMAGEN en la GUIDiapositiva*/
    private void cargaImagen(Diapositiva diapo){
    	label_foto = new JLabel();
		label_foto.setBounds(15, 35, 120, 90);
		try {
			setFoto(diapo.getFichero());
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(label_foto);
    }
    
    /** Carga el marco */
    private void cargaMarco(File marco){
    	try {
			setBackground(marco);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /** Carca la FOTO de la {@link GUIDiapositiva} */
    private void setFoto(File file) throws IOException{
    	label_foto.setIcon(new ImageIcon((new ImageIcon(file.toString()).getImage().getScaledInstance(120, 90, Image.SCALE_FAST))));
    }

    /**
     * Metodo interno que asigna un fondo a la GUIDiapositiva
     * @param file
     * @throws IOException
     */
    private void setBackground(File file) throws IOException{
        if (file==null)
            marco=null;
        else
            marco=ImageIO.read(file);
    }
    
    /** Carga los Popups que tendran las diapositivas */
    private void cargarPopups(){
    	JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
		mntmVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaVisorImagenes(gui_mesa.getlistaGUIDiapositivas(),Integer.parseInt(label_numero.getText())-1);
			}
		});
		popupMenu.add(mntmVisualizar);
		
		JMenuItem mntmDescartar = new JMenuItem("Descartar");
		mntmDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui_mesa.removeDiapositiva(yo);
			}
		});
		popupMenu.add(mntmDescartar);
    }
    
    /**
     * Añade a las diapositivas un menu estilo POPUP
     * @param component
     * @param popup
     */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
    
	/** Carga los controles del raton */
    private void cargarControlesRaton(JPanel p){
        p.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
        			//se ha hecho dos click con el boton primero (izquierdo)
        				new VentanaVisorImagenes(gui_mesa.getlistaGUIDiapositivas(),Integer.parseInt(label_numero.getText())-1);
        			}
        	}
        });
    }
 }