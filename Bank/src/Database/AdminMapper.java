package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper {
	public static Admin mapAdmin(ResultSet rs){
		try {
			if(rs.next()){
				Admin a = new Admin(rs.getNString(1),rs.getString(2));
				String nume = rs.getString("Nume");
				String cnp = rs.getString("CNP");
				String adress = rs.getString("Adress");
				a.setAdress(adress);
				a.setNume(nume);
				a.setCNP(cnp);
				return a;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Admin findAdmin(String username) throws SQLException{
		PreparedStatement stmt;
		Connection conn;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			stmt = conn.prepareStatement("Select * from Admin where AUsername=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return mapAdmin(rs);
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static void addAdmin(Admin a) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into Admin values(?,?,?,?,?)");
			stmt.setString(1, a.getCNP());
			stmt.setString(2, a.getNume());
			stmt.setString(3, a.getAdress());
			stmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void deleteAdmin(String username) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Delete from Admin where AUsername=?");
			stmt.setString(1, username);
			stmt.executeUpdate();	
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
