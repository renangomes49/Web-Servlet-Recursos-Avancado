package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConexaoBanco;

public class DaoCalcularDataFinal {

	private Connection connection;
	
	public DaoCalcularDataFinal() {
		connection = ConexaoBanco.getConnection();
	}
	
	public void gravarDataFinal(String date) throws Exception {
		
		String sql = "insert into finalprojetos (datafinal) values (?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, date);
		statement.execute();
		connection.commit();
		
	}
	
}






