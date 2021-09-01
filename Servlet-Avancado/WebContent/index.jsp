<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recursos Avançados em Java</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<style type="text/css">
	
	h3{
		position: relative;
		left: 35%;
		right: 35%;
	}
</style>

</head>
<body>

	<h3>Recursos Avançados em Java</h3> 
	

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">Menu</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
		      <ul class="navbar-nav">
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">Funcionalidades</a>
		          <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
		        	  <li><a class="dropdown-item" href="pages/capturarExcecoes.jsp">Capturar Exceções</a></li>
		   			  <li><a class="dropdown-item" href="pages/acessoAoSistema.jsp">Acessar Sistema</a></li>
		    		  <li><a class="dropdown-item" href="pages/paginaPai.jsp">Load Jquey</a></li>
		    		  <li><a class="dropdown-item" href="pages/progressBar.jsp">Barra de Progresso</a></li>
		    		  <li><a class="dropdown-item" href="pages/uploadImagens.jsp">Upload e Dowload de Arquivos</a></li>
		    		  <li><a class="dropdown-item" href="pages/datatable.jsp">DataTable JQuery</a></li>
		 		      <li><a class="dropdown-item" href="pages/relatorio.jsp">Relatório</a></li>
		 		      <li><a class="dropdown-item" href="pages/datas.jsp">Calculando Datas</a></li>
		 		 	  <li><a class="dropdown-item" href="pages/calendario.jsp">Calendário</a></li> 
		 		 	  <li><a class="dropdown-item" href="pages/ganttview.jsp">Gantt View</a></li> 		 		 	  
		    		  <li><a class="dropdown-item" href="pages/LoginServlet?acao=deslogar">Deslogar</a></li>
		          </ul>
		        </li>
		      </ul>
		    </div>
		  </div>
	</nav>



<script type="text/javascript" src="<%= request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>

</body>
</html>