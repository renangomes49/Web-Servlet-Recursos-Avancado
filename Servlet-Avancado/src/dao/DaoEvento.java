package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBanco;
import model.Evento;
import model.Usuario;

public class DaoEvento {

	private Connection connection;	
	
	public DaoEvento() {
    
		connection = ConexaoBanco.getConnection();		
	}
	
	public List<Evento> listarEventos() throws Exception{
		
		List<Evento> listaEvento = new ArrayList<Evento>();
		
		String sql = "select * from eventos";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Evento evento = new Evento();
			evento.setDataEvento(resultSet.getString("dataEvento"));
			evento.setDescricao(resultSet.getString("descricao"));
			listaEvento.add(evento);
		}
		
		return listaEvento;
	}
}
