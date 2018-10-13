<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="style.css">
<link type="text/css" rel="stylesheet" href="lib/materialize/css/materialize.min.css">
</head>
<body>
	<form action="NeuUeberpruefen" method="post">
		<div class="input-field">
			<input type="email" name="email" id="email"/>
			<label for="email">E-Mail</label>
		</div>
	
		<div class="input-field">
			<input type="password" name="password" id="password"/>
			<label for="password">Password</label>
		</div>
	
		<div class="input-field">
			<input type="password" name="password2" id="password2"/>
			<label for="password2">Confirm Password</label>
		</div>
	
		<div class="input-field">
			<input type="text" name="firstname" id="firstname"/>
			<label for="firstname">Firstname</label>
		</div>
	
		<div class="input-field">
			<input type="text" name="lastname" id="lastname"/>
			<label for="lastname">Lastname</label>
		</div>
		<input class="waves-effect waves-light btn" type="submit" value="Register"/>
		
		<%
		if(request.getAttribute("error") != null) {
			out.append("<p class='error'>"+request.getAttribute("error")+"</p>");
		}
		%>
		
	</form>
	
	<script type="text/javascript" src="lib/materialize/js/materialize.min.js"></script>
</body>
</html>