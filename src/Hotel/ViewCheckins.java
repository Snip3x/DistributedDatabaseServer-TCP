package Hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewCheckins extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static ViewCheckins frame;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewCheckins();
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
	public ViewCheckins() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton back = new JButton("Back");
		panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		JButton reportSelected = new JButton("Report Selected");
		panel.add(reportSelected);
		
		JLabel lblNewLabel = new JLabel("Checkin List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Email", "From", "Till"
			}
		){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<String> list = Database.getCheckins();
		for (String s : list) {
			model.addRow(s.split("--"));
		}

		reportSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row =table.getSelectedRow();
				String report = table.getValueAt(row,1).toString();
				report += "--"+table.getValueAt(row,2);
				report += "--"+table.getValueAt(row,0);
				report += "--"+Hotel.HotelID;
				report += "--"+table.getValueAt(row,3);
				report += "--"+table.getValueAt(row,4);
				try {
					Hotel.out.writeInt(2);
					Hotel.out.writeUTF(report);

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
