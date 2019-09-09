<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="bean.UserBean"%>
    <%@page import="bean.InquiryBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Welcome to inquiry page</title>
</head>
<body>
<div align="center">
<form action="/InquirySystem/InquiryCtl" method="post"> 
<%@ include file="Header.jsp"%>
<table>
<%UserBean user= (UserBean)request.getAttribute("user");
InquiryBean bean= (InquiryBean)request.getAttribute("inquirybean");
request.setAttribute("user", user);

String msg=(String) request.getAttribute("successmessage");
String op=(String)request.getAttribute("operation");

if (msg!= null){
%>

<tr><td align="center"><h2><font size="5" color="green"><%=msg %></font></h2></td></tr>
<%} %><%String errormessage = (String) request.getAttribute("errormessage");
			if (errormessage != null) {%>
			<h2><font size="6" color="red"><%=errormessage%> </font></h2>
			<%}%>
<%try{if (op.equals("Make an Inquiry")){%>
<tr><td align="left">Name:</td><td><input type="text" name="name" ></td></tr>
<tr><td align="left" >Contact number:</td><td><input type="text" name="contactNumber"></td></tr>
<tr><td align="left">Purpose:</td><td><input type= "text" name="purpose"></td></tr>
<tr><td align="left">Reference:</td><td><input type="text" name="reference"></td></tr>
<tr><td align="left">Remarks:</td><td><input type="text" name="remarks"></td></tr>
<tr><td align="left"><input type="submit" name="submit" value="Submit Inquiry"></td></tr>
<%}else if (op.equals("Remove Inquiry")||op.contains("RemoveInquiry")){ 
int id=(Integer)request.getAttribute("id");%>
<tr><td align="left">Name:</td><td><input type="text" name="name" value= <%= bean.getName() %>></td></tr>
<tr><td align="left" >Contact number:</td><td><input type="text" name="contactNumber" value=<%=bean.getContactNumber() %>></td></tr>
<tr><td><input type="submit" name="submit" value="Remove Inquiry"></td></tr>
<%} else if (op.equals("Edit Inquiry")|| op.contains("EditInquiry")){ 
	int id=(Integer)request.getAttribute("id");
%>
<tr><td align="left">Name:</td><td><input type="text" name="name" value= <%=bean.getName() %>></td></tr>
<tr><td align="left" >Contact number:</td><td><input type="text" name="contactNumber" value= <%= bean.getContactNumber()%>></td></tr>
<tr><td align="left">Purpose:</td><td><input type= "text" name="purpose" value=<%=bean.getPurpose()%>></td></tr>
<tr><td align="left">Reference</td><td><input type="text" name="reference" value= <%=bean.getReference()%>></td></tr>
<tr><td align="left">Remarks:</td><td><input type="text" name="remarks" value=<%=bean.getRemark()%>></td></tr>
<tr><td align="left"><input type="submit" name="submit" value="Edit Inquiry"><input type="hidden" name="id" value="<%=id%>"></td></tr>
<% }}catch(NullPointerException ne){%>
<tr><td align="left">Name:</td><td><input type="text" name="name" ></td></tr>
<tr><td align="left" >Contact number:</td><td><input type="text" name="contactNumber"></td></tr>
<tr><td align="left">Purpose:</td><td><input type= "text" name="purpose"></td></tr>
<tr><td align="left">Reference:</td><td><input type="text" name="reference"></td></tr>
<tr><td align="left">Remarks:</td><td><input type="text" name="remarks"></td></tr>
<tr><td align="left"><input type="submit" name="submit" value="Submit Inquiry"></td></tr>
<%}%>
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