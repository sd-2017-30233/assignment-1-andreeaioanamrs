package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class ClientMapper {
	public static Client mapClient(ResultSet rs){
		try {
			if(rs.next()){
				Client c = new Client(rs.getString(1),rs.getString(2),rs.getString(3));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static Client findClient(String CNP) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Clienti where CNP=?");
			stmt.setString(1, CNP);
			ResultSet rs = stmt.executeQuery();
			return mapClient(rs);
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static List<Client> allClients() throws SQLException{
		try{
			List<Client> c = new ArrayList<Client>();
			Client aux;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Clienti");
			while(rs.next()){
				aux = new Client(rs.getString(1),rs.getString(2),rs.getString(3));
				c.add(aux);
			}
			return c;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static boolean addClient(Client c) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into Clienti values(?,?,?)");
			stmt.setString(3, c.getAdress());
			stmt.setString(1, c.getCNP());
			stmt.setString(2, c.getNume());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteClient(String CNP) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Delete from Clienti where CNP=?");
			stmt.setString(1, CNP);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
