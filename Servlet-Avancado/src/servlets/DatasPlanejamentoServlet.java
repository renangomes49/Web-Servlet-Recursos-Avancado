package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoProjeto;
import model.Projeto;
import model.Series;

@WebServlet("/pages/DatasPlanejamentoServlet")
public class DatasPlanejamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoProjeto daoProjeto = new DaoProjeto();

    public DatasPlanejamentoServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {

			List<Projeto> projetos = daoProjeto.getProjetos();
			
			if(!projetos.isEmpty()) {
		
				String json =  new ObjectMapper().writeValueAsString(projetos); 
				
				response.getWriter().write(json);
				
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// parametros vindo da ganttview.jsp
			Series seriesUpdate = new Series(); 
			seriesUpdate.setId(Long.parseLong(request.getParameter("serie")));
			seriesUpdate.setProjeto(Long.parseLong(request.getParameter("projeto")));
			seriesUpdate.setDataInicial(request.getParameter("start"));
			seriesUpdate.setDataFinal(request.getParameter("end"));
			
			daoProjeto.atualizar(seriesUpdate);
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
			
	}

}
