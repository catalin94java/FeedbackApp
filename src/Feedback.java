import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.lang.Comparable;
import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Comparator;

import javax.swing.*;



public class Feedback extends JFrame {

	
	
	
	private JPanel contentPane;
	public JTable table_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static DefaultTableModel dtm;
	public static Feedback frame = new Feedback();
	public static String a = "0";
	public  Integer count = 0;	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Feedback frame = new Feedback();
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
	
	public void refreshTable() {
		table_1.setModel(Feedback.frame.dtm);
		Feedback.frame.dtm.setRowCount(0);
		try {
			String queryFeedback = "SELECT * FROM FEEDBACK ";
			PreparedStatement pstFeedback = connection.prepareStatement(queryFeedback);
			ResultSet rsFeedback = pstFeedback.executeQuery();
			String b = "";
			while(rsFeedback.next()) {
				Feedback.frame.dtm.addRow(new Object[] {
						b = rsFeedback.getString(1),
						rsFeedback.getString(2),
						rsFeedback.getString(3),
						rsFeedback.getString(4),
						rsFeedback.getString(5),
									
				});					
				
			}
			
			count = Integer.parseInt(b);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	public Feedback() {
		
		connection = mySqlConnection.dbConnector();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 11, 841, 516);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(191, 205, 219), null, null, null), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 48, 817, 443);
		panel.add(panel_1);
					
				
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				IntroducereFeedback.dialog.setVisible(true);
								
			}
		});
		button.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		button.setBounds(684, 385, 89, 23);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(33, 39, 749, 337);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 729, 292);
		panel_2.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 13));
		scrollPane.setViewportView(table_1);
		table_1.setTableHeader(table_1.getTableHeader());
		
		 dtm = new DefaultTableModel(new Object[][] {
			
			
		}, new String[] {"ID", "FirstName", "LastName", "Email", "Remarks"
		});
		
		
		table_1.setModel(dtm);
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(22);
		table_1.getColumnModel().getColumn(0).setMinWidth(10);
		table_1.getColumnModel().getColumn(0).setMaxWidth(334);
		
		table_1.setFillsViewportHeight(true);
		
			
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
		sorter.setModel(dtm);
		sortComparator sc = new sortComparator();
		NumberComparator nc = new NumberComparator();

		sorter.setComparator(0, nc);
		sorter.setComparator(1, sc);
		sorter.setComparator(2, sc);
		sorter.setComparator(3, sc);
		table_1.setRowSorter(sorter);
			
		table_1.setRowHeight(25);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				enterDeleteID.dialog.setVisible(true);
							
			}
		});
		btnDelete.setToolTipText("");
		btnDelete.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		btnDelete.setBounds(43, 385, 100, 23);
		panel_1.add(btnDelete);
		btnDelete.setVisible(false);
		
				
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				EnterEditID.dialog.setVisible(true);
				
			}
		});
		btnEdit.setToolTipText("");
		btnEdit.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		btnEdit.setBounds(594, 385, 89, 23);
		panel_1.add(btnEdit);
		btnEdit.setVisible(false);
			
		
		try {
			
			String queryFeedback = "SELECT * FROM FEEDBACK ";
			PreparedStatement pstFeedback = connection.prepareStatement(queryFeedback);
			ResultSet rsFeedback = pstFeedback.executeQuery();
			
			while(rsFeedback.next()) {
				
				dtm.addRow(new Object[] {
						a = rsFeedback.getString(1),
						rsFeedback.getString(2),
						rsFeedback.getString(3),
						rsFeedback.getString(4),
						rsFeedback.getString(5),
										
				});					
				count++;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 841, 21);
		panel.add(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		menu.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 12));
		
		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EnterOpenID.dialog.setVisible(true);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menu.add(menuItem);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JMenuItem menuItem_2 = new JMenuItem("Exit");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("Help");
		menuBar.add(menu_1);
		menu_1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 12));
		
		String admin = "admin";
		String adminPassword = "12345";

		if(LoginForm.frame.textUsername.getText().toLowerCase().equals(admin) && LoginForm.frame.passwordField.getText().toLowerCase().equals(adminPassword)) {
			btnDelete.setVisible(true);
			btnEdit.setVisible(true);
		}
		

	}
}
