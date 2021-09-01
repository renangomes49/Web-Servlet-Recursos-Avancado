package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuario;
import model.Usuario;

@WebServlet("/pages/dadosDataTableServlet")
public class DadosDataTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAOUsuario daoUsuario = new DAOUsuario();
       
    public DadosDataTableServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			List<Usuario> listaUsuario = daoUsuario.listarUsuarioSemImagem();
			
			if(!listaUsuario.isEmpty()) {
				
				String data = "";
				
				// O ultimo objeto nao pode conter a virgula de separaçao, por isso esse contador
				int totalUsuario = listaUsuario.size(), index = 1;
				
				for (Usuario usuario : listaUsuario) {
					
					data = data + "["+
							      "\""+usuario.getLogin()+"\", "+
							      "\""+usuario.getSenha()+"\" "+
					               "]";
					if(index < totalUsuario) {
						data += ",";
					}
					index++;
				}
				
				String json = "{"+
									"\"draw\": 1, "+
									"\"recordsTotal\": "+listaUsuario.size()+","+
									"\"recordsFiltered\": "+listaUsuario.size()+","+
									"\"data\": ["+data +"]"+
							  "}";
				
			

				response.setStatus(200); // resposta completa - resposta OK
				response.getWriter().write(json); //json de resposta

			}
				
			
		}catch(Exception e) {
			
			e.printStackTrace();
			response.setStatus(500);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
