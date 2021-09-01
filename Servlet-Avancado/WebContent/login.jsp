<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<style type="text/css">

	h1{
		position: absolute;
		top: 10%;
		left: 45%;
		right: 45%;
		
	}

	form {
			
		position: absolute;
		top: 20%;
		left: 30%;
		right: 30%;

	}

</style>

</head>
<body>

	<h1>Login</h1>
	
	<form action="LoginServlet" method="post">
		<input type="hidden" name="url" value="<%= request.getParameter("url") %>">
		
		<div class="mb-3">
    		<label for="exampleInputEmail1" class="form-label">Login</label>
    		<input class="form-control" id="login" name="login" aria-describedby="emailHelp">
  		</div>
		<div class="mb-3">
    		<label for="exampleInputPassword1" class="form-label">Senha</label>
   			 <input type="password" class="form-control" id="senha" name="senha">
		 </div>
		 <button type="submit" class="btn btn-primary">Acessar</button>
	</form>

</body>
</html>