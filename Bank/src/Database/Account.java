package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Statement;

public class Account {
	private String ICN,Tip,DC,CNP,CN;
	private double amount;
	public String getCNP() {
		return CNP;
	}
	public Account(String ICN,String Tip,String DC,double amount,String cnp,String CN){
		this.ICN = ICN;
		this.Tip = Tip;
		this.DC = DC;
		this.amount = amount;
		this.CNP = cnp;
		this.CN = CN;
	}
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	public String getICN() {
		return ICN;
	}
	public void setICN(String iCN) {
		ICN = iCN;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTip() {
		return Tip;
	}
	public void setTip(String tip) {
		Tip = tip;
	}
	public String getDC() {
		return DC;
	}
	public void setDC(String dC) {
		DC = dC;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	
	public static List<Account> getAccounts(String cnp) throws SQLException{
		try{
			List<Account> c = new ArrayList<Account>();
			Account aux;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Clienti where CNP=?");
			stmt.setString(1, cnp);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				aux = new Account(rs.getString(1),rs.getString(2),rs.getString(3),Double.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6));
				c.add(aux);
			}
			return c;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static Account findAccount(String ICN) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Account where ICN=?");
			stmt.setString(1, ICN);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) return new Account(rs.getString(1),rs.getString(2),rs.getString(3),Double.valueOf(rs.getString(4)),rs.getString(5),rs.getString(6));
			else return null;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static boolean addAccount(Account ac) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into Account values(?,?,?,?,?,?)");
			stmt.setString(1, ac.getICN());
			stmt.setString(2, ac.getTip());
			stmt.setString(3, ac.getDC());
			stmt.setDouble(4, ac.getAmount());
			stmt.setString(5, ac.getCNP());
			stmt.setString(6, ac.getCN());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteAccount(String ICN) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Delete from Account where ICN=?");
			stmt.setString(1, ICN);
			stmt.executeUpdate();	
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static String update(String icn2, double d) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Update Account Set Amount=? where ICN=?");
			stmt.setString(2, icn2);
			stmt.setDouble(1, d);
			stmt.executeUpdate();	
			return "Account successfully updated!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Account could not be updated";
		}

		
	}
	
}
