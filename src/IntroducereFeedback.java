import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.util.StringUtils;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class IntroducereFeedback extends JDialog {

	private final JPanel contentPanel = new JPanel();
	 JTextField textFirstName;
	 JTextField textLastName;
	 JTextField textEmail;
	 JTextArea txtrWriteARemark; 
	 public static IntroducereFeedback  dialog  = new IntroducereFeedback();
	 
	 JButton btnAdd;
	


	/**
	 * Launch the application.
	 */
	
	 public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	 
	public static void main(String[] args) {
		try {
//			IntroducereFeedback dialog = new IntroducereFeedback();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	/**
	 * Create the dialog.
	 */
	Connection connection  = null;
	public IntroducereFeedback() {
		
		
		connection = mySqlConnection.dbConnector();
		
		
		
		setTitle("AdFeedback");
		setBounds(100, 100, 600, 551);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFirstName = new JTextField();
		textFirstName.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textFirstName.setBounds(261, 53, 185, 32);
		contentPanel.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textLastName.setColumns(10);
		textLastName.setBounds(261, 113, 185, 32);
		contentPanel.add(textLastName);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(261, 167, 185, 32);
		contentPanel.add(textEmail);
		
		JLabel lblFirstName = new JLabel("FirstName:");
		lblFirstName.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblFirstName.setBounds(136, 57, 115, 20);
		contentPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("LastName:");
		lblLastName.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblLastName.setBounds(136, 117, 115, 20);
		contentPanel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblEmail.setBounds(136, 171, 115, 20);
		contentPanel.add(lblEmail);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblRemarks.setBounds(133, 220, 115, 20);
		contentPanel.add(lblRemarks);
		
		JTextArea txtrWriteARemark = new JTextArea();
		txtrWriteARemark.setLineWrap(true);
		txtrWriteARemark.setToolTipText("");
		txtrWriteARemark.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		txtrWriteARemark.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtrWriteARemark.setBounds(133, 251, 313, 202);
		contentPanel.add(txtrWriteARemark);
							
	 	JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isValidEmailAddress(textEmail.getText()) == false) {
					JOptionPane.showMessageDialog(IntroducereFeedback.this, "Enter a valid email adress.", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					
				
			if(textFirstName.getText().length() == 0 
				|| textLastName.getText().length() == 0 
					|| textEmail.getText().length() == 0  
						|| txtrWriteARemark.getText().length() == 0) 
			{
				JOptionPane.showMessageDialog(IntroducereFeedback.this, "Complete all fields.", "Error", JOptionPane.WARNING_MESSAGE);
			}
							
			else 
			{
				
			
				try {
										
					String fnC = textFirstName.getText().substring(0, 1).toUpperCase() + textFirstName.getText().substring(1);
					String snC = textLastName.getText().substring(0, 1).toUpperCase() + textLastName.getText().substring(1);
					String emC = textEmail.getText().toLowerCase();
					String tarC = txtrWriteARemark.getText().substring(0, 1).toUpperCase() + txtrWriteARemark.getText().substring(1);
					
					String queryAddFeedback = "INSERT INTO FEEDBACK (FIRSTNAME,LASTNAME,EMAIL,REMARKS) VALUES (?,?,?,?)";
					String query1 = "SET @NUM := 0;";
					String query2 =  "UPDATE feedback SET id = @NUM := @num + 1 ;";
					String query3 = "ALTER TABLE feedback AUTO_INCREMENT =1;";
					
					PreparedStatement pst = connection.prepareStatement(queryAddFeedback);	
					pst.setString(1, fnC);
					pst.setString(2, snC);
					pst.setString(3, emC);
					pst.setString(4, tarC);	
					pst.execute();
					
					
					PreparedStatement pst1 = connection.prepareStatement(query1);
					PreparedStatement pst2 = connection.prepareStatement(query2);
					PreparedStatement pst3 = connection.prepareStatement(query3);
				
					
					pst1.execute();
					pst2.execute();
					pst3.execute();
												
					Feedback.frame.dtm.addRow(new Object[] {
								
							Integer.parseInt(Feedback.frame.a ) + 1,
							fnC,
							snC,
							emC,
							tarC
													
					});
					
					
					pst.close();
					pst1.close();
					pst2.close();
					pst3.close();
				
					Feedback.frame.refreshTable();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
				
				textFirstName.setText(null);
				textLastName.setText(null);
				textEmail.setText(null);
				txtrWriteARemark.setText(null);
			}
			
		
			}
		}
		});
		btnAdd.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		btnAdd.setBounds(357, 464, 89, 23);
		contentPanel.add(btnAdd);
		
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFirstName.setText(null);
				textLastName.setText(null);
				textEmail.setText(null);
				txtrWriteARemark.setText(null);
			}
		});
		btnReset.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		btnReset.setBounds(136, 464, 89, 23);
		contentPanel.add(btnReset);
	}
}
