package Hotel;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Hotel extends JFrame {

	/**
	 * 
	 */
	static Socket connection;
	static int HotelID;
	static DataOutputStream out;
	static DataInputStream in;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fldVeriCode;
	static Hotel frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Hotel();
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
	public Hotel() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Hotel Verification ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 46, 414, 37);
		contentPane.add(lblNewLabel);
		
		fldVeriCode = new JTextField();
		fldVeriCode.setBounds(117, 111, 202, 20);
		contentPane.add(fldVeriCode);
		fldVeriCode.setColumns(10);

		connection = new Socket("localhost",5500);
		out = new DataOutputStream(connection.getOutputStream());
		in = new DataInputStream(connection.getInputStream());


		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					out.writeUTF(fldVeriCode.getText());
					HotelID = in.readInt();
					if(HotelID!=-1){
					new HotelPanel().start();
					new Thread(new Connection()).start();
					frame.dispose();
					}
					else{
						connection.close();
					System.exit(0);}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnStart.setBounds(168, 157, 89, 23);
		contentPane.add(btnStart);
	}

}
