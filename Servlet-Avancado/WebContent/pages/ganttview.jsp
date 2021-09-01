<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gannt View</title>

	<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css" />

	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>


</head>
<body>
	
	<h1>Gentt View</h1>
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>


	<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="../scriptGanttView/date.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>

	<!--  <script src="../assets/js/jquery-3.6.0.min.js" ></script>  -->

	<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {	
		$.get("DatasPlanejamentoServlet",function(response){
	
				// processa dados gant view INICIO	
				var ganttDataResposta = JSON.parse(response);
				
				var ganttData = "";
				ganttData += "["; 
				
				$.each(ganttDataResposta, function(index, projeto) {// for dos projetos
					
					ganttData += "{ \"id\": \""+projeto.id+"\", \"name\": \""+projeto.nome+"\", \"series\": [";
					
					$.each(projeto.series, function(idx, serie) {// for das series
						
							var cores = "#3366FF,#00CC00".split(',');
						
							var cor;
							if (idx === 0) {
								cor = "#CC33CC";
							}else {
							   cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
							}
							
							var datainicial = serie.dataInicial.split('-');
							var datafinal = serie.dataFinal.split('-');
							
							ganttData +="{ \"name\": \""+serie.nome+"\", \"start\":\""+ new Date(datainicial[0],datainicial[1],datainicial[2])+"\", \"end\": \""+new Date(datafinal[0],datafinal[1],datafinal[2])+"\" , \"color\": \""+cor+"\", \"projeto\": \""+serie.projeto+"\" , \"serie\": \""+serie.id+"\" }";
							
							if (idx < projeto.series.length - 1){
								ganttData +=",";
							}
					
					});// fim for da series
				
				
					ganttData +="]}";// fecha o array json de series
					 
				   if (index < ganttDataResposta.length - 1){
					   ganttData +=",";
				   }
					
			 });// fim for dos projetos
				
			ganttData += "]";
				
			ganttData = JSON.parse(ganttData);
				 
			// processa dados gant view FIM
			
			$("#ganttChart").ganttView({ 
					data: ganttData,
					slideWidth: 600,
					behavior: {
						onClick: function (data) { 
							var msg = "Você clicou em um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
							$("#eventMessage").text(msg);
						},
						onResize: function (data) { 
							
							var start = data.start.toString("yyyy-M-d");
							var end = 	data.end.toString("yyyy-M-d");
							$.post("DatasPlanejamentoServlet", { start: start, end : end, serie : data.serie, projeto : data.projeto });
							
							var msg = "Você redimensionou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";

							$("#eventMessage").text(msg);
						},
						onDrag: function (data) { 
							

							var start= data.start.toString("yyyy-M-d");
							var end = data.end.toString("yyyy-M-d");
							
							$.post("DatasPlanejamentoServlet", { start: start, end : end, serie : data.serie, projeto : data.projeto });
							
							var msg = "Você arrastou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";

							$("#eventMessage").text(msg);
						}
					}
				});
		});
	});	
	</script>


	<!-- * [ EXEMPLO PARA TRAZER DO SERVIDOR
		
		{
			id: 1, name: "Projeto Java Web ", series: [
				{ name: "Planejado", start: new Date(2021,00,05), end: new Date(2010,00,20) },
				{ name: "Atual", start: new Date(2021,00,06), end: new Date(2010,00,17), color: "#f0f0f0" },
				{ name: "Projetado", start: new Date(2021,00,06), end: new Date(2010,00,20), color: "#e0e0e0" }
			]
		}
		
	];*/
 -->

</body>
	
</html>






