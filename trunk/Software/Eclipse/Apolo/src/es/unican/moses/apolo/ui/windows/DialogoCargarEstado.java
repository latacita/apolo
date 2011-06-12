package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DialogoCargarEstado extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 8915839844016113774L;
	/** ATRIBUTOS */
	private JButton btnCancelar;
	private JButton btnCargar;
	private JButton btnSeleccionarFichero;
	private JLabel lbl_fichero;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoCargarEstado dialog = new DialogoCargarEstado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoCargarEstado() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Cargar fichero");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 259, 198);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JProgressBar progressBar = new JProgressBar();
			getContentPane().add(progressBar, BorderLayout.SOUTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Seleccione el fichero a cargar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{81, 49, 77, 0};
			gbl_panel.rowHeights = new int[]{39, 16, 28, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			JLabel lblFicheroACargar = new JLabel("Fichero a cargar:");
			GridBagConstraints gbc_lblFicheroACargar = new GridBagConstraints();
			gbc_lblFicheroACargar.fill = GridBagConstraints.VERTICAL;
			gbc_lblFicheroACargar.insets = new Insets(0, 0, 5, 5);
			gbc_lblFicheroACargar.gridx = 0;
			gbc_lblFicheroACargar.gridy = 0;
			panel.add(lblFicheroACargar, gbc_lblFicheroACargar);
			
			lbl_fichero = new JLabel("Seleccione un fichero");
			lbl_fichero.setHorizontalAlignment(SwingConstants.TRAILING);
			GridBagConstraints gbc_lbl_fichero = new GridBagConstraints();
			gbc_lbl_fichero.gridwidth = 2;
			gbc_lbl_fichero.fill = GridBagConstraints.VERTICAL;
			gbc_lbl_fichero.insets = new Insets(0, 0, 5, 0);
			gbc_lbl_fichero.gridx = 1;
			gbc_lbl_fichero.gridy = 0;
			panel.add(lbl_fichero, gbc_lbl_fichero);
			
			btnSeleccionarFichero = new JButton("Seleccionar fichero");
			GridBagConstraints gbc_btnSeleccionarFichero = new GridBagConstraints();
			gbc_btnSeleccionarFichero.fill = GridBagConstraints.BOTH;
			gbc_btnSeleccionarFichero.insets = new Insets(0, 0, 5, 0);
			gbc_btnSeleccionarFichero.gridwidth = 3;
			gbc_btnSeleccionarFichero.gridx = 0;
			gbc_btnSeleccionarFichero.gridy = 1;
			panel.add(btnSeleccionarFichero, gbc_btnSeleccionarFichero);
			
			btnCargar = new JButton("Cargar");
			btnCargar.setEnabled(false);
			GridBagConstraints gbc_btnCargar = new GridBagConstraints();
			gbc_btnCargar.fill = GridBagConstraints.BOTH;
			gbc_btnCargar.gridwidth = 2;
			gbc_btnCargar.insets = new Insets(0, 0, 0, 5);
			gbc_btnCargar.gridx = 0;
			gbc_btnCargar.gridy = 2;
			panel.add(btnCargar, gbc_btnCargar);
			
			btnCancelar = new JButton("Cancelar");
			GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.gridx = 2;
			gbc_btnCancelar.gridy = 2;
			panel.add(btnCancelar, gbc_btnCancelar);
		}
	}
}
