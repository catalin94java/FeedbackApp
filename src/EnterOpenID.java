import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterOpenID extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public static EnterOpenID dialog = new EnterOpenID();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
//			EnterOpenID dialog = new EnterOpenID();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EnterOpenID() {
		setBounds(100, 100, 304, 189);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 11, 287, 86);
		contentPanel.add(panel);
		
		JLabel lblEnterRowId = new JLabel("Enter row ID you want to OPEN:");
		lblEnterRowId.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		lblEnterRowId.setBounds(30, 21, 247, 14);
		panel.add(lblEnterRowId);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(30, 47, 69, 20);
		panel.add(textField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 111, 287, 35);
		contentPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRows er = new EditRows();
				er.setVisible(true);
				
				dispose();
			}
		});
		button.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		button.setActionCommand("OK");
		panel_1.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
		button_1.setActionCommand("Cancel");
		panel_1.add(button_1);
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField ;
	}
}
