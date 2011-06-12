package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class DialogoPropiedades extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoPropiedades dialog = new DialogoPropiedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoPropiedades() {
		setResizable(false);
		setTitle("Propiedades");
		setModal(true);
		setBounds(100, 100, 357, 366);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion de archivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Informacion de Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
			}
		}
	}

}
