import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterEditID extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public static EnterEditID dialog = new EnterEditID();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
//			dialog = new EnterEditID();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EnterEditID() {
		setBounds(100, 100, 303, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Enter row ID you want to EDIT:");
			lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
			lblNewLabel.setBounds(30, 21, 247, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			setTextField(new JTextField());
			getTextField().setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
			getTextField().setBounds(30, 47, 69, 20);
			contentPanel.add(getTextField());
			getTextField().setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						EditRows er = new EditRows();
						er.setVisible(true);
						dispose();
					
						
					}
				});
				okButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
