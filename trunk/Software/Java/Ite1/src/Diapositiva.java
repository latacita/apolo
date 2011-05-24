import java.awt.*;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

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
 private static final long serialVersionUID = -3488034684833395929L;

    Image marco=null;
    JLabel foto;
    JLabel numeroFoto;
    JLabel pesoFoto;
    
    public Diapositiva(File file){
        super();
        setLayout(null);
        
        //Cargamos el marco
        try {
			setBackground(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
		popupMenu.add(mntmVisualizar);
		
		JMenuItem mntmDescartar = new JMenuItem("Descartar");
		popupMenu.add(mntmDescartar);
		//Cargamos el Jlabel
		foto = new JLabel("");
		foto.setBounds(15, 35, 120, 90);
		add(foto);
		
		numeroFoto = new JLabel("");
		numeroFoto.setHorizontalAlignment(SwingConstants.CENTER);
		numeroFoto.setBounds(87, 11, 53, 19);
		add(numeroFoto);
		
		pesoFoto = new JLabel("");
		pesoFoto.setHorizontalAlignment(SwingConstants.CENTER);
		pesoFoto.setBounds(6, 121, 141, 16);
		add(pesoFoto);
    }
    
    public void setNumeroFoto(String n){
    	numeroFoto.setText(n);
    }
    
    public void setNombreFoto(String s){
    	pesoFoto.setText(s);
    }
    
    public void setFoto(File file) throws IOException{
    	foto.setIcon(new ImageIcon((new ImageIcon(file.toString()).getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH))));
    }
    
    public void setFoto(URL url) throws IOException{
    	foto.setIcon(new ImageIcon((new ImageIcon(url.toString()).getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH))));
    }

    public void setBackground(File file) throws IOException{
        if (file==null)
            marco=null;
        else
            marco=ImageIO.read(file);
    }

    public void setBackground(URL url) throws IOException{
        if (url==null)
            marco=null;
        else
            marco=ImageIO.read(url);
    }
    
    public void setBackground(Image img) throws IOException{
        if (img==null)
            marco=null;
        else
            marco=img;
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