package facturas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelFacturaCliente extends JPanel {
	private JTextField tfCliente;
	private JLabel lblSeleccionaUnCliente;
	private Connection con;
	private PreparedStatement pt;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;

	/**
     * Create the panel.
     */
    public PanelFacturaCliente() {
            setToolTipText("");
            setLayout(new BorderLayout(0, 0));

            panel = new JPanel();
            add(panel, BorderLayout.NORTH);

            lblSeleccionaUnCliente = new JLabel("Selecciona un Cliente:");
            panel.add(lblSeleccionaUnCliente);

            tfCliente = new JTextField();
            panel.add(tfCliente);
            tfCliente.setColumns(10);

            JButton btnGenerarFactura = new JButton("Generar Factura");
            panel.add(btnGenerarFactura);

            scrollPane = new JScrollPane();
            add(scrollPane, BorderLayout.CENTER);

            table = new JTable();
            scrollPane.setViewportView(table);

            btnGenerarFactura.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            generarFacturas();
                    }
            });

    }

	public void generarFacturas() {
		try {
			con = Conexion.getConnection();

			eliminar();
			String sql = "SELECT * FROM invoice WHERE customerid = ?";

			pt = con.prepareStatement(sql);

			pt.setString(1, tfCliente.getText().toString());

			ResultSet rs = pt.executeQuery();

			// TableModel definition
			String[] tableColumnsName = { "ID", "CUSTOMERID", "TOTAL" };
			DefaultTableModel aModel = (DefaultTableModel) table.getModel();
			aModel.setColumnIdentifiers(tableColumnsName);

			// Loop through the ResultSet and transfer in the Model
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[colNo];
				for (int i = 0; i < colNo; i++) {
					objects[i] = rs.getObject(i + 1);
				}
				aModel.addRow(objects);
			}
			table.setModel(aModel);
			repaint();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void eliminar() {
		DefaultTableModel tb = (DefaultTableModel) table.getModel();
		int a = table.getRowCount() - 1;
		for (int i = a; i >= 0; i--) {
			tb.removeRow(tb.getRowCount() - 1);
		}
	}

}
