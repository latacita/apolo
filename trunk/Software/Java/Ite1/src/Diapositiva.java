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

/**
 * Clase Diapositiva.
 * Esta clase extiende a la clase JPanel, añadiendole ciertas funcionalidades.
 * - Dibuja un fondo que representa a una diapositiva clasica.
 * - Contiene un JLabel en el que se mostrara una imagen
 * 
 * @author Angel Tezanos Ibañez
 *
 */
 public class Diapositiva extends JPanel {

	 /** Atributos de la clase */
	 private static final long serialVersionUID = -3488034684833395929L;
	 public static final int tamDiapo = 150;
	 private final String stringMarco = "HDiapositiva.png";
	 private int numero;
	 private final File foto;
	 private Diapositiva diapositiva;
	 
	 private final Mesa mesa;
	 Image marco=null;
	 JLabel lFoto;
	 JLabel lNumeroDiapo;
	 JLabel lNombreFoto;
    
    public Diapositiva(Mesa m, File file){
        super();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(tamDiapo,tamDiapo));
        setLayout(null);
        foto = file;
        this.mesa = m;
        diapositiva = this;
        
        
        //cargar marco
        cargaMarco(new File(stringMarco));

		//Cargamos la imagen
        cargaImagen(file);
        
		//Cargamos label que indica numero
        cargaNumeroDiapo();
		
		//Cargar label nombre foto
        cargarNombreFoto(file);
				
        cargarPopups();
        
        cargarControlesRaton(this);
    }
    
    /** Carga los Popups que tendran las diapositivas */
    private void cargarPopups(){
    	JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
		mntmVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VisorDeImagenes(mesa.getListaDiapositivas(),Integer.parseInt(lNumeroDiapo.getText())-1);
			}
		});
		popupMenu.add(mntmVisualizar);
		
		JMenuItem mntmDescartar = new JMenuItem("Descartar");
		mntmDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesa.removeDiapositiva(diapositiva);
			}
		});
		popupMenu.add(mntmDescartar);
    }
    
    private void cargarControlesRaton(JPanel p){
        p.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
        			//se ha hecho dos click con el boton primero (izquierdo)
        				new VisorDeImagenes(mesa.getListaDiapositivas(),Integer.parseInt(lNumeroDiapo.getText())-1);
        			}
        	}
        });
    }
    
    private void cargarNombreFoto(File file){
		lNombreFoto = new JLabel("");
		lNombreFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lNombreFoto.setBounds(6, 121, 141, 16);
		setNombreFoto(file.getName());
		add(lNombreFoto);
    }
    
    private void cargaNumeroDiapo(){
    	lNumeroDiapo = new JLabel("");
    	lNumeroDiapo.setHorizontalAlignment(SwingConstants.CENTER);
    	lNumeroDiapo.setBounds(87, 11, 53, 19);
		add(lNumeroDiapo);
    }
    
    private void cargaImagen(File file){
    	lFoto = new JLabel();
		lFoto.setBounds(15, 35, 120, 90);
		try {
			setFoto(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(lFoto);
    }
    private void cargaMarco(File marco){
    	try {
			setBackground(marco);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public File getFile(){
    	return foto;
    }
    
    public void setNumeroFoto(String n){
    	numero = Integer.parseInt(n);
    	lNumeroDiapo.setText(n);
    }
    public void setNumeroFoto(int n){
    	numero = n;;
    	lNumeroDiapo.setText(Integer.toString(numero));
    }
    
    public int getNumero(){
    	return numero;
    }
    
    public void setNombreFoto(String s){
    	lNombreFoto.setText(s);
    }
    
    public void setFoto(File file) throws IOException{
    	lFoto.setIcon(new ImageIcon((new ImageIcon(file.toString()).getImage().getScaledInstance(120, 90, Image.SCALE_FAST))));
    }

    public void setBackground(File file) throws IOException{
        if (file==null)
            marco=null;
        else
            marco=ImageIO.read(file);
    }

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
 }