package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Card {
	String CN,Pass;
	public Card(String c, String p){
		this.CN = c;
		this.Pass = p;
	}
	
	public String getCN() {
		return this.CN;
	}
	
	public String getPass() {
		return this.Pass;
	}
	
	public void setPass(String p){
		this.Pass = p;
	}
		
	public static Card findCard(String CN) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Card where CN=?");
			stmt.setString(1, CN);
			ResultSet rs = stmt.executeQuery();
			Card c = new Card(CN,rs.getString(2));
			return c;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static void addCard(Card c) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into Card values(?,?)");
			stmt.setString(1, c.getCN());
			stmt.setString(2, c.getPass());
			stmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void updateCard(Card c) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Update Card set Pass=? where CN=?)");
			stmt.setString(1, c.getCN());
			stmt.setString(2, c.getPass());
			stmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void deleteCard(String CN) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Delete from Card where CN=?");
			stmt.setString(1, CN);
			stmt.executeUpdate();	
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}