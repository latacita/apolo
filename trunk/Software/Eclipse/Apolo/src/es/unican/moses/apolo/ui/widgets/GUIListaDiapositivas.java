package es.unican.moses.apolo.ui.widgets;

import java.awt.Dimension;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import es.unican.moses.apolo.logic.Constantes;

public class GUIListaDiapositivas extends JPanel {

	/** AUTOGERENDADO */
	private static final long serialVersionUID = 1L;
	private LinkedList<GUIDiapositiva> listaDiapositivas;
	private JLabel lbl_foto1;
	private JLabel lbl_foto2;
	private JLabel lbl_foto3 ;
	private JLabel lbl_contadorSecuencia;
	private JLabel marco_1;
	private JLabel marco_2;
	private JLabel marco_3;
	private ImageIcon marcoAlbum;

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
		
		
	private void inicializacion(){
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		//Marcos
		marcoAlbum = new ImageIcon(Constantes.RUTA_MARCO_ALBUM);
		
		lbl_foto1 = new JLabel("");
		lbl_foto1.setBounds(11, 10, 70, 46);
		add(lbl_foto1);
				
		marco_1 = new JLabel("");
		marco_1.setBounds(6, 6, 80, 53);
		marco_1.setVisible(false);
		add(marco_1);
		
		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(51, 38, 70, 46);
		add(lbl_foto2);
		
		marco_2 = new JLabel("");
		marco_2.setBounds(46, 34, 80, 53);
		marco_2.setVisible(false);
		add(marco_2);
		
		lbl_foto3 = new JLabel("");
		lbl_foto3.setBounds(92, 58, 70, 46);
		add(lbl_foto3);
		
		marco_3 = new JLabel("");
		marco_3.setBounds(87, 54, 80, 53);
		marco_3.setVisible(false);
		add(marco_3);
		
		JLabel lbl_subsecuencia = new JLabel("Secuencia");
		lbl_subsecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_subsecuencia.setBounds(6, 108, 80, 16);
		add(lbl_subsecuencia);
		
		lbl_contadorSecuencia = new JLabel("0");
		lbl_contadorSecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_contadorSecuencia.setBounds(6, 122, 80, 16);
		add(lbl_contadorSecuencia);
		setPreferredSize(new Dimension(170, 145));

	}
	
	private void cargaFotosDeLista(){
		if (listaDiapositivas!=null){
			switch (listaDiapositivas.size()) {
			case 1:
				marco_2.setIcon(marcoAlbum);
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				marco_2.setVisible(true);
				break;
			case 2:
				marco_1.setIcon(marcoAlbum);
				marco_3.setIcon(marcoAlbum);
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				marco_1.setVisible(true);
				marco_3.setVisible(true);
				break;
			default:
				marco_1.setIcon(marcoAlbum);
				marco_2.setIcon(marcoAlbum);
				marco_3.setIcon(marcoAlbum);
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.get(listaDiapositivas.size()/2).getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(70, 46, Image.SCALE_FAST))));
				marco_1.setVisible(true);
				marco_2.setVisible(true);
				marco_3.setVisible(true);
				break;
			}
		}
	}
	
	public void setNumeroSecuencia(int numero){
		lbl_contadorSecuencia.setText(String.valueOf(numero));
	}
}
