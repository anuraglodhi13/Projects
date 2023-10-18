<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/login.css">
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
						id="necessary">*&nbsp</span> <input type="text" name="username-forget"
						required /></td>
				</tr>
				<tr id="bottom">
					<td>
					<a href = "login.jsp"> Login </a>
					<input type="submit" value="Find Password" />
					</td>
					
				</tr>
				<tr>
				<% 
				String password = (String)request.getAttribute("found-password");
				String usernameFound = (String)request.getAttribute("username-found");
				if(password != null && password.length() > 0) {
					out.println("<h4> Your Password is " + password + "</h4>");
				}
				else if (usernameFound != null && usernameFound.equals("false")){
					out.println("<h4> Username Not Found Try Again !!! </h4>");
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