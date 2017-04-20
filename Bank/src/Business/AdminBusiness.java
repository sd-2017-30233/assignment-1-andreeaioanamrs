package Business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Database.Employee;
import Database.EmployeeMapper;
import Database.History;
import Database.HistoryMapper;

public class AdminBusiness {
	public static List<Employee> getEmployees() throws SQLException{
		 return  EmployeeMapper.allEmployees();	 
	}

	public static Employee getEmployeeUsername(String username){
		try {
			return EmployeeMapper.findEmployeeUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Employee getEmployeeCNP(String cnp){
		try {
			return EmployeeMapper.findEmployeeCNP(cnp);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String addEmployee(String user,String pass,String nume,String cnp,String adr){
			try{
			if (EmployeeMapper.findEmployeeUsername(user)!= null) return "Username already taken! Choose another one!";
			else if (pass == null) return "Choose a valid password!";
			else if (cnp.length() != 13) return "Invalid CNP!";
			else if (nume == null) return "Insert a name!";
			else if (adr == null) return "Insert an adress!";
			else return String.valueOf(EmployeeMapper.addEmployee(new Employee(user,pass,nume,cnp,adr)));}
			catch (SQLException e){
				e.printStackTrace();
				return "Employee Could not be added!";
			}
	}
	
	public static String deleteEmployee(String user){
		try{
		if (EmployeeMapper.findEmployeeUsername(user) == null) return "Invalid Username!";
		else if (user == null) return "Invalid Username!";
		else return String.valueOf(EmployeeMapper.deleteEmployee(user));}
		catch (SQLException e){
			e.printStackTrace();
			return "Employee Could not be deleted!";
		}
		}
	
	public static boolean generateReport(String username) throws IOException{
		try{
			List<History> l = HistoryMapper.allHistory(username);
			if(l!= null) {
			BufferedWriter buf1 = new BufferedWriter(new FileWriter("Raport.txt"));
		    buf1.write("\tRaport\n");
		    for(int i=0;i<l.size();i++)
		    	buf1.write("Employee: " +l.get(i).getEusername() +" la data de "+l.get(i).getDate()+" "+ l.get(i).getDescriere()+ "\n");
		    buf1.close();
		    return true;}
			else return false;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
