package Management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ManagmentPanel {

	private JFrame frame;
	JLabel LblStatus;
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagmentPanel window = new ManagmentPanel();
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
	public ManagmentPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 476, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hotels Record Management Server");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 33, 440, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnVbackup = new JButton("View Backup");
		btnVbackup.setBounds(166, 97, 126, 23);
		frame.getContentPane().add(btnVbackup);
		btnVbackup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new ViewBackup().start();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnStartbackup = new JButton("Make Backup");
		btnStartbackup.setBounds(166, 143, 126, 23);
		frame.getContentPane().add(btnStartbackup);
		btnStartbackup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ManagementServer.connectionList.size()==0)
					return;
				ManagementServer.databasebusy = true;
				for (Connection connection : ManagementServer.connectionList) {
					try {
						connection.out.writeInt(1);
						System.out.println("happpp");
						int size = connection.in.readInt();
						for (int i = 0; i<size; i++){
							Database.storeBackup(connection.in.readUTF());
							Thread.sleep(500);
						}


					} catch (IOException | InterruptedException | SQLException ex) {
						ex.printStackTrace();
					}
				}
				ManagementServer.databasebusy = false;
			}
		});
		
		JButton btnViewHotels = new JButton("Hotels List");
		btnViewHotels.setBounds(166, 189, 126, 23);
		frame.getContentPane().add(btnViewHotels);
		btnViewHotels.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new ViewHotels().start();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton btnReportView = new JButton("Covid Report");
		btnReportView.setBounds(166, 235, 126, 23);
		frame.getContentPane().add(btnReportView);
		btnReportView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewReports().start();
			}
		});

		JLabel LblStatus = new JLabel("Status: Running");
		LblStatus.setBounds(10, 303, 429, 14);
		frame.getContentPane().add(LblStatus);
	}
}
