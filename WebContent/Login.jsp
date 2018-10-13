<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="style.css">
<link type="text/css" rel="stylesheet" href="lib/materialize/css/materialize.min.css">
</head>
<body>
	<form action="UserUeberpruefen" method="post">
	
	<div class="input-field">
		<input type="email" name="email" class="validate" id="email"/>
		<label for="email">E-Mail</label>
	</div>
	<div class="input-field">
		<input type="password" name="password" id="password"/>
		<label for="password">Password</label>
		<!-- <span class="helper-text" data-error="wrong" data-success="right">Helper text</span> -->
	</div>
	<input class="waves-effect waves-light btn" type="submit" value="Login"/>
	<a class="waves-effect waves-light btn" href="Register.jsp">Register</a>
	
	<%
	if(request.getAttribute("error") != null) {
		out.append("<p class='error'>"+request.getAttribute("error")+"</p>");
	}
	%>
	
	</form>
	
	<script type="text/javascript" src="lib/materialize/js/materialize.min.js"></script>
</body>
</html>