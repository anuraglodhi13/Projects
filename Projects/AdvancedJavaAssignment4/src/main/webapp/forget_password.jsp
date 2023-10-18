<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/login.css"/>" rel="stylesheet">
<title>E-Commerce</title>
</head>
<body>
	<div id="loginForm">
		<form action="<%=request.getContextPath()%>/forgetPassword" method="post"
			onsubmit="return validateLoginForm(this)">
			<table>
				<tr>
					<td id="top">Forget Password</td>
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
				<tr id="bottom">
					<td>
					<a href = "login.jsp"> Login </a>
					<input type="submit" value="Reset Password" />
					</td>
					
				</tr>
				<tr>
				<% 
				String password = (String)request.getAttribute("password-reset");
				if(password != null && password.equals("true")) {
					out.println("<h4> Password reset Succesfully !!! </h4>");
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