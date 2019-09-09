<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp Page</title>

	<img src="images/logo-41.png"/>

</head>
<body>
<div align="center">
<form action="/InquirySystem/UserCtl" method="post">
	<table>
	<tr><td>Enter Full Name:</td><td align="left"><input type="text" name="fullName"></td></tr>
	<tr><td>Enter Father Name:</td><td align="left"><input type="text" name="fatherName"></td></tr>
	<tr><td>Enter Address:</td><td align="left"><input type="text" name="address"></td></tr>
	<tr><td align="right">Enter Phone No:<input type="text"name="phoneNo"></td></tr>
	<tr><td align="right">Enter Mobile No:<input type="text" name="mobileNo"></td></tr>
	<tr><td align="right">Enter a User Name:<input type="text" name="userName"></td></tr>
	<tr><td align="right">Enter password<input type="password" name="password"></td></tr>
	<tr><td align="center"><input type="submit" value="Submit"></td></tr>
	</table>
	</form>
</div>
<%  
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setHeader ("Expires", "0"); //prevents caching at the proxy server  
%>
</body>
</html>