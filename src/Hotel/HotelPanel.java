package Hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class HotelPanel {

	private JFrame frame;
	private JTextField nameFld;
	private JTextField emailFld;
	private JTextField fromFld;
	private JTextField tillFld;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelPanel window = new HotelPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HotelPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 465, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nameFld = new JTextField();
		nameFld.setBounds(184, 114, 174, 20);
		frame.getContentPane().add(nameFld);
		nameFld.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(83, 117, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(83, 148, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		emailFld = new JTextField();
		emailFld.setColumns(10);
		emailFld.setBounds(184, 145, 174, 20);
		frame.getContentPane().add(emailFld);
		
		JLabel lblNewLabel_3 = new JLabel("From");
		lblNewLabel_3.setBounds(83, 176, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		fromFld = new JTextField();
		fromFld.setColumns(10);
		fromFld.setBounds(184, 173, 174, 20);
		frame.getContentPane().add(fromFld);
		
		JLabel lblNewLabel_4 = new JLabel("Till");
		lblNewLabel_4.setBounds(83, 207, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		tillFld = new JTextField();
		tillFld.setColumns(10);
		tillFld.setBounds(184, 204, 174, 20);
		frame.getContentPane().add(tillFld);
		
		JLabel lblNewLabel_2 = new JLabel("Checkin Entry");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 47, 429, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnCheckin = new JButton("Checkin");
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "";
				data = data+nameFld.getText();
				data =data+"--";
				data = data+emailFld.getText();
				data =data+"--";
				data = data+fromFld.getText();
				data =data+"--";
				data = data+tillFld.getText();

				try {
					Database.store(data);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCheckin.setBounds(260, 235, 100, 39);
		frame.getContentPane().add(btnCheckin);
		
		JButton btnReport = new JButton("Report Covid");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ViewCheckins().start();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				;
			}
		});
		btnReport.setBounds(10, 313, 133, 23);
		frame.getContentPane().add(btnReport);
	}
}
