<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tshirt Search</title>
<style>
.product-table {
  border:1px solid black ;
  border-collapse:collapse ;
  text-align: center;
}
.header-wrapper {
	display: flex;
	position: relative;
	width: 100%;
	justify-content: center;
	align-items: center;
}

</style>
</head>
<body style="margin-left: 20px; margin-right: 20px;">
	<%@page import="com.nagarro.model.TshirtData"%>
	<%@page import="java.util.List"%>
	<%
	  response.setHeader("pragma", "no-cache");              
	  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	  response.setHeader("Expires", "0");
	  
	  if(session.getAttribute("username") == null) {
		  response.sendRedirect("login.jsp");
	  }
	  
	%>
	<div class="header-wrapper">
			<h2 class="header" style="text-align: centre; margin: auto;">Tshirt Search Tool</h2>
			<div class="checkbox-div">
				<form method="POST" action="<%=request.getContextPath()%>/logout">
					<% String username = (String)session.getAttribute("username");
        out.println("<label for='sub-folder-checkbox'>Hi "+username+" &nbsp;&nbsp; </label>");%>
					<input type="submit" value="Logout" id="logout" name="logout" />
				</form>
			</div>
		</div>
	</header>
	<br>
	<br>
	<h2>Please Enter Tshirt Details To Search</h3>
	
	<div>
		<form id="tshirtSearch" action="<%=request.getContextPath()%>/tshirt"
			method="POST" 
			>
			<table>
				<tr>
					<td>Colour</td>
					<td><input type="text" name="colour"> </td>
				</tr>
				<tr>
					<td>Size</td>
					<td><select name="size" id="size">
							<option value="S">S</option>
							<option value="M">M</option>
							<option value="L">L</option>
							<option value="XL">XL</option>
							<option value="XXL">XXL</option>
					</select></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><select name="gender" id="gender">
							<option value="M">Male</option>
							<option value="F">Female</option>
							<option value="U">Unisex</option>
					</select></span></td>
				</tr>
				<tr>
					<td>Output Preference</td>
					<td><select name="output-preference" id="output-preference">
							<option value="1">Price</option>
							<option value="2">Rating</option>
							<option value="3">Both Price And Rating</option>
					</select></td>
				</tr>
				<tr>
					<td><br> <input type="submit" value="Search Tshirt"
						id="form_submit_btn" name="form_submit_btn"></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div>
		<br> <br>
			<%
			List <TshirtData> li = (List)request.getAttribute("searched-tshirt");
			Integer listSize = (Integer)request.getAttribute("list-size");
			String showMessage = (String)request.getAttribute("show-not-found");
			
			if(showMessage != null && showMessage.equals("true")){
				out.print("<h3> Hmmm, we din't found anything for your query.</h3>");
				out.print("<h3> Try something different !!! </h3>");
			}
			if(li != null && li.size() != 0) {
				out.print("<table class='product-table' style='width: 100%'>");
				out.print("<tr class='product-table'>");
				out.print("<th class='product-table'>SNO</th>");
				out.print("<th class='product-table'>ID</th>");
				out.print("<th class='product-table'>NAME</th>");
				out.print("<th class='product-table'>COLOUR</th>");
				out.print("<th class='product-table'>GENDER_RECOMMENDATION</th>");
				out.print("<th class='product-table'>SIZE</th>");
				out.print("<th class='product-table'>PRICE</th>");
				out.print("<th class='product-table'>RATING</th>");
				out.print("<th class='product-table'>AVAILABILITY</th>");
				out.print("</tr>");
			for(int i=0;i<li.size();i++) {
					int sNo = i+1;
					String id = li.get(i).getId();
					String name = li.get(i).getName();
					String colour = li.get(i).getColor();
					String gender = li.get(i).getGenderRecommendation();
					String size = li.get(i).getSize();
					String price = li.get(i).getPrice();
					String rating = li.get(i).getRating();
					String availability = li.get(i).getAvailability();
					out.println("<tr class = 'product-table'>");
					out.println("<td class = 'product-table' width='50px' height = '50px'>"+sNo+"</td>");
					out.println("<td class = 'product-table' width='50px' height = '50px'>"+id+"</td>");
					out.println("<td class = 'product-table'  width='10px' height = '10px'>"+name+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+colour+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+gender+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+size+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+price+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+rating+"</td>");
					out.println("<td class = 'product-table'  width='100px' height = '100px'>"+availability+"</td>");
					out.println("</tr>");	
				}
			}
		%>
		</table>

	</div>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/tshirt.js"></script>
	
</body>
</html>