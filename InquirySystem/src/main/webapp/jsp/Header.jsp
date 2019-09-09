<%@page import="bean.InquiryBean"%>
<%@page import="ctl.LoginCtl"%>
<%@page import="bean.UserBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inquiry Management System</title>
</head>
<body>
<%
		UserBean userBean = (UserBean) session.getAttribute("user");

		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hi, ";
		if(userLoggedIn){
	%>
<h3 align="right"><a href="/InquirySystem/LoginCtl?operation=Logout"><font size= "4"color="red">Logout</font></a></h3>
<%} %>

<img src="images/logo-41.png" height="200px" width="300px"/>
		<table width="100%" border="0">
		<tr>
					</tr>
		<%
			if (userLoggedIn) {
		%>
		<tr>
			<td colspan="4">
				<%
					if (userBean.getRole().equalsIgnoreCase("admin")) {
				%><a
				href="/InquirySystem/UserCtl?operation=AddUser"><b> Add User </b></a> | <a
				href="/InquirySystem/UserListCtl?operation=Remove User"><b> Remove User </b></a> | <a
				href="/InquirySystem/UserListCtl?operation=Edit User"><b> Edit User </b></a> | <a
				href="/InquirySystem/InquiryCtl?operation=Make an Inquiry"><b> Add Inquiry </b></a> | <a
				href="/InquirySystem/InquiryListCtl?operation=Remove Inquiry"><b> Remove Inquiry </b></a> | <a
				href="/InquirySystem/InquiryListCtl?operation=Edit Inquiry"><b> Edit Inquiry </b></a> | <a 
				href="/InquirySystem/InquiryListCtl?operation=See Inquiry List"><b> See Inquiries </b></a> | <a
				href="/InquirySystem/UserListCtl?operation=See User List"><b> See Users </b></a> | 
				<%}else{%>
				<a 
			href="/InquirySystem/UserCtl?operation=Edit Profile"><b> Edit Profile </b></a> | <a
			href="/InquirySystem/InquiryCtl?operation=Make an Inquiry"><b> Add Inquiry </b></a> | <a 
			href="/InquirySystem/InquiryListCtl?operation=Remove Inquiry"><b> Remove Inquiry </b></a> | <a 
			href="/InquirySystem/InquiryListCtl?operation=Edit Inquiry"><b> Edit Inquiry </b></a> | <a
			href="/InquirySystem/InquiryListCtl?operation=See Inquiry List"><b> See Inquiries </b></a> | 
			</td>
			
<%} %>
		</tr>
		<%
			}
		%>
	</table>
	<hr>
</body>
</html>