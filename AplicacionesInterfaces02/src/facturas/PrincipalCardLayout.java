package facturas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;

public class PrincipalCardLayout extends JFrame {
	private JPanel card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalCardLayout frame = new PrincipalCardLayout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalCardLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		card = new JPanel();
		setContentPane(card);
		card.setLayout(new CardLayout(0, 0));
		card.add(new JPanel(),"default");

		ClientPanel clientPanel = new ClientPanel();
		card.add(clientPanel, "clientes");

		PanelFacturaCliente facturacliente = new PanelFacturaCliente();
		card.add(facturacliente, "facturas");

		JLabel lblPanelFacturas = new JLabel();
		facturacliente.add(lblPanelFacturas);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mnConsultar = new JMenuItem("Consultar");
		menuBar.add(mnConsultar);
		mnConsultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				itemStateChanged("clientes");
			}
		});

		
		JMenuItem mnFactoraPorCliente = new JMenuItem("Factora por cliente");
		menuBar.add(mnFactoraPorCliente);
		mnFactoraPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemStateChanged("facturas");
			}
		});

		JMenuItem mnFacturaPorId = new JMenuItem("Factura por ID");
		menuBar.add(mnFacturaPorId);
		

	}

	public void itemStateChanged(String evt) {
		CardLayout cl = (CardLayout) (card.getLayout());
		cl.show(card, evt);
	}
}
