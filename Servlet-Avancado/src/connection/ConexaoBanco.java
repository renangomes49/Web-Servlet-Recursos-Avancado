package connection;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConexaoBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/servlets-avancado?autoReconnect=true";
	private static String usuario = "postgres";
	private static String senha = "980704";	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static { /*qunado chamar a classe diretamente, também haverá uma conexão*/
		conectar();
	}
	
	public ConexaoBanco() {
		conectar();
	}
	
	public static void conectar() {
		
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver"); 
				connection = DriverManager.getConnection(url, usuario, senha);
				connection.setAutoCommit(false);				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


