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
	 
	 /**
	  * Atributos de la clase
	  */
	 private static final long serialVersionUID = -3488034684833395929L;
	 public static final int tamDiapo = 150;
	 private final String stringMarco = "HDiapositiva.png";
	 private final File foto;
	 

	 Image marco=null;
	 JLabel lFoto;
	 JLabel lNumeroDiapo;
	 JLabel lNombreFoto;
    
    public Diapositiva(File file){
        super();
        setPreferredSize(new Dimension(tamDiapo,tamDiapo));
        setLayout(null);
        foto = file;
        
        //cargar marco
        cargaMarco(new File(stringMarco));

		//Cargamos la imagen
        cargaImagen(file);
        
		//Cargamos label que indica numero
        cargaNumeroDiapo();
		
		//Cargar label nombre foto
        cargarNombreFoto(file);
				

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
		mntmVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VisualizadorImagen(foto);
			}
		});
		popupMenu.add(mntmVisualizar);
		
		JMenuItem mntmDescartar = new JMenuItem("Descartar");
		popupMenu.add(mntmDescartar);
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
    
    public void setNumeroFoto(String n){
    	lNumeroDiapo.setText(n);
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