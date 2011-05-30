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

/**
 * Clase que muestra una ventana mostrando una imagen.
 * Permitiendo la navegacion hacia delante o atras en las imagenes.
 * @author Angel
 *
 */
public class VisorDeImagenes extends JDialog {

	/** Autogenerado */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	private final JPanel contentPanel = new JPanel();
	private LinkedList<Diapositiva> lista;
	private int numfoto;
	private File file;
	private ImageIcon imagenCargada;
	private JLabel lblFoto;

	/**
	 * Create the dialog.
	 */
	public VisorDeImagenes(String str){
		this(new File (str));
	}
	public VisorDeImagenes(File file){
		lista = new LinkedList<Diapositiva>();
		lista.add(new Diapositiva(null, file));
		new VisorDeImagenes(lista, 0);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public VisorDeImagenes(LinkedList<Diapositiva> l, int num) {
		//Carga de parametros
		lista = l;
		numfoto = num;
		file = l.get(numfoto).getFile();

		setTitle("Visualizador de Imagenes");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setResizable(false);
		
		
		{
			lblFoto = new JLabel("");
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblFoto);
		}
		
		cargaImagen(file);
		redimensionarVentana();
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton btnSiguiente = new JButton("Siguiente");
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ((numfoto+1)<lista.size()){
							cargaImagen(lista.get(numfoto+1).getFile());
							numfoto++;
							file = lista.get(numfoto).getFile();
						}else{
							new MensajeInformacion("Usted esta en la ultima foto de la lista");
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
							cargaImagen(lista.get(numfoto-1).getFile());
							numfoto--;
							file = lista.get(numfoto).getFile();
						}else{
							new MensajeInformacion("Usted esta en la primera foto de la lista");
						}
					}
				});
				buttonPane.add(btnAnterior, BorderLayout.WEST);
			}
			{
				JLabel lblNombreDeLa = new JLabel("Nombre: '" + file.getName()+ "' ------ Tamaño: " + imagenCargada.getIconWidth() + "x" + imagenCargada.getIconHeight() + "px");
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
		setBounds(screenSize.width/2-dimensioFoto.width/2, screenSize.height/2-dimensioFoto.height/2-100, dimensioFoto.width+5, dimensioFoto.height+60);
		lblFoto.setBounds(0, 0, dimensioFoto.width, dimensioFoto.height);
		lblFoto.setIcon(new ImageIcon(imagenCargada.getImage().getScaledInstance(dimensioFoto.width, dimensioFoto.height, Image.SCALE_FAST)));
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
