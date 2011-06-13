package es.unican.moses.apolo.ui.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Paquetes propios
import es.unican.moses.apolo.logic.Diapositiva;

public class DialogoPropiedades extends JDialog {

	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	private Diapositiva diapo;
	private JLabel lbl_nombre;
	private JLabel lbl_ruta;
	private JLabel lbl_fecha;
	private JLabel lbl_tamano;
	private JLabel lbl_anchura;
	private JLabel lbl_altura;
	private JComponent padre;
	

	/**
	 * Create the dialog.
	 */
	public DialogoPropiedades(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inicializacion();
	}
	public DialogoPropiedades(JComponent padre, Diapositiva diapo){
		this.diapo = diapo;
		this.padre = padre;
		inicializacion();
		setValores();
		setVisible(true);
	}
	
	private void inicializacion(){
		setResizable(false);
		setTitle("Propiedades");
		setModal(true);
		setBounds(100, 100, 357, 366);
		setLocationRelativeTo(padre);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
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
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion de archivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setLayout(null);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
				lblNombre.setBounds(23, 31, 120, 16);
				panel_1.add(lblNombre);
				{
					JLabel lblRuta = new JLabel("Ruta:");
					lblRuta.setHorizontalAlignment(SwingConstants.TRAILING);
					lblRuta.setBounds(23, 59, 120, 16);
					panel_1.add(lblRuta);
				}
				{
					JLabel lblUltimaModificacion = new JLabel("Ultima Modificacion:");
					lblUltimaModificacion.setHorizontalAlignment(SwingConstants.TRAILING);
					lblUltimaModificacion.setBounds(23, 87, 120, 16);
					panel_1.add(lblUltimaModificacion);
				}
				{
					JLabel lblTamao = new JLabel("Tama\u00F1o:");
					lblTamao.setHorizontalAlignment(SwingConstants.TRAILING);
					lblTamao.setBounds(88, 115, 55, 16);
					panel_1.add(lblTamao);
				}
				{
					lbl_nombre = new JLabel("");
					lbl_nombre.setFont(new Font("SansSerif", Font.BOLD, 12));
					lbl_nombre.setBounds(148, 31, 186, 16);
					panel_1.add(lbl_nombre);
				}
				{
					lbl_ruta = new JLabel("");
					lbl_ruta.setFont(new Font("SansSerif", Font.BOLD, 12));
					lbl_ruta.setBounds(148, 59, 186, 16);
					panel_1.add(lbl_ruta);
				}
				{
					lbl_fecha = new JLabel("");
					lbl_fecha.setFont(new Font("SansSerif", Font.BOLD, 12));
					lbl_fecha.setBounds(148, 87, 186, 16);
					panel_1.add(lbl_fecha);
				}
				{
					lbl_tamano = new JLabel("");
					lbl_tamano.setHorizontalAlignment(SwingConstants.TRAILING);
					lbl_tamano.setFont(new Font("SansSerif", Font.BOLD, 12));
					lbl_tamano.setBounds(148, 115, 47, 16);
					panel_1.add(lbl_tamano);
				}
				{
					JLabel lblBytes = new JLabel("MB");
					lblBytes.setBounds(200, 115, 55, 16);
					panel_1.add(lblBytes);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Informacion de Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setLayout(null);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				{
					JLabel lblAnchura = new JLabel("Anchura:");
					lblAnchura.setHorizontalAlignment(SwingConstants.TRAILING);
					lblAnchura.setBounds(89, 34, 55, 16);
					panel_1.add(lblAnchura);
				}
				{
					JLabel lblAltura = new JLabel("Altura:");
					lblAltura.setHorizontalAlignment(SwingConstants.TRAILING);
					lblAltura.setBounds(89, 62, 55, 16);
					panel_1.add(lblAltura);
				}
				{
					lbl_anchura = new JLabel("");
					lbl_anchura.setFont(new Font("SansSerif", Font.PLAIN, 13));
					lbl_anchura.setHorizontalAlignment(SwingConstants.TRAILING);
					lbl_anchura.setBounds(156, 34, 55, 16);
					panel_1.add(lbl_anchura);
				}
				{
					lbl_altura = new JLabel("");
					lbl_altura.setFont(new Font("SansSerif", Font.PLAIN, 13));
					lbl_altura.setHorizontalAlignment(SwingConstants.TRAILING);
					lbl_altura.setBounds(156, 62, 55, 16);
					panel_1.add(lbl_altura);
				}
				{
					JLabel lblPx = new JLabel("px");
					lblPx.setBounds(216, 34, 28, 16);
					panel_1.add(lblPx);
				}
				{
					JLabel label = new JLabel("px");
					label.setBounds(216, 62, 28, 16);
					panel_1.add(label);
				}
			}
		}
	}
	
	private void setValores(){
		lbl_nombre.setText(diapo.getFichero().getName());
		lbl_ruta.setText(diapo.getFichero().getPath());
		Date date = new Date(diapo.getFichero().lastModified());
		GregorianCalendar calen = new GregorianCalendar();
		calen.setTime(date);
		lbl_fecha.setText(String.valueOf(calen.get(Calendar.DATE)) + "/" + String.valueOf(calen.get(Calendar.MONTH)) + "/" + String.valueOf(calen.get(Calendar.YEAR)));
		double tam = ((double)diapo.getFichero().length())/((double)(1024*1024));
		DecimalFormat format = new DecimalFormat("##.##");
		lbl_tamano.setText(format.format(tam)); //Para que lo de en MB
		lbl_altura.setText(String.valueOf(diapo.getAltura()));
		lbl_anchura.setText(String.valueOf(diapo.getAnchura()));
	}
	
}
