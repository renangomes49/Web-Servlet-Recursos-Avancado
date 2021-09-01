package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalcularDataFinal;

@WebServlet("/pages/CalcularDataFinalServlet")
public class CalcularDataFinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DaoCalcularDataFinal daoCalcularDataFinal = new DaoCalcularDataFinal();
	
    public CalcularDataFinalServlet() {
  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 08:00-12:00 e 13:30-17:30 expediente trabalho */
		/* 1 dia é igual a horas */
		
		try {
			
			int horaDia = 8;
			Date dataCalculada = null;
			Double totalDias = 0.0;
			
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horaDia) {
				totalDias = 1.0;
				dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			}else {
				totalDias = (double) (tempo / horaDia);
				
				Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();
				
				calendar.setTime(dateInformada);
				calendar.add(Calendar.DATE, totalDias.intValue());
				
				dataCalculada = calendar.getTime();
			
			}
			daoCalcularDataFinal.gravarDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");	
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias",totalDias);
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}





