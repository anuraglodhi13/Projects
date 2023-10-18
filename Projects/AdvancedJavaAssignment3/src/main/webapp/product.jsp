<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/product.css">
<style>
.header-wrapper {
	display: flex;
	position: relative;
	width: 100%;
	justify-content: center;
	align-items: center;
}

.save {
	display: none;
}

.alert {
	padding: 20px;
	background-color: #f44336;
	color: white;
	opacity: 1;
	transition: opacity 0.6s;
	margin-bottom: 15px;
}

.closebtn {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>

</head>
<body style="margin-left: 20px; margin-right: 20px;">
	<%@page import="java.util.ArrayList"%>
	<%@page import="model.Product"%>
	<%@page import="java.util.Iterator"%>
	<%@page import="java.io.ByteArrayInputStream"%>
	<%@page import="java.awt.image.BufferedImage"%>
	<%@page import="javax.imageio.ImageIO"%>
	<%@page import="java.io.File"%>
	<%@page import="java.util.List"%>
	<%@page import="java.io.FileOutputStream"%>
	<div>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
		<% 
List<Product> li = (List) session.getAttribute("productList");
if(li==null)
{
	Cookie c =  new Cookie("username", "") ;
	c.setMaxAge(0);
	response.addCookie(c);
	response.sendRedirect("login.jsp");
	return  ;
}
String id, title, size, preview , editAction ,imgPath,quantity;
String SAVE_DIR,appPath,savePath,filePath ;
SAVE_DIR = "Images";
appPath = request.getServletContext().getRealPath("");
savePath = appPath + "\\" + SAVE_DIR;
FileOutputStream fos ;
double totalSize = 0.0;
int max_pid = Integer.MIN_VALUE;
for(int i = 0; i<li.size(); i++){
	int productId = li.get(i).getId();
	max_pid = Math.max(max_pid,productId);
	filePath = savePath +"\\" + "name" +"_"+ productId;
	File file = new File(filePath);
	if(file.exists()) {
		double bytes = file.length();
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        totalSize+=megabytes;
	}
}
if(totalSize > 10) {
	String danger = "Danger!";
	String message = " Database files capacity exceeded due to previous entry !!!";
	out.println("<div class='alert'>"+
			  "<span class='closebtn'>&times;</span>"+ 
			  "<strong>"+danger+"</strong>"+message+""+
			"</div>");
}
%>
	</div>
	<header>
		<div class="header-wrapper">
			<h2 class="header" style="text-align: centre; margin: auto;">Product
				Management Tool</h2>
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
	<h2>Please Enter Product Details To Add To Stock</h3>
	<div>
		<form id="productSave" action="<%=request.getContextPath()%>/product"
			method="POST" enctype="multipart/form-data"
			onsubmit='return numberValidation()'>
			<table>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>Quantity</td>
					<td><input type="text" name="quantity" onchange="validateQuantity(this.value)"> <span
						id="numberText1"></span></td>
				</tr>
				<tr>
					<td>Size</td>
					<td><input type="text" name="size" onchange = "validateSize(this.value)"> <span
						id="numberText"></span></td>
				</tr>
				<tr>
					<td>Image</td>
					<td><input type="file" name="image" id="image"
						onchange="validateImageSize(this)"></td>
				</tr>
				<tr>
					<td><br> <input type="submit" value="Submit"
						id="form_submit_btn" name="form_submit_btn"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<br> <br>
		<table class='product-table' style='width: 100%'>
			<tr class='product-table'>
				<th class='product-table'>S.NO</th>
				<th class='product-table'>Title</th>
				<th class='product-table'>Quantity</th>
				<th class='product-table'>Size</th>
				<th class='product-table'>Image</th>
				<th class='product-table'>Action</th>
			</tr>
			<% 


for(int i = 0; i<li.size(); i++){
	int sno = i+1;
	title = li.get(i).getTitle();
	int productId = li.get(i).getId();
	quantity = li.get(i).getQuantity();
	size = li.get(i).getSize();
		filePath = savePath +"\\" + "name" +"_"+ productId;
		fos = new FileOutputStream(filePath);
    fos.write(li.get(i).getProduct_image());
	preview = "Images\\"+"name" +"_"+ productId;
	int row_id = sno;
	String titleClassNameWithRow = "title"+String.valueOf(row_id);
	String quantityClassNameWithRow = "quantity"+String.valueOf(row_id);
	String sizeClassNameWithRow = "size"+String.valueOf(row_id);
	String previewClassNameWithRow = "preview"+String.valueOf(row_id);
	String actionClassNameWithRow = "action"+String.valueOf(row_id);
	out.println("<tr class = 'product-table'>");
	out.println("<td class = 'product-table' width='50px' height = '50px'>"+sno+"</td>");
	out.println("<td class = 'product-table' id="+titleClassNameWithRow+" width='200px' height = '200px'>"+title+"</td>");
	out.println("<td class = 'product-table' id="+quantityClassNameWithRow+" width='100px' height = '100px'>"+quantity+"</td>");
	out.println("<td class = 'product-table' id="+sizeClassNameWithRow+" width='100px' height = '100px'>"+size+"</td>");
	out.println("<td class = 'product-table image-data' id="+previewClassNameWithRow+" width='200px' height = '200px'><img width='150px' height = '150px' src = '" + preview + "'></td>");
	out.println("<td class = 'product-table' width='200px' height = '200px' id="+actionClassNameWithRow+">"
			+"<input type='button' id='edit_button"+row_id+"' value='Edit' class='edit' onclick='edit_row("+row_id+")'> &nbsp "
			+"<input type='button' id='save_button"+row_id+"' value='Save' class='save' onclick='save_row("+row_id+")'> &nbsp"
			+"<input type='button' id='delete_button"+row_id+"' value='Delete' class='delete' onclick='delete_row("+row_id+")'>"
			+"</td>");
	out.println("</tr>");	
}
%>
		</table>

	</div>

	<div>
		<p class=total-size>
		<h2>
			Total Size of the uploaded products is :
			<%
int sze = 0; 
for(int i =0; i<li.size();i++) {
	if(li.get(i).getSize() == null || li.get(i).getSize().equals("")) {
		continue;
	}
	
	sze += Integer.parseInt(li.get(i).getSize());
}
out.println(""+sze+"</h2>");

%>
			</p>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/product.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	var close = document.getElementsByClassName("closebtn");
	    var i;
	    for (i = 0; i < close.length; i++) {
	    close.onclick = function(){
	    var div = this.parentElement;
	    div.style.opacity = "0";
	    setTimeout(function(){ div.style.display = "none"; }, 600);
	  }
	    }
</script>
</body>
</html>