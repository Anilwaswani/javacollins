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
		<form action="/InquirySystem/UserCtl" method="post">
		<%@ include file="Header.jsp"%>
			<%UserBean bean=(UserBean)request.getAttribute("bean");
		//	String id=(String) request.getAttribute("id");
		
				String successmessage = (String) request.getAttribute("successmessage");
				if (successmessage != null) {
			%>
			<h2>
				<font size="6" color="green"><%=successmessage%> </font>
			</h2>
			<%
				}
			%>
			<%
				String errormessage = (String) request.getAttribute("errormessage");
				if (errormessage != null) {
			%>
			<h2>
				<font size="6" color="red"><%=errormessage%> </font>
			</h2>
			<%
				}
			%>

			<table>
				<%
					String operation = (String) request.getAttribute("operation");
				try {	if (operation.equals("AddUser")) {
				%>

				<tr>
					<td>Name:</td><td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Mobile Number:</td>
					<td ><input type="text" name="mobileNo"></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td ><input type="text" name="role"></td>
				</tr>
				<tr>
					<td >Username:</td><td><input type="text"
						name="userName"></td>
				</tr>
				<tr>
					<td >Password:</td><td><input type="password"
						name="password"></td>
				</tr>
				<tr>
					<%
						if (operation.equals("AddUser")) {
					%>
					<td><input type="submit" name="operation"
						value="AddUser"></td>
					<%
						} else if (operation.equals("SignUp")) {
					%>
					<td align="center"><input type="submit" name="operation"
						value="SignUp"></td>
					<%
						}
					%>
				</tr>
				<%
					} else if (operation.equals("SignUp")) {
				%>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Mobile Number:</td>
					<td><input type="text" name="mobileNo"></td>
				</tr>
				<tr>
					<td>Username:</td><td> <input type="text"
						name="userName"></td>
				</tr>
				<tr>
					<td>Password:</td><td> <input type="password"
						name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="operation"
						value="SignUp"></td>
				</tr>
				<%
					} else if (operation.equals("Remove User")||operation.contains("RemoveUser")) {
						int id=Integer.parseInt(request.getParameter("id"));
						
						
				%>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value= <%=bean.getName() %>></td>
				</tr>
				<tr>
					<td>Mobile No:</td><td><input type="text"
						name="mobileNo" value=<%=bean.getMobileNo() %>></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" name="operation"
						value="RemoveUser"></td>
				</tr>
				<%
					} else if (operation.contains("EditUser")) {
						int id1=(Integer) request.getAttribute("id");
						
				%>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value=<%=bean.getName() %>></td>
				</tr>
				<tr>
					<td>Mobile Number:</td>
					<td><input type="text" name="mobileNo" value=<%=bean.getMobileNo() %>></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td><input type="text" name="role" value=<%=bean.getRole() %>></td>
				</tr>
				<tr>
					<td>Username:</td><td><input type="text"
						name="userName"></td>
				</tr>
				<tr>
					<td>Password:</td><td><input type="password"
						name="password"></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="Edit User"></td>
				</tr>
				<%
					} else if (operation.equals("Edit Profile")) {
				%>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Mobile Number:</td>
					<td ><input type="text" name="mobileNo"></td>
				</tr>
				<tr>
					<td>Username:</td><td> <input type="text"
						name="userName"></td>
				</tr>
				<tr>
					<td>Password:</td><td> <input type="password"
						name="password"></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="Edit Profile"></td>
				</tr>
				<%}else if(operation.equals("Edit User")) { %>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
				<td>Mobile Number:</td>
				<td><input type="text" name="mobileNo" ></td>
			</tr>
			<tr>
				<td>Role:</td>
				<td><input type="text" name="role" ></td>
			</tr>
			<tr>
				<td>Username:</td><td><input type="text"
					name="userName"></td>
			</tr>
			<tr>
				<td>Password:</td><td><input type="password"
					name="password"></td>
			</tr>
			<tr>
				<td align="right"><input type="submit" name="operation"
					value="Edit User"></td>
			</tr>
			<%}}catch(NumberFormatException nfe){ %>
					<tr>
					<td>Name:</td>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<td>Mobile No:</td><td><input type="text"
						name="mobileNo" ></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" name="operation"
						value="Remove User"></td>
				</tr>
				<%}%>		</table>
		</form>
	</div>
	<%  
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setHeader ("Expires", "0"); //prevents caching at the proxy server  
%>
</body>
</html>