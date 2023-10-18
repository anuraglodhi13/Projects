<%@page import="com.nagarro.model.AuthorDTO"%>
<%@page import="com.nagarro.model.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*,java.text.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/modal.css"/>" rel="stylesheet">
<title>HomePage List</title>
</head>
<body>

	<%
	  response.setHeader("pragma", "no-cache");              
	  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	  response.setHeader("Expires", "0");
	  
	  if(session.getAttribute("username") == null) {
		  response.sendRedirect("login");
	  }
	  
	%>
	<!-- Main container starts here -->
	<div class="container-fluid">

		<!-- Navbar starts here -->

		<nav class="navbar navbar-primary bg-gradient-primary"
			style="background-color: aliceblue; height: 80px;">
			<h3 class="navbar-nav mx-auto">
				<b style="padding-left: 150px;"> Library Management </b>
			</h3>
			<form class="form-inline my-2 my-lg-0" action="logout" method="GET">
				<h5 style="margin-bottom: 0px; margin-right: 5px;">
					<% out.println("<b> Welcome &nbsp;" +(String)session.getAttribute("username")+"&nbsp;</b>");%>
					
				</h5>
				<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Logout</button>
			</form>
		</nav>

		<!-- Navbar ends here  -->

		<!-- Main section starts -->

		<main style="padding-left: 20 px; padding-right: 20 px;">
			<br>
			<div style="display: flex; justify-content: center;">
				<div
					style="flex: 1; justify-content: center; display: flex; transform: translateX(10px); font-size: 24px;">
					<b style="padding-left: 30px;">Books Listing</b>
				</div>
				<div>
					<button class="btn btn-primary" type="submit"
						onclick="location.href='<%=request.getContextPath()%>/add'">Add
						a Book</button>
				</div>

			</div>

			<br> <br>
<% String bookExist = (String)session.getAttribute("book-already-exist");
	if(bookExist != null && bookExist.equals("true")) {
		out.println("<span style='color:red'>That book can't be added as book code is already present </span> <br>");
		session.setAttribute("book-already-exist",null);
	}
	String bookEdit = (String)session.getAttribute("book-edited"); 
	if(bookEdit != null && bookEdit.equals("true")) {
		out.println("<span style='color:green'>Book Edited Successfully!! </span> <br>");
		session.setAttribute("book-edited",null);
	}
	String bookDelete = (String)session.getAttribute("book-deleted"); 
	if(bookDelete != null && bookDelete.equals("true")) {
		out.println("<span style='color:red'>Book Deleted Successfully!! </span> <br>");
		session.setAttribute("book-deleted",null);
	}
	String bookAdd = (String)session.getAttribute("book-added"); 
	if(bookAdd != null && bookAdd.equals("true")) {
		out.println("<span style='color:green'>Book Added Successfully!! </span> <br>");
		session.setAttribute("book-added",null);
	}
	%>
	<br>

			<!-- Table section starts here -->



			<section>
				<div>
					<table class="table table-bordered">
						<thead>
							<tr style="text-align: center;">
								<th>Book Code</th>
								<th>Book Name</th>
								<th>Author</th>
								<th>Date Added</th>
								<th style="width: 235px;">Actions</th>
							</tr>

						</thead>
						<tbody>
							<!-- looping through products fetched from table -->
							<c:forEach items="${booksInTable}" var="book">
								<tr style="text-align: center;">
									<td>${book.getBookCode()}</td>
									<td>${book.getBookTitle()}</td>
									<td>${book.getAuthor().getAuthorName()}</td>
									<td>${book.getAddedOn()}</td>
									<td>
										<div class="action-block"
											style="display: flex; text-align: center;">
											<!-- Edit button -->
											<form action="edit/${book.getBookCode()}" method="GET">
												<button class="btn btn-outline-secondary my-2 mx-3 my-sm-0"
													style="width: 88%;" type="submit">Edit</button>
											</form>
											<!-- Delete button -->
											<a href="#deleteModal${book.getBookCode()}" data-toggle="modal"
												class="btn btn-outline-danger my-2 ml-4 my-sm-0 trigger-btn">Delete</a>
										</div>
									</td>
								</tr>
								
								
								<!-- Delete Modal -->
			<div id="deleteModal${book.getBookCode()}" class="modal fade">
				<div class="modal-dialog modal-confirm">
					<div class="modal-content">
						<div class="modal-header flex-column">
							<div class="icon-box">
								<i class="material-icons">&#xE5CD;</i>
							</div>
							<h4 class="modal-title w-100">Are you sure?</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
						<div class="modal-body">
							<p>Do you really want to delete this record? This process
								cannot be undone.</p>
						</div>
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancel</button>
							<form action="delete/${book.getBookCode()}" method="GET">
								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</div>
					</div>
				</div>
			</div>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</section>
		</main>
		<footer>
			<nav class="navbar navbar-primary bg-gradient-primary"
				style="background-color: aliceblue; height: 80px;"></nav>
		</footer>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</body>
</html>