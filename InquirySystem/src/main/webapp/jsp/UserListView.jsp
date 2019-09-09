<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
     <%@ page import="bean.UserBean" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Inquiry Management System</title>

</head>
<body>
<div align="center">
<%@ include file="Header.jsp"%>
<%List list = (List)request.getAttribute("userList");  %>
<%String successmessage = (String) request.getAttribute("successmessage");
			if (successmessage != null) {
		%>
			<h2>
				<font size="6" color="green"><%=successmessage%> </font>
			</h2>
			<%
			}
		%>
			<%String errormessage = (String) request.getAttribute("errormessage");
			if (errormessage != null) {
		%>
			<h2>
				<font size="6" color="red"><%=errormessage%> </font>
			</h2>
			<%
			}
		%>
		<form action="/InquirySystem/UserListCtl" method="post" >
		
		Name:<input type ="text" name="name">
		<input type="submit" name="submit" value="SearchByName">
		Mobile Number:<input type ="text" name="mobileNumber">
		<input type="submit" name="submit" value="SearchByMobile">
<table border="1">
<tr><th> id</th><th>Username</th><th>Password</th><th>Name</th><th> Mobile No.</th><th> Role</th><th> Edit/Delete</th></tr>
<% Iterator it=list.iterator();
while(it.hasNext()){
	UserBean bean= (UserBean)it.next(); 
%>
<tr>
</td><td><%=bean.getId() %></td><td><%=bean.getUserName() %></td><td><%=bean.getPassword() %></td>
<td><%=bean.getName() %></td><td><%=bean.getMobileNo()%></td><td><%=bean.getRole()%></td>
<td><a href="/InquirySystem/UserCtl?operation=EditUser&id=<%= bean.getId()%>"><font size= "4"color="blue">Edit</font></a><a href="/InquirySystem/UserCtl?id=<%=bean.getId()%>&operation=RemoveUser"><font size= "4"color="blue">/Delete</font></a></td>
</tr>
<%} %>
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