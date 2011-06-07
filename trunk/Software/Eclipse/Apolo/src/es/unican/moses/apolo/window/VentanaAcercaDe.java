package es.unican.moses.apolo.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Clase que Dibuja una ventana mostrando informacion acerca de la aplicacion
 * y su autor.
 * @author Angel
 */
public class VentanaAcercaDe extends JDialog {

	/** Autogenerado */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public VentanaAcercaDe() {
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setTitle("Acerca de Apolo");
		setBounds(100, 100, 600, 251);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblProyectoFinDe = new JLabel("Proyecto Fin de Carrera de Angel Tezanos Iba\u00F1ez");
		lblProyectoFinDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblProyectoFinDe.setBounds(167, 13, 415, 27);
		panel.add(lblProyectoFinDe);
		
		JLabel label_2 = new JLabel("<html>Apolo surgio como Proyecto Fin de Carrera de Angel Tezanos Iba\u00F1ez. <br>" +
				"El software se dise\u00F1o con el fin de disponer de una herramienta " +
				"capaz de organizar y clasificar fotografias como si estas se trataran de diapositivas. " +
				"Para ello se dise�o una interfaz grafica de usuario que recordase a  " +
				"clasico clasificador de diapositivas. De esta manera se intenta lograr ese aire nostalgico " +
				"de las antiguas diapositivas.</html>");
		label_2.setBounds(167, 53, 415, 117);
		panel.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(12, 13, 133, 159);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(1, 1, 131, 157);
		panel_1.add(lblFoto);
		lblFoto.setIcon(new ImageIcon("img/Yo.jpg"));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		
		setVisible(true);
	}
}