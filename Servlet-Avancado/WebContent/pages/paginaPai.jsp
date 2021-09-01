<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<h1>Página Pai LoadJquery</h1>
	
	<input type="button" value="Carregar Página" onclick="carregar();">
	
	<div id="mostrarPaginaFilha"></div>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript">
	
		function carregar(){
			
			$('#mostrarPaginaFilha').load('paginaFilha.jsp'); // carregar paginaFilha em Jquery
			
		}
	
	</script>

</body>
</html>