<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataTable JQuery</title>

<link rel="stylesheet" href="http://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="http://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>

</head>
<body>

	<table id="minhatabela" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Login</th>
                <th>Senha</th>
            </tr>
        </thead>
	</table>
	
</body>

	<script type="text/javascript">
		
	$(document).ready(function() { // faz o processamento assim que a tela carrega // metodo GET
	    $('#minhatabela').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": "dadosDataTableServlet" // URL de retorno dos dados do servidor, ou seja, Resposta
	    } );
	} );
	
	</script>


</html>