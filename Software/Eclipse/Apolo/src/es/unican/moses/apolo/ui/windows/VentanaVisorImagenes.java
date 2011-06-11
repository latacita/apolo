package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;


/**
 * Clase que muestra una ventana mostrando una imagen.
 * Permitiendo la navegacion hacia delante o atras en las imagenes.
 * @author Angel
 *
 */
public class VentanaVisorImagenes extends JDialog {

	/** Autogenerado */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	private final JPanel contentPanel = new JPanel();
	private int numfoto;
	private LinkedList<GUIDiapositiva> listafotos;
	
	/** Componentes Internos*/
	private ImageIcon imagenCargada;
	private JLabel label_foto;

	
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaVisorImagenes(LinkedList<GUIDiapositiva> listafotos, int num) {
		setResizable(false);
		//Carga de parametros
		this.listafotos = listafotos;
		numfoto = num;
		inicializacion();
	}

	private void inicializacion(){
		
		setTitle("Visualizador de Imagenes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		
		
		{
			label_foto = new JLabel("");
			label_foto.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(label_foto);
		}
		
		cargaImagen(listafotos.get(numfoto).getDiapositiva().getFichero());
		redimensionarVentana();
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton btnSiguiente = new JButton("Siguiente");
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ((numfoto+1)<listafotos.size()){
							cargaImagen(listafotos.get(numfoto+1).getDiapositiva().getFichero());
							numfoto++;
						}else{
							//new MensajeInformacion("Usted esta en la ultima foto de la lista");
						}
					}
				});
				buttonPane.add(btnSiguiente, BorderLayout.EAST);
			}
			{
				JButton btnAnterior = new JButton("Anterior");
				btnAnterior.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ((numfoto-1)>=0){
							cargaImagen(listafotos.get(numfoto-1).getDiapositiva().getFichero());
							numfoto--;
						}else{
							//new MensajeInformacion("Usted esta en la primera foto de la lista");
						}
					}
				});
				buttonPane.add(btnAnterior, BorderLayout.WEST);
			}
			{
				JLabel lblNombreDeLa = new JLabel("Nombre: '" + listafotos.get(numfoto).getDiapositiva().getNombre()+ 
						"' ------ Tamaño: " + listafotos.get(numfoto).getDiapositiva().getAnchura() + 
						"x" + listafotos.get(numfoto).getDiapositiva().getAltura() + "px");
				lblNombreDeLa.setHorizontalAlignment(SwingConstants.CENTER);
				buttonPane.add(lblNombreDeLa, BorderLayout.CENTER);
			}
			{
				JSeparator separator = new JSeparator();
				buttonPane.add(separator, BorderLayout.NORTH);
			}
		}
		
		setVisible(true);
	}
	
	/**
	 * Carga imagen el Jlabel
	 * @param f
	 */
	private void cargaImagen(File f){
		imagenCargada = new ImageIcon(f.getAbsolutePath());
		redimensionarVentana();
	}
	
	/**
	 * Centrar y dar tamaño a la ventana
	 */
	private void redimensionarVentana(){
		Dimension dimensioFoto = calcularDimensionesFoto(imagenCargada);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		label_foto.setBounds(0, 0, dimensioFoto.width, dimensioFoto.height);
		label_foto.setIcon(new ImageIcon(imagenCargada.getImage().getScaledInstance(dimensioFoto.width, dimensioFoto.height, Image.SCALE_FAST)));
		setBounds(screenSize.width/2-dimensioFoto.width/2, screenSize.height/2-dimensioFoto.height/2-100, dimensioFoto.width+5, dimensioFoto.height+60);
	}
	
	
	/**
	 * Calcular dimensiones optimas del jlabel que presentara la imagen, dependiendo de la foto
	 * En otras palabras, si no entra se reescala para que entre.
	 */
	public Dimension calcularDimensionesFoto(ImageIcon imagen){
		int margen = 300; //margen que dejamos para que la ventana no ocupe todo
		Dimension dimensionFinal = new Dimension();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimensionFoto = new Dimension(imagen.getIconWidth(),imagen.getIconHeight());
		//Comprobacion de que entre la foto en la pantalla
		if(screenSize.width - margen < dimensionFoto.width || screenSize.height - margen < dimensionFoto.height){
			//Reescalar imagen. No entra en el tamaño original
			//Divido altura y anchura y veo cual es mas restrictiva, para escalar segun esa..
			double divX, divY;
			divX = (double)(screenSize.width - margen) / (double)dimensionFoto.width;
			divY = (double)(screenSize.height - margen) / (double)dimensionFoto.height;
			if (divX > divY){
				//divY mas restrictiva
				dimensionFinal.setSize((int)dimensionFoto.width*divY,(int)dimensionFoto.height*divY);
			}else{
				//divX mas restrictiva
				dimensionFinal.setSize((int)dimensionFoto.width*divX,(int)dimensionFoto.height*divX);
			}
		}else{
			//La foto entra en la pantalla a tamaño original
			dimensionFinal.setSize(dimensionFoto.width, dimensionFoto.height);
		}
		return dimensionFinal;
	}

}
