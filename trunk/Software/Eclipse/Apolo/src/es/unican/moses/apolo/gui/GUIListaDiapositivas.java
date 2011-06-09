package es.unican.moses.apolo.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class GUIListaDiapositivas extends JPanel {

	/** AUTOGERENDADO */
	private static final long serialVersionUID = 1L;
	private LinkedList<GUIDiapositiva> listaDiapositivas;
	private JLabel lbl_foto1;
	private JLabel lbl_foto2;
	private JLabel lbl_foto3 ;
	private JLabel lbl_contadorSecuencia;

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
		
		lbl_foto1 = new JLabel("");
		lbl_foto1.setBounds(6, 6, 80, 80);
		add(lbl_foto1);
		
		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(46, 25, 80, 80);
		add(lbl_foto2);
		
		lbl_foto3 = new JLabel("");
		lbl_foto3.setBounds(87, 54, 80, 80);
		add(lbl_foto3);
		
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
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				break;
			case 2:
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				break;
			default:
				lbl_foto1.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getFirst().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				lbl_foto2.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.get(listaDiapositivas.size()/2).getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				lbl_foto3.setIcon(new ImageIcon((new ImageIcon(listaDiapositivas.getLast().getDiapositiva().getFichero().toString()).getImage().getScaledInstance(80, 80, Image.SCALE_FAST))));
				break;
			}
		}
	}
	
	public void setNumeroSecuencia(int numero){
		lbl_contadorSecuencia.setText(String.valueOf(numero));
	}
}
