 package Database;
 
public class Admin {
	private String AUsername,Pass,Nume,CNP,Adress;
	public Admin(String AUsername,String Pass){
		this.setAUsername(AUsername);
		this.setPass(Pass);
	}
	public String getAUsername() {
		return this.AUsername;
	}
	public void setAUsername(String aUsername) {
		this.AUsername = aUsername;
	}
	public String getCNP() {
		return this.CNP;
	}
	public void setCNP(String cNP) {
		this.CNP = cNP;
	}
	public String getNume() {
		return this.Nume;
	}
	public void setNume(String nume) {
		this.Nume = nume;
	}
	public String getAdress() {
		return this.Adress;
	}
	public void setAdress(String adress) {
		this.Adress = adress;
	}
	public String getPass() {
		return this.Pass;
	}
	public void setPass(String pass) {
		this.Pass = pass;
	}
}
