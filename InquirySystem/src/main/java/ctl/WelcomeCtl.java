package ctl;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import bean.UserBean;
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/ctl/WelcomeCtl" })
/**
 * Servlet implementation class WelcomeCtl
 */
public class WelcomeCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession(true);
			UserBean user= (UserBean) session.getAttribute("user");
			if(user==null){
				System.out.println(user.getName());
				response.sendRedirect("/InquirySystem/LoginCtl");
			} else{
				RequestDispatcher rd= request.getRequestDispatcher("/jsp/Welcome.jsp");
				rd.forward(request, response);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doPostWelcomeCtl");
	String op=request.getParameter("operation");System.out.println(op);
	
	if(op.equals("Remove User")||op.equals("AddUser")|| op.equals("Edit User")||op.equals("Edit Profile")){
		HttpSession session =request.getSession(true);
		session.setAttribute("operation", op);
		response.sendRedirect("/InquirySystem/UserCtl");
	}
	else if(op.equals("Remove Inquiry")||op.equals("Make an Inquiry")){
		HttpSession session =request.getSession(true);
		UserBean user= (UserBean) session.getAttribute("user");
		
		session.setAttribute("user", user);
		session.setAttribute("operation", op);
		request.setAttribute("operation", op);
		 response.sendRedirect("/InquirySystem/InquiryCtl");
	}else if (op.equals("See User List")){
		HttpSession session =request.getSession(true);
		session.setAttribute("operation", op);
		response.sendRedirect("/InquirySystem/UserListCtl");
		
	}else if (op.equals("See Inquiry List")||op.equals("Edit Inquiry")){
		HttpSession session =request.getSession(true);
		session.setAttribute("operation", op);
		response.sendRedirect("/InquirySystem/InquiryListCtl");
		 
	}
		
	}

}
