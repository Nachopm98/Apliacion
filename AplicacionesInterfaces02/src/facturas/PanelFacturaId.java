package facturas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTextPane;

public class PanelFacturaId extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelFacturaId() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton btnCrear = new JButton("Crear");
		panel.add(btnCrear);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		
		
		
	}

}
