package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBanco;
import model.Projeto;
import model.Series;

public class DaoProjeto {

	private Connection connection;
	
	public DaoProjeto() {
		connection = ConexaoBanco.getConnection();
	}
	
	
	public void atualizar(Series series) throws Exception {
		
		
		String sqlUpdate = "update series set inicio = ?, fim = ? where id = " +  series.getId();
		PreparedStatement statement = connection.prepareStatement(sqlUpdate);
		
		statement.setString(1, series.getDataInicial());
		statement.setString(2, series.getDataFinal());;
		
		statement.executeUpdate();
		
		connection.commit();
		
	}
	
	public List<Projeto> getProjetos() throws Exception{
		List<Projeto> projetos = new ArrayList<Projeto>();
	
		String sqlProjetos = "select * from projeto";
		PreparedStatement statement = connection.prepareStatement(sqlProjetos);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Projeto projeto = new Projeto();
			
			projeto.setId(resultSet.getLong("id"));
			projeto.setNome(resultSet.getString("nome"));
			
			// inicio - Pegando as series correspondentes a cada projeto
			String sqlSeries = "select * from series where projeto = " + resultSet.getLong("id");
			PreparedStatement statement2 = connection.prepareStatement(sqlSeries);
			ResultSet resultSetSeries = statement2.executeQuery();
			List<Series> series = new ArrayList<Series>();
			while(resultSetSeries.next()) {
				Series serie = new Series();
				serie.setId(resultSetSeries.getLong("id"));
				serie.setNome(resultSetSeries.getString("nome"));			
				serie.setDataInicial(resultSetSeries.getString("inicio"));
				serie.setDataFinal(resultSetSeries.getString("fim"));
				serie.setProjeto(resultSetSeries.getLong("projeto"));
				
				series.add(serie);
			}
			// fim
			
			projeto.setSeries(series);
			
			projetos.add(projeto);
		}
		
		return projetos;
	}
	
}





