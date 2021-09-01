package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBanco;
import model.Usuario;

public class DAOUsuario {

	private Connection connection;
	
	public DAOUsuario(){
		connection = ConexaoBanco.getConnection();
	}
	
	public void inserirUsuario(Usuario usuario) throws Exception{
		
		String tipoDados = usuario.getImagem().split(",")[0].split(";")[0].split("/")[1];
		
		String sql = "INSERT INTO usuario(login, senha, imagem,tipofile) VALUES (?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getImagem());
		statement.setString(4, tipoDados);
		
		statement.execute();
		
		connection.commit();
	}
	
	public List<Usuario> listarUsuario() throws SQLException{
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		String sql = "select * from usuario";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			
			listaUsuarios.add(usuario);
		}
		
		return listaUsuarios;
	}


	public List<Usuario> listarUsuarioSemImagem() throws SQLException{
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		String sql = "select login, senha from usuario";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			
			listaUsuarios.add(usuario);
		}
		
		return listaUsuarios;
	}

	
	public Usuario buscarArquivo(String loginUser) throws SQLException {
		
		String sql = "select * from usuario where login = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,loginUser);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			
			Usuario usuario = new Usuario();
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			usuario.setTipofile(resultSet.getString("tipofile"));
			
			return usuario;
		}
		
		return null;
	}
	
}
