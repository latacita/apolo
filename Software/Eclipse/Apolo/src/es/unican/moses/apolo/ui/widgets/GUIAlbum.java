package es.unican.moses.apolo.ui.widgets;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.ImageIcon;

//Paquetes propios
import es.unican.moses.apolo.logic.Constantes;
import es.unican.moses.apolo.ui.windows.Apolo;

public class GUIAlbum extends JPanel implements Serializable{
	
	/** AUTOGENRERADO */
	private static final long serialVersionUID = 1L;
	private JPanel visor;
	private Apolo padre;

	/**
	 * Create the panel.
	 */
	public GUIAlbum() {
		initialice();
	}
	public GUIAlbum(Apolo padre) {
		this.padre = padre;
		initialice();
	}
	
	
	private void initialice(){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		visor = new JPanel();
		scrollPane.setViewportView(visor);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setIcon(new ImageIcon(Constantes.RUTA_ICON_CARGAR));
		btnCargar.setToolTipText("Cargar fichero");
		panel.add(btnCargar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.guardar();
			}
		});
		btnGuardar.setIcon(new ImageIcon(Constantes.RUTA_ICON_GUARDAR));
		btnGuardar.setToolTipText("Guardar en un fichero");
		panel.add(btnGuardar);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.importar();
			}
		});
		btnImportar.setIcon(new ImageIcon(Constantes.RUTA_ICON_IMPORTAR));
		btnImportar.setToolTipText("Importar fotografias");
		panel.add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				padre.exportar();
			}
		});
		btnExportar.setIcon(new ImageIcon(Constantes.RUTA_ICON_EXPORTAR));
		btnExportar.setToolTipText("Exportar fotografias");
		panel.add(btnExportar);

	}
	
	public void addListaFotos(LinkedList<GUIDiapositiva> listaDiapositivas, String descripcion){
		GUIListaDiapositivas pagAlbum = new GUIListaDiapositivas(listaDiapositivas);
		pagAlbum.setDescripcion(descripcion);
		visor.add(pagAlbum);
		renumerar();
		
			
		visor.validate();
	}
	
	private void renumerar(){
		for ( int i = 0 ; i < visor.getComponents().length; i++){
			if (visor.getComponent(i) instanceof GUIListaDiapositivas){
				((GUIListaDiapositivas)visor.getComponent(i)).setNumeroSecuencia(i+1);
			}
		}
	}
	
	public LinkedList<GUIListaDiapositivas> getListaGUIListaDiapositivas(){
		LinkedList<GUIListaDiapositivas> lista = new LinkedList<GUIListaDiapositivas>();
		for (Component c : visor.getComponents()){
			if (c instanceof GUIListaDiapositivas)
				lista.add(((GUIListaDiapositivas)c));
		}
		return lista;
	}
}
