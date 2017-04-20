package Database;

import java.sql.SQLException;
import java.util.Objects;

import Database.AdminMapper;
import Database.EmployeeMapper;

public class Employee {
	private String EUsername, Pass, Nume,CNP,Adress;
	public Employee(String u,String p){
		this.setEUsername(u);
		this.setPass(p);
	}
	public Employee(String u,String p,String n, String c, String a){
		this.setEUsername(u);
		this.setPass(p);
		this.setAdress(a);
		this.setCNP(c);
		this.setNume(n);
	}
	public String getNume() {
		return Nume;
	}
	public void setNume(String nume) {
		Nume = nume;
	}
	public String getPass() {
		return this.Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getCNP() {
		return this.CNP;
	}
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	public String getEUsername() {
		return this.EUsername;
	}
	public void setEUsername(String eUsername) {
		EUsername = eUsername;
	}
	public String getAdress() {
		return this.Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
}
