package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class Client {
	String CNP,Nume,Adress;
	public Client(String CNP,String Nume,String Adress){
		this.CNP = CNP;
		this.Nume = Nume;
		this.Adress = Adress;
	}
	public String getCNP(){
		return this.CNP;
	}
	public void setCNP(String x){
		this.CNP = x;
	}
	public String getNume(){
		return this.Nume;
	}
	public void setNume(String x){
		this.Nume = x;
	}
	public String getAdress(){
		return this.Adress;
	}
	public void setAdress(String x){
		this.Adress = x;
	}
}
