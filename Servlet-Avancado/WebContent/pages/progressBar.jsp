<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Barra de Progresso</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">

	#myProgress{
		
		width: 100%;
		
		/* cor fundo barra de progresso - cinza  */
		background-color: #ddd;
	
	}
	
	#myBar{
		
		width: 0%;
		height: 30px;
		
		/* cor barra de progresso - cinza  */
		background: #4CAF50;
	}
	
	.ui-progressbar{
	
		position: relative;
	
	}
	
	.progress-label{
	
		position: relative;
		left: 50%;
		top: 4px;
		font-weight: bold;
		text-shadow: 1px 1px 0 #fff;
	}

</style>

</head>
<body>

	<h1>Barra de Progresso com JavaScript</h1>

	<div id="myProgress">
		<div id="myBar">
		
		</div>
	</div>
	<br>
	<button onclick="exibirBarra()">Iniciar Barra de Progresso</button>
	
	
	<br>
	<h1>Barra de Progresso com Jquery</h1>

	<div id="progress-bar">
		<div class="progress-label">
			Carregando...
		</div>
	</div>

	
	<script type="text/javascript">
	
	
		// barra de progresso com JavaScript
		function exibirBarra() {
			
			var elem = document.getElementById("myBar");
			var width = 0;
			var id = setInterval(() => {
				
				if(width >= 100){
					clearInterval(id);
				}else{
					width++;
					elem.style.width = width + "%";
				}
				
			}, 10);
			
		}
		
		// barra de progresso com JQuery
		$(function(){
			var progressbar = $("#progress-bar"), progresslabel = $(".progress-label");
			
			progressbar.progressbar({ // cria a barra na div
				value : false,
				change : function() {
					progresslabel.text (progressbar.progressbar('value') + "%");
				},
				complete : function (){
					progresslabel.text('Completo!');	
				}
				
			});
			
			function progress() {
				var val = progressbar.progressbar("value") || 0;
				
				progressbar.progressbar("value", val + 2);
				
				if(val < 99){
					setTimeout(progress,80); 
				}
				
			}
			
			setTimeout(progress,2000); // chamado ao abrir a tela
		
	});
	</script>	


</body>
</html>









