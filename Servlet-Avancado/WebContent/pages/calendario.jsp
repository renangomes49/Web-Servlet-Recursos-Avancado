<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calendário</title>

<link href='https://fullcalendar.io/assets/demo-to-codepen.css' rel='stylesheet' />
<style>

    html, body {
      margin: 0;
      padding: 0;
      font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
      font-size: 14px;
    }

    #calendar {
      max-width: 1100px;
      margin: 40px auto;
    }

  </style>
 
 <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.9.0/main.min.css' rel='stylesheet' />
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.9.0/main.min.js'></script>
 <script src='https://fullcalendar.io/assets/demo-to-codepen.js'></script> 
  
  <script src="../assets/js/jquery-3.6.0.min.js" ></script>
  
</head>
<body>
  <div id='calendar'></div>

</body>

<script type="text/javascript">



	document.addEventListener('DOMContentLoaded', function() {
		
		$.get("CalendarioEventosServlet",function(response){
			
			var datas = JSON.parse(response);

			var calendarEl = document.getElementById('calendar');
		
		    var calendar = new FullCalendar.Calendar(calendarEl, {
			      initialView: 'dayGridMonth',
			      initialDate: '2021-07-07',
			      headerToolbar: {
			        left: 'prev,next today',
			        center: 'title',
			        right: 'dayGridMonth,timeGridWeek,timeGridDay'
			      },
			      events: datas	
			    });
		
		    calendar.render();
	  });    
  });


</script>

</html>