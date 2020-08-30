import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.function.LongToIntFunction;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import org.omg.CORBA.SetOverrideType;

import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	public JTextField textUsername;
	public JPasswordField passwordField;
	public static LoginForm frame = new LoginForm();
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	

	/**
	 * Create the frame.
	 */
	
	
	public LoginForm() {
		
		connection = mySqlConnection.dbConnector();
		
		setTitle("Login ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 319);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setToolTipText("");
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, SystemColor.controlShadow, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		textUsername = new JTextField();
		textUsername.setBounds(295, 67, 390, 32);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(160, 67, 135, 32);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(160, 142, 125, 32);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(295, 142, 390, 32);
		contentPane.add(passwordField);
		
		 JButton btnNewButton = new JButton("LOGIN");
		 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
											
				
				if (textUsername.getText().length() == 0 || passwordField.getText().length() == 0) 
				{ JOptionPane.showMessageDialog(LoginForm.this, "Username/Password required.", "Error", JOptionPane.WARNING_MESSAGE);
				return;
				}				
				
				
				
				try {
					String md5Hash = HashGeneratorUtils.generateMD5(passwordField.getText());
					System.out.println("Input String: " + passwordField.getText());
			        System.out.println("MD5 Hash: " + md5Hash);
					}catch(HashGenerationException ex) {
						ex.printStackTrace();
						
					}
								
				
				
				try {
					String query  = "select * from conturi where username=? and password=MD5(?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textUsername.getText());
					pst.setString(2, passwordField.getText());						
					ResultSet rs = pst.executeQuery();
													
									
					int count = 0;
					while (rs.next()) {
						count = count + 1;
						
						 
					}
					
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "Username and password is correct");
						dispose();
						
						Feedback.frame.setVisible(true);								
					}
					else if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate username and password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Username/password is not correct !");
					}
					
					rs.close();
					pst.close();
				}catch(Exception e) {
					e.printStackTrace();
				}	
											
				
			}
		});
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		btnNewButton.setBounds(295, 203, 105, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		btnNewButton_1.setBounds(580, 203, 105, 40);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
	}

	
}
