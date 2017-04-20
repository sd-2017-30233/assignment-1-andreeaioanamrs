package Business;

import java.sql.SQLException;
import java.util.Objects;

import Database.Admin;
import Database.AdminMapper;
import Database.Employee;
import Database.EmployeeMapper;

public class Logare {

	public static boolean logAdmin(String username, String pass){
		Admin a;
		if(username != null && pass != null)
			try {
				a = AdminMapper.findAdmin(username);
			//	System.out.print(ad.getNume());
				if (a != null ) 
					if(Objects.equals(a.getPass(),pass)) return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public static boolean logEmployee(String username,String pass){
		Employee e;
		if(username != null && pass != null)
		try {
			e = EmployeeMapper.findEmployeeUsername(username);
			//System.out.print(em.getNume());
		if (e != null ) 
			if(Objects.equals(e.getPass(),pass)) return true;
		} catch (SQLException er) {
			er.printStackTrace();
		}
		return false;
	}
}
