<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/login.css"/>" rel="stylesheet">
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Library Management</title>
</head>
<body>
	<%
	  response.setHeader("pragma", "no-cache");              
	  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	  response.setHeader("Expires", "0");
	  
	%>
	<!-- Main container starts here -->
	<div class="container-fluid">

		<!-- Navbar starts here -->

		<nav class="navbar navbar-primary bg-gradient-primary"
			style="background-color: aliceblue; height: 80px;">
			<h3 class="navbar-nav mx-auto">
				<b> Library Management </b>
			</h3>
		</nav>

		<!-- Navbar ends here  -->
		<main>
			<div id="loginForm">
				<form action="<%=request.getContextPath()%>/login" method="post"
					class="needs-validation" novalidate>
					<table>
						<tr>
							<td id="top">Login</td>
						</tr>
						<tr>
							<td>
								<div class="form-group row">
									<label for="username" class="col-sm-2 col-form-label">
										Username: </label>
									<div class="col-sm-6">
										<input class="form-control" id="username" name="username"
											type="text" required pattern=".{5,50}">

										<div class="invalid-feedback">Min 5 and Max 50 Characters are allowed</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td><div class="form-group row">
									<label for="username" class="col-sm-2 col-form-label">
										Password: </label>
									<div class="col-sm-6">
										<input class="form-control" id="password" name="password"
											type="password" required pattern=".{5,50}">

										<div class="invalid-feedback">Min 5 and Max 50 Characters are allowed</div>
									</div>
								</div>
						</tr>
						<tr>
				<%
				
				String bothNotFound = (String) session.getAttribute("wrong-credentials");
				
				if(bothNotFound != null && bothNotFound.equals("true")) {
					out.println("<h5  style='color:red'> <b>Invalid Credentials, Please double check them !! <b></h5>");
				}
				%>
				</tr>
						<tr id="bottom">
							<td><input type="submit" value="Login>>" /></td>
						</tr>
					</table>
				</form>
			</div>
		</main>
		<footer>
			<nav class="navbar navbar-primary bg-gradient-primary"
				style="background-color: aliceblue; height: 80px;"></nav>
		</footer>
	</div>
	<
	<!-- jQuery library -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>


	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
		<script type="text/javascript" src="<c:url value='/resources/login.js'/>"></script>
</body>
</html>