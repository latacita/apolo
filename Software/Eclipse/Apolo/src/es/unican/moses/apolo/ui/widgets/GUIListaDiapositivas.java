package es.unican.moses.apolo.ui.widgets;

import java.awt.Dimension;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;

/**
 * Clase GUIListaDiapositivas
 * Representa a una serie de Diapositivas
 * Extiende a JPanel
 * En su interior contiene unos iconos que simbolizan una carpeta.
 * Se puestran fotos dinamicamente, dependiendo de las que contenga.
 * 
 * @author Angel
 *
 */
public class GUIListaDiapositivas extends JPanel {

	/** AUTOGERENDADO */
	private static final long serialVersionUID = 1L;
	/** Atributos */
	private LinkedList<GUIDiapositiva> listaDiapositivas;
	private JLabel lbl_foto1;
	private JLabel lbl_foto2;
	private JLabel lbl_foto3 ;
	private JLabel lbl_contadorSecuencia;
	private JLabel lbl_descripcion;
	private JLabel label_back;

	/**
	 * Create the panel.
	 */
	public GUIListaDiapositivas() {
		inicializacion();
	}
	public GUIListaDiapositivas(LinkedList<GUIDiapositiva> listaDiapositivas){
		this.listaDiapositivas = listaDiapositivas;
		inicializacion();
		cargaFotosDeLista();
	}
		
	/**
	 * Inicializacion de componentes
	 */
	private void inicializacion(){
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel lbl_subsecuencia = new JLabel("Secuencia");
		lbl_subsecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subsecuencia.setBounds(20, 20, 80, 16);
		add(lbl_subsecuencia);
		
		lbl_contadorSecuencia = new JLabel("0");
		lbl_contadorSecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_contadorSecuencia.setBounds(20, 40, 80, 16);
		add(lbl_contadorSecuencia);
		
		lbl_descripcion = new JLabel("");
		lbl_descripcion.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbl_descripcion.setForeground(new Color(0, 100, 0));
		lbl_descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_descripcion.setBounds(20, 66, 80, 54);
		add(lbl_descripcion);
		
		JLabel label_front = new JLabel("");
		label_front.setIcon(new ImageIcon(Constantes.RUTA_ICONO_ALBUM_FRONT));
		label_front.setBounds(6, 5, 105, 128);
		add(label_front);
		
		lbl_foto1 = new JLabel("");
		lbl_foto1.setBounds(73, 10, 80, 53);
		add(lbl_foto1);
		
		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(73, 48, 80, 53);
		add(lbl_foto2);
		
		lbl_foto3 = new JLabel("");
		lbl_foto3.setBounds(73, 75, 80, 53);
		add(lbl_foto3);
		setPreferredSize(new Dimension(170, 145));
		
		label_back = new JLabel("");
		label_back.setIcon(new ImageIcon(Constantes.RUTA_ICONO_ALBUM_BACK));
		label_back.setBounds(70, 5, 95, 128);
		add(label_back);

	}
	
	/**
	 * Metodo privado que carga las fotos dinamicamente.
	 */
	private void cargaFotosDeLista(){
		if (listaDiapositivas!=null){
			switch (listaDiapositivas.size()) {
			case 1:
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				break;
			default:
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				break;
			}
		}
	}
	
	/**
	 * Metodo que indica el numero de secuencia.
	 * @param numero
	 */
	public void setNumeroSecuencia(int numero){
		lbl_contadorSecuencia.setText(String.valueOf(numero));
	}
	
	/**
	 * Metodo que coloca una descripcion 
	 * @param text
	 */
	public void setDescripcion(String text){
		lbl_descripcion.setText(text);
	}
	
	/**
	 * Metodo que devuelve la lista de diapositivas que contiene
	 * @return
	 */
	public LinkedList<GUIDiapositiva> getLisDiapositivas(){
		return listaDiapositivas;
	}
}
