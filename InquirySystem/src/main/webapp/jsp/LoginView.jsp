<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inquiry Management System</title>


</head>
<body>
<div align="center">
	
	<form action="/InquirySystem/LoginCtl" method="post" >
	<%@ include file="Header.jsp"%>
		<%String message = (String) request.getAttribute("errormessage");
		String msg = (String) request.getAttribute("successmessage");
			if (message != null) {
		%>
		<h2 >
		<font size="6" color="red"><%=message%>
		</font></h2>
		<%
			}
	
			else if (msg != null) {
		%>
		<h2 >
		<font size="6" color="green"><%= msg%>
		</font></h2>
		<%
			}
		%>
		
<table style="align: center">
			<tr>
				<td>Username:</td><td> <input type="text" name=userName></td>
			</tr>
			<tr>
				<td>Password: </td><td> <input type="password" name=password></td>
			</tr>
			<tr>
				<td><input type="submit" name="operation" value="Login"></td>
			<td><input type="submit" name ="operation" value="SignUp">  
</td>
			</table>


</form>
</div>

</body>
</html>