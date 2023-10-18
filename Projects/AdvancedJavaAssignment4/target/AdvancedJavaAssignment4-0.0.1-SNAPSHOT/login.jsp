<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/login.css" />" rel="stylesheet">
<title>E-Commerce</title>
</head>
<%
	  response.setHeader("pragma", "no-cache");              
	  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	  response.setHeader("Expires", "0");
	  
	%>
	<div id="loginForm">
		<form action="<%=request.getContextPath()%>/login" method="post"
			onsubmit="return validateLoginForm(this)">
			<table>
				<tr>
					<td id="top">Login</td>
				</tr>
				<tr>
					<td><label for="username">Username:</label> <span
						id="necessary">*&nbsp</span> <input type="text" name="username"
						required /></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label> <span
						id="necessary">*&nbsp</span> <input type="password"
						name="password" required /></td>
				</tr>
				<tr>
					<td> <a href = "forget_password.jsp">Forgot Password ? </a>
					</td>
				</tr>
				<tr id="bottom">
					<td><input type="submit" value="Login>>" /></td>
				</tr>
				<tr>
				<%
				String onlyUsername = (String) request.getAttribute("only-username");
				String bothNotFound = (String) request.getAttribute("both-not-found");
				String onlyPassword = (String) request.getAttribute("only-password");
				
				if (onlyPassword != null && onlyPassword.equals("true")) {
					out.println("<h4>Username is wrong !!!");
					request.setAttribute("only-password",null);
					out.println("<a href = 'login.jsp'> Try Again </a ></h4>");
				}
				else if(onlyUsername != null && onlyUsername.equals("true")) {
					out.println("<h4>Password is wrong !!!");
					request.setAttribute("only-username",null);
					out.println("<a href = 'login.jsp'> Try Again </a >"+"Or Try"+"<a href = 'forget_password.jsp'> Forget Password </a> </h4>");
				}
				else if(bothNotFound != null && bothNotFound.equals("true")) {
					out.println("<h4>Both Username and password is wrong !!!");
					request.setAttribute("both-not-found",null);
					out.println("<a href = 'login.jsp'> Try Again </h4>");
				}
				%>
				</tr>
			</table>
		</form>
	</div>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>