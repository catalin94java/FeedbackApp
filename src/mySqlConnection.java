
import java.sql.*;
import javax.swing.*;

public class mySqlConnection {
	private static String envValue= System.getenv("envmysql");
	Connection conn  = null;
	
	public static Connection dbConnector() {
		
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root", envValue);
//			JOptionPane.showMessageDialog(null, "Connection Successfull");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
