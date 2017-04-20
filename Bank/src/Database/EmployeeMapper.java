package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class EmployeeMapper {
	
	public static Employee mapEmployee(ResultSet rs){
		try {
			if(rs.next()){
				Employee em = new Employee(rs.getString(1),rs.getString(2));
				String nume = rs.getString(3);
				String cnp = rs.getString(4);
				String adress = rs.getString(5);
				em.setAdress(adress);
				em.setNume(nume);
				em.setCNP(cnp);
				return em;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static Employee findEmployeeCNP(String CNP) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Employee where CNP=?");
			stmt.setString(1, CNP);
			ResultSet rs = stmt.executeQuery();
			return mapEmployee(rs);
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	

	public static Employee findEmployeeUsername(String username) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Select * from Employee where EUsername=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return mapEmployee(rs);
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static List<Employee> allEmployees() throws SQLException{
		try{
			List<Employee> e = new ArrayList<Employee>();
			Employee aux;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Employee");
			while(rs.next()){
				aux = new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				e.add(aux);
			}
			return e;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
			}
	}
	
	public static boolean addEmployee(Employee em) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Insert into Employee values(?,?,?,?,?)");
			stmt.setString(4, em.getCNP());
			stmt.setString(3, em.getNume());
			stmt.setString(5, em.getAdress());
			stmt.setString(1, em.getEUsername());
			stmt.setString(2, em.getPass());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteEmployee(String username) throws SQLException{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","");
			PreparedStatement stmt = conn.prepareStatement("Delete from Employee where EUsername=?");
			stmt.setString(1, username);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
