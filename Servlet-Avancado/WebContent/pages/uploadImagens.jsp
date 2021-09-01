<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Configuração JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload de Imagens</title>
</head>
<body>

	<h1>Upload de Imagens</h1>
	
		<input type="file" id="file" name="file" onchange="uploadFile();">
		<img id="target" width="200" height="200">	<br><br><br><br>
	
		<a href="FileUploadServlet?acao=carregar">Carregar Lista de Arquivos</a> <br> <br> 

		<table border="1">
			
			<tr>
				<td>Usuario</td>
				<td>Download</td>
				<td>Arquivo na base 64</td>
			</tr>
			
			<c:forEach items="${listaUserImagem}" var="user">
				
			<tr>		
				<td>${user.login}</td>
				<td><a target="_blank" href="FileUploadServlet?acao=download&loginuser=${user.login}">Download</a></td>
				<td>${user.imagem}</td>
			</tr>
			
			</c:forEach>
			
		</table>
	
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>

	<script type="text/javascript">
	
		function uploadFile(){
		
			var target = document.querySelector('img');
			var file = document.querySelector('input[type=file]').files[0];
			
			var reader = new FileReader();
			
			reader.onloadend = function (){
				
				target.src = reader.result;
				
				// ___________Upload Imagem___________________//
					$.ajax({
			
						method: "POST",
						url: "FileUploadServlet", 
						data: {fileUpload : reader.result }
			
				   }).done(function(response){ 
			            alert(response);
		           }).fail(function(xhr, status, erroThrown){ 
						alert(xhr.responseText);  
					});			
				// ___________Upload Imagem___________________//
				
			};
			
			if(file){
				reader.readAsDataURL(file);
				
			}else{
				target.src="";
			}
		}
			
	</script>

</body>
</html>