<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<%
	User user = (User) request.getSession().getAttribute("user");
%>
<p>
	Hallo <%= user.getFirstname()+" "+user.getLastname() %>
</p>

</body>
</html>