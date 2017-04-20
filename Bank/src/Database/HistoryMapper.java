package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class HistoryMapper {
	
	public static History mapHistory(ResultSet rs){
		try {
			if(rs.next()){
				History em = new History(rs.getString(1),rs.getString(2),rs.getString(3));
				return em;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static List<History> allHistory(String EUsername) throws SQLException{
		try{
			List<History> h = new ArrayList<History>();
			History aux;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from History where EUsername=?");
			stmt.setString(1, EUsername);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				aux = new History(rs.getString(1),rs.getString(2),rs.getString(3));
				h.add(aux);
			}
			return h;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static boolean addHistory(History h) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into History values(?,?,?)");
			stmt.setString(3, h.getDescriere());
			stmt.setString(1, h.getEusername());
			stmt.setString(2, h.getDate());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
}
