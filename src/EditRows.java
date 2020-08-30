import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditRows extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static EditRows frame = new EditRows();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EditRows frame = new EditRows();
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
	Connection connection = null;
	
	public EditRows() {
		connection = mySqlConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 584, 553);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(261, 53, 185, 32);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(261, 113, 185, 32);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(261, 167, 185, 32);
		panel.add(textField_2);
							
		
		JLabel label = new JLabel("FirstName:");
		label.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		label.setBounds(136, 57, 115, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("LastName:");
		label_1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		label_1.setBounds(136, 117, 115, 20);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		label_2.setBounds(136, 171, 115, 20);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Remarks:");
		label_3.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		label_3.setBounds(133, 220, 115, 20);
		panel.add(label_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(133, 251, 313, 202);
		panel.add(textArea);
					
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(IntroducereFeedback.isValidEmailAddress(textField_2.getText()) == false) {
					JOptionPane.showMessageDialog(EditRows.this, "Enter a valid email adress.", "Error", JOptionPane.WARNING_MESSAGE);
				} 
				
				else {
					
					if(textField.getText().length() == 0 
							||textField_1.getText().length() == 0 
								|| textField_2.getText().length() == 0  
									|| textArea.getText().length() == 0) 
						{
							JOptionPane.showMessageDialog(EditRows.this, "Complete all fields.", "Error", JOptionPane.WARNING_MESSAGE);
						}
				
					else {
				try {
					
					String fnC = textField.getText().substring(0, 1).toUpperCase() + textField.getText().substring(1);
					String snC = textField_1.getText().substring(0, 1).toUpperCase() + textField_1.getText().substring(1);
					String emC = textField_2.getText().toLowerCase();
					String tarC = textArea.getText().substring(0, 1).toUpperCase() + textArea.getText().substring(1);
					
					String query = "UPDATE FEEDBACK SET FIRSTNAME = '"+fnC+"',"
																+ "LASTNAME = '"+snC+"',"
																+ "EMAIL = '"+emC+"',"
																+ "REMARKS = '"+ tarC+"' "
																+ "WHERE ID = '"+EnterEditID.dialog.getTextField().getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					pst.close();
					
						
					}catch(Exception ex) {
					ex.printStackTrace();
				}
				dispose();

				Feedback.frame.refreshTable();
					}
				}
			}
		});
		
		btnUpdate.setToolTipText("");
		btnUpdate.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		btnUpdate.setBounds(357, 464, 89, 23);
		panel.add(btnUpdate);
		
		try {
			String query = "SELECT FirstName,LastName,Email,Remarks FROM FEEDBACK WHERE ID = '"+EnterEditID.dialog.getTextField().getText()+"' ";
			if (EnterEditID.dialog.getTextField().getText().length() == 0) {
				query = "SELECT FirstName,LastName,Email,Remarks FROM FEEDBACK WHERE ID = '"+EnterOpenID.dialog.getTextField().getText()+"'";
			btnUpdate.setVisible(false);
			}
 			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
						
			while(rs.next()) {
										
							textField.setText(rs.getString(1));
							textField_1.setText(rs.getString(2));
							textField_2.setText(rs.getString(3));
							textArea.setText(rs.getString(4));
																
		}
						
						
			}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
