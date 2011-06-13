package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Clase DialogoSolicitaNombreONumeracion
 * Muestra un dialogo modal en el que se pide 
 * si se desea un nombre para todas las fotos junto con un numero
 * o solo un numero.
 * @author Angel
 *
 */
public class DialogoSolicitaNombreONumeracion extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = -6742368992096779117L;
	/** Atributos */
	private JComboBox comboBox;
	private JTextField textField;
	private JLabel lblNombreComun;
	private String nombre;

	/**
	 * Create the dialog.
	 */
	public DialogoSolicitaNombreONumeracion(){
		this.nombre = "";
		inicializacion();
	}
	
	/** 
	 * Inicializa los componentes
	 */
	private void inicializacion(){
		setTitle("Tipo de nombre para las fotografias exportadas");
		setResizable(false);
		setBounds(100, 100, 501, 160);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cerrarVentana();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{20, 105, 191, 155, 0};
			gbl_panel.rowHeights = new int[]{25, 22, 28, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			comboBox = new JComboBox();
			comboBox.addItem(new String("Solo numeracion"));
			comboBox.addItem(new String("Nombre y numeracion"));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (comboBox.getSelectedIndex()==1){
						textField.setEnabled(true);
						lblNombreComun.setEnabled(true);
					}else{
						textField.setEnabled(false);
						lblNombreComun.setEnabled(false);
					}
				}
			});
			
			JLabel lblIndiqueElTipo = new JLabel("Indique el tipo de nombre que desea para sus fotos");
			lblIndiqueElTipo.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lblIndiqueElTipo = new GridBagConstraints();
			gbc_lblIndiqueElTipo.insets = new Insets(0, 0, 5, 5);
			gbc_lblIndiqueElTipo.gridwidth = 2;
			gbc_lblIndiqueElTipo.gridx = 1;
			gbc_lblIndiqueElTipo.gridy = 1;
			panel.add(lblIndiqueElTipo, gbc_lblIndiqueElTipo);
			comboBox.setMaximumRowCount(2);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.anchor = GridBagConstraints.WEST;
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 1;
			panel.add(comboBox, gbc_comboBox);
			
			
			lblNombreComun = new JLabel("Nombre Comun:");
			lblNombreComun.setEnabled(false);
			GridBagConstraints gbc_lblNombreComun = new GridBagConstraints();
			gbc_lblNombreComun.insets = new Insets(0, 0, 0, 5);
			gbc_lblNombreComun.gridx = 1;
			gbc_lblNombreComun.gridy = 2;
			panel.add(lblNombreComun, gbc_lblNombreComun);
			lblNombreComun.setHorizontalAlignment(SwingConstants.LEFT);
			
			textField = new JTextField();
			textField.setEnabled(false);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 2;
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 2;
			panel.add(textField, gbc_textField);
			textField.setColumns(10);
			
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
		}	
	}
	
	/**
	 * Metodo que se realiza cuando se cierra la ventana
	 */
	private void cerrarVentana(){
		if (comboBox.getSelectedIndex() == 0){
			nombre = null;
		}else{
			nombre = textField.getText() + "_";
		}
		this.setVisible(false);
	}
	
	/**
	 * Retorna la descripcion introducida.
	 * @return
	 */
	public String retorno(){
		return nombre;
	}
}
