package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.ConexaoBanco;
import model.Usuario;

@WebFilter(urlPatterns = {"/pages/*"}) // urls para serem interceptadas
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;
 
    public FilterAutenticacao() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		// retorna null caso nao esteja logado
		Usuario usuario = (Usuario) session.getAttribute("usuario");
				
		
		String urlParaAutenticar = req.getServletPath();
		
		
		if(usuario == null && !urlParaAutenticar.equalsIgnoreCase("/pages/LoginServlet")) { // usuario nao logado
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?url=" + urlParaAutenticar);
			dispatcher.forward(request, response);
			
			return;
		}
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = ConexaoBanco.getConnection();
	
	}

}




