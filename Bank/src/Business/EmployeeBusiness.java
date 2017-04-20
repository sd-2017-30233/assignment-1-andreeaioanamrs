package Business;

import java.sql.SQLException;
import java.util.List;

import Database.Account;
import Database.Client;
import Database.ClientMapper;
import Database.Employee;
import Database.EmployeeMapper;

public class EmployeeBusiness {
		
		public static List<Client> getClients() throws SQLException{
			 return  ClientMapper.allClients();	 
		}
		
		public static Client getClient(String cnp){
			try{
				if (cnp.length() == 13) return ClientMapper.findClient(cnp);
				else return null;}
				catch (SQLException e){
					e.printStackTrace();
					return null;
				}
		}
		
		public static String deleteClient(String cnp){
			try{
			if (ClientMapper.findClient(cnp) == null) return "Client does not exists!";
			else if (cnp.length() != 13) return "Invalid CNP!";
			else return String.valueOf(ClientMapper.deleteClient(cnp));}
			catch (SQLException e){
				e.printStackTrace();
				return "Client Could not be deleted!";
			}
			}
		
		public static String addClient(String cnp,String nume,String adr){
			try{
			if (ClientMapper.findClient(cnp)!= null) return "Client already exists!";
			else if (cnp.length() != 13) return "Invalid CNP!";
			else if (nume == null) return "Insert a name!";
			else if (adr == null) return "Insert an adress!";
			else return String.valueOf(ClientMapper.addClient(new Client(cnp,nume,adr)));}
			catch (SQLException e){
				e.printStackTrace();
				return "Client Could not be added!";
			}
		}
		
		public static List<Account> viewAcc(String cnp){
			try {
				return Account.getAccounts(cnp);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static String addAccount(String icn,String tip,String cnp,String cn){
			try{
			if (Account.findAccount(icn)!= null) return "Account already exists!";
			else if (icn.length() != 16) return "Invalid ICN!";
			else if (tip == null) return "Insert a name!";
			else if (cnp == null) return "Insert an cnp!";
			else return String.valueOf(Account.addAccount(new Account(icn,tip,"date",0,cnp,cn)));}
			catch (SQLException e){
				e.printStackTrace();
				return "Account Could not be added!";
			}
	}

		public static String transfer(String account1, String account2, Double val) {
			try {
				Account a = Account.findAccount(account1);
				Account a2 = Account.findAccount(account2);
				if (a == null) return "First account doesn't exist!";
				else if(a.getAmount()< val) return "Not enough money!";
				else if (a2 == null) return "Second account doesn't exist!";
				else
					return String.valueOf(Account.update(a2.getICN(),a2.getAmount()+val));
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		public static Account findAccount(String account1) {
			try {
				return Account.findAccount(account1);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		public static String updateAccount(String account1,Double money) {
			return Account.update(account1, money);
		}

		public static String deleteAccount(String icn) {
			try{
				if (Account.findAccount(icn) == null) return "Account does not exists!";
				else if (icn.length() != 16) return "Invalid CNP!";
				else return String.valueOf(Account.deleteAccount(icn));}
				catch (SQLException e){
					e.printStackTrace();
					return "Account Could not be deleted!";
				}
		}
}
