package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;

import es.unican.moses.apolo.ui.widgets.GUIAlbum;
import es.unican.moses.apolo.ui.widgets.GUIEstanteria;
import es.unican.moses.apolo.ui.widgets.GUIMesa;



public class DialogoGuardarEstado extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1083703956465044089L;
	/** Atributos */
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEstadoapl;
	private JLabel lbl_directorio;
	private JButton guardarButton;
	private JFrame padre;
	private JProgressBar progreso;
	private GUIAlbum gui_album;
	private GUIEstanteria gui_estanteria;
	private GUIMesa gui_mesa;

	/**
	 * Create the dialog.
	 */
	public DialogoGuardarEstado() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inicializar();
		setVisible(true);
	}
	public DialogoGuardarEstado(JFrame padre, GUIAlbum gui_album, GUIEstanteria gui_estanteria, GUIMesa gui_mesa) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.padre = padre;
		this.gui_album = gui_album;
		this.gui_estanteria = gui_estanteria;
		this.gui_mesa = gui_mesa;
		inicializar(); 
		setVisible(true);
	}
		
		
	private void inicializar(){
		setTitle("Guardar");
		setModal(true);
		setBounds(100, 100, 430, 227);
		setLocationRelativeTo(padre);
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{320, 110, 0};
		gbl_contentPanel.rowHeights = new int[]{96, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
			panel.setLayout(null);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			
			JLabel lblGuardarEn = new JLabel("Guardar en:");
			lblGuardarEn.setBounds(20, 67, 76, 16);
			panel.add(lblGuardarEn);
			
			JButton btnSeleccionarDirectorio = new JButton("Seleccionar Directorio");
			btnSeleccionarDirectorio.setToolTipText("Seleccionar directorio");
			btnSeleccionarDirectorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					accionBotonSeleccionarDirectorio();
				}
			});
			btnSeleccionarDirectorio.setBounds(96, 61, 177, 28);
			panel.add(btnSeleccionarDirectorio);
			
			lbl_directorio = new JLabel("");
			lbl_directorio.setToolTipText("No seleccionado");
			lbl_directorio.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_directorio.setBounds(20, 91, 253, 28);
			lbl_directorio.setText("No seleccionado");
			panel.add(lbl_directorio);
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(20, 32, 55, 16);
				panel.add(lblNombre);
			}
			
			txtEstadoapl = new JTextField();
			txtEstadoapl.setToolTipText("Nombre del fichero de estado");
			txtEstadoapl.setHorizontalAlignment(SwingConstants.RIGHT);
			txtEstadoapl.setText("Estado_Salvado");
			txtEstadoapl.setBounds(96, 26, 136, 28);
			panel.add(txtEstadoapl);
			txtEstadoapl.setColumns(10);
			
			JLabel lblapl = new JLabel(".apl");
			lblapl.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblapl.setBounds(233, 32, 55, 16);
			panel.add(lblapl);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Elementos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JCheckBox chckbxAlbums = new JCheckBox("Albums");
				chckbxAlbums.setToolTipText("Album de la aplicacion, parte superior de la misma");
				chckbxAlbums.setSelected(true);
				panel.add(chckbxAlbums);
			}
			{
				JCheckBox chckbxBaldas = new JCheckBox("Baldas");
				chckbxBaldas.setToolTipText("Baldas de la aplicacion, parte centrar de la misma");
				chckbxBaldas.setSelected(true);
				panel.add(chckbxBaldas);
			}
			{
				JCheckBox chckbxMesa = new JCheckBox("Mesa");
				chckbxMesa.setToolTipText("Mesa de la aplicacion, parte baja de la misma");
				chckbxMesa.setSelected(true);
				panel.add(chckbxMesa);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				guardarButton = new JButton("Guardar");
				guardarButton.setToolTipText("Guardar el estado del programa");
				guardarButton.setEnabled(false);
				guardarButton.setActionCommand("OK");
				buttonPane.add(guardarButton, BorderLayout.CENTER);
				getRootPane().setDefaultButton(guardarButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				cancelButton.setToolTipText("Cancelar la accion");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, BorderLayout.EAST);
			}
			{
				progreso = new JProgressBar();
				buttonPane.add(progreso, BorderLayout.SOUTH);
			}
		}
		
	}
	
	private void accionBotonSeleccionarDirectorio(){
		String ruta = seleccionarDirectorio();
		if (ruta==""){
			JOptionPane.showMessageDialog(padre, "Nombre y ruta no validos" , "Error", JOptionPane.INFORMATION_MESSAGE);
			lbl_directorio.setText("No seleccionado");
			lbl_directorio.setToolTipText("No seleccionado");
			guardarButton.setEnabled(false);
		}else{
			guardarButton.setEnabled(true);
			lbl_directorio.setText(ruta);
			lbl_directorio.setToolTipText(ruta);
		}
			
	}
	
	
	private String seleccionarDirectorio(){
		String ruta="";
		//Lanzar seleccionador de carpeta
		JFileChooser seleccionador = new JFileChooser(); 
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ruta = ""; 
		try{ 
			if(seleccionador.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){ 
				ruta = seleccionador.getSelectedFile().getAbsolutePath(); 
			}
		}catch (Exception ex){ 
			ex.printStackTrace(); 
		} 
		return ruta;
	}
	
	private void cancelar(){
		dispose();
	}
}
