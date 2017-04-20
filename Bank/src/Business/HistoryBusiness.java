package Business;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Database.History;
import Database.HistoryMapper;

public class HistoryBusiness {
	public void makeNote(String user,String descriere){
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			HistoryMapper.addHistory(new History(user,String.valueOf(localDate),descriere));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
