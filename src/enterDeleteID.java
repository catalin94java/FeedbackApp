import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
public class enterDeleteID extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public static enterDeleteID dialog = new enterDeleteID();
	
	
	/**
	 * Launch the application.
	 */
	//Returns true if s is a number
	static boolean isNumber(String s) 
    { 
        for (int i = 0; i < s.length(); i++) 
        if (Character.isDigit(s.charAt(i))  == false) 
            return false;   
        
        return true; 
    } 
	
	
	public static void main(String[] args) {
		try {
//			enterDeleteID dialog = new enterDeleteID();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	Connection connection = null;
	public enterDeleteID() {
		connection  = mySqlConnection.dbConnector();
		
		setBounds(100, 100, 286, 149);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 46, 69, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		
		
		
		JLabel lblEnterIdYou = new JLabel("Enter row ID \r\nyou want to DELETE:");
		lblEnterIdYou.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		lblEnterIdYou.setBounds(10, 21, 262, 14);
		contentPanel.add(lblEnterIdYou);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						Scanner scanner = new Scanner(textField.getText());
						
						if (textField.getText().length() == 0  || scanner.hasNextInt() == false) {
							JOptionPane.showMessageDialog(enterDeleteID.this, "Enter valid row ID", "Error", JOptionPane.WARNING_MESSAGE);
						}
						
						
						Integer i = Integer.parseInt(textField.getText());
						if(i > Feedback.frame.count|| i < 1) {
							JOptionPane.showMessageDialog(enterDeleteID.this, "Enter valid row ID", "Error", JOptionPane.WARNING_MESSAGE);
						
						}
						
							
					
						else {
													
						try {
						String query = "DELETE FROM FEEDBACK WHERE ID = '" + textField.getText()+"' ";
						String query1 = "SET @NUM := 0;";
						String query2 =  "UPDATE feedback SET id = @NUM := @num + 1 ;";
						String query3 = "ALTER TABLE feedback AUTO_INCREMENT =1;";
						
						PreparedStatement pst = connection.prepareStatement(query);
						PreparedStatement pst1 = connection.prepareStatement(query1);
						PreparedStatement pst2 = connection.prepareStatement(query2);
						PreparedStatement pst3 = connection.prepareStatement(query3);
						
						pst.execute();
						pst1.execute();
						pst2.execute();
						pst3.execute();
						
						pst.close();
						pst1.close();
						pst2.close();
						pst3.close();
										
						Feedback.frame.dtm.removeRow(Integer.parseInt(textField.getText()) - 1);
						Feedback.frame.refreshTable();
						}catch(Exception ex){
							ex.printStackTrace();
						}
							
						
						}
						
						}catch(NumberFormatException exc) 
						{
							System.out.println("Invalid input");
						}
					}
					
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
