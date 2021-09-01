package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DAOUsuario;
import model.Usuario;

@WebServlet("/pages/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOUsuario daoUsuario = new DAOUsuario();
	
    public FileUploadServlet() {
   
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("uploadImagens.jsp");
				
				request.setAttribute("listaUserImagem", daoUsuario.listarUsuario());
				
				dispatcher.forward(request, response);				
			}else if(acao.equalsIgnoreCase("download")){
				
				String loginUser = request.getParameter("loginuser");
				Usuario usuario = daoUsuario.buscarArquivo(loginUser);
			
				if(usuario != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuario.getTipofile());
					
					// Pega somente a imagem pura
					String imagemPura = usuario.getImagem().split(",")[1];
				
					// converte base 64 em bytes
					byte [] imagemBytes = new Base64().decodeBase64(imagemPura); // do pacote util.codec.binary
				
					/* coloca os bytes em um objeto de entrada para processar*/
					InputStream inputStream = new ByteArrayInputStream(imagemBytes);
				
					/*Inicio - Escrever dados da resposta*/
						int read = 0;
						byte[] bytes = new byte[1024];
						OutputStream outputStream = response.getOutputStream();
					
						while((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes,0,read);
						}
						
						outputStream.flush();
						outputStream.close();
						
					/*Fim - Escrever dados da resposta*/
				}
				
			}

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String fileUpload = request.getParameter("fileUpload");	 // imagem em 64
			
			Usuario usuario = new Usuario();
			usuario.setImagem(fileUpload);
			usuario.setLogin("moreira");
			usuario.setSenha("senha");
			daoUsuario.inserirUsuario(usuario);
			
			response.getWriter().write("Upload realizado com sucesso");
		}catch(Exception e) {
			response.getWriter().write("Erro ao realizar Upload");
		}
	}

}
