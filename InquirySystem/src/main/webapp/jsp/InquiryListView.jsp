<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
     <%@ page import="bean.InquiryBean" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Inquiry Management System</title>

</head>
<body>
<div align="center">
<%List list = (List)request.getAttribute("inquiryList");%>


<form  action="/InquirySystem/InquiryListCtl" method="post" >
<%@ include file="Header.jsp"%>
		<%String message = (String) request.getAttribute("errormessage");
			if (message != null) {
		%>
		<h2 >
		<font size="6" color="red"><%=message%>
		</font></h2>
		<%
			}
		%>
<%String successmessage = (String) request.getAttribute("successmessage");
			if (message != null) {
		%>
		<h2 >
		<font size="6" color="green"><%=successmessage%>
		</font></h2>
		<%
			}
		%>

 Date:<input type ="text" name="date">
<input type="submit" name="submit" value="SearchByDate">
Name:<input type ="text" name="name">
<input type="submit" name="submit" value="SearchByName">

<table border="1">
<tr><th> id</th><th>Name</th><th>Contact No.</th><th>Purpose</th><th>Time</th><th>Reference</th><th>Remark</th><th>Add By</th><th>Date</th><th> Edit/Delete</th></tr>
<% Iterator it=list.iterator();
while(it.hasNext()){
	InquiryBean bean= (InquiryBean)it.next(); 
%>
<tr>
<td><%=bean.getId() %></td><td><%=bean.getName()%></td><td><%=bean.getContactNumber() %></td>
<td><%=bean.getPurpose()%></td><td><%=bean.getTime()%></td><td><%=bean.getReference()%></td><td><%=bean.getRemark()%></td><td><%=bean.getAddBy()%></td><td><%=bean.getDate()%></td>
<td><a href="/InquirySystem/InquiryCtl?id=<%=bean.getId()%>&operation=EditInquiry"><font size= "4"color="blue">Edit</font></a><a href="/InquirySystem/InquiryCtl?id=<%=bean.getId()%>&operation=RemoveInquiry"><font size= "4"color="blue">/Delete</font></a></td>
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