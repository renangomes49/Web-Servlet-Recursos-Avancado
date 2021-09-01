<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Capturando Exce��es com Jquery</title>
</head>
<body>

	<h3>Capturando Exce��es com Jquery</h3>

	<input type="text" value="Informe o Valor" id="txtvalor">
	<input type="button" onclick="testarExce�oesJquery();" value="Testar Exce��o">

<script type="text/javascript" src="<%= request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">

	function testarExce�oesJquery(){
		
		valorInformado = $('#txtvalor').val();
		
		$.ajax({
			
			method: "POST",
			url: "capturarExcecao", // para qual servlet ?
			data: {valorParam : valorInformado}
			
		}).done(function(response){ // resposta ok - nenhum erro
			alert('Suceso ' + response);
			// fazer algo
		}).fail(function(xhr, status, erroThrown){ // resposta erro - algum problema ocorreu
			alert(xhr.responseText); // mostra resposta do servidor 
		});
		
	}

</script>

</body>
</html>