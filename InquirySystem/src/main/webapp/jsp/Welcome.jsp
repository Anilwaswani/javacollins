<%@page import="bean.UserBean"%>
<html>

<body><%
			UserBean bean = (UserBean) session.getAttribute("user");
			String name = bean.getName();

			String role = bean.getRole();
			session.setAttribute("user", bean);
		%><h3><font size="5" color="green">
					<%
						if (bean!=null) {
							//String role = (String) session.getAttribute("role");
						String 	Msg="Hello,"+ bean.getName() + " (" + bean.getRole() + ")";
					%>

					<%=Msg%></font></h3> <%
 	}
 %>


<div align="center">
		

	<form action="/InquirySystem/WelcomeCtl" method="post">
<%@ include file="Header.jsp"%>
					</td>
		
		
		<td>
					</form>
	</div>
	<%  
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setHeader ("Expires", "0"); //prevents caching at the proxy server  
%>   
	
</body>
</html>