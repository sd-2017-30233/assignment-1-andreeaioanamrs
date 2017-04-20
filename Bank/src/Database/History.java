package Database;

import java.time.LocalDate;

public class History {
	String EUsername,Descriere;
	String Date;
	public History(String EU,String DDate,String Descri){
		this.EUsername = EU;
		this.Date = DDate;
		this.Descriere = Descri;
	}
	
	public void setEusername(String eu){
		this.EUsername = eu;
	}
	
	public String getEusername(){
		return this.EUsername;
	}
	
	public void setDescriere(String d){
		this.Descriere = d;
	}
	
	public String getDescriere(){
		return this.Descriere;
	}
	public void setDate(String d){
		this.Date = d;
	}
	
	public String getDate(){
		return this.Date;
	}
}
