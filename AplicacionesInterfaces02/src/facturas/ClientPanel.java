package facturas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ClientPanel extends JPanel {
	private JTable table; 
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	public static  DefaultTableModel dtm;


	/**
	 * Create the panel.
	 */
	public ClientPanel() {
		
		table = new JTable();
		
		try {
			con = Conexion.getConnection();
			if(con == null)
				System.out.println("error.");
		
			stmt = con.createStatement();
			
			// secuencia SQL a la base de datos
			String sql = "SELECT  * FROM CUSTOMER;";
			rs = stmt.executeQuery(sql);
			
			dtm= (new DefaultTableModel(
			new Object[][] {

				
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Calle", "Ciudad"
			}
		));
			int columnas = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object[] objects = new Object[columnas];
				for(int i = 0; i < columnas; i++) {
					objects[i] = rs.getObject(i+1);
				}
				dtm.addRow(objects);
			}
		
				table.setModel(dtm);
				table.setBounds(352, 186, -337, -134);
				setLayout(new BorderLayout(0, 0));
				add(new JScrollPane(table));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
