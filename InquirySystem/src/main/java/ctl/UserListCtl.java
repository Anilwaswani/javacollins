package ctl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NoRecordFoundException;
import model.InquiryModel;
import model.UserModel;
import util.DataValidator;
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected boolean validate(HttpServletRequest request, String operation) {
		if(operation.equals("SearchByName")){
		 if (DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("errormessage", "Enter Valid Name");
		return false;
		}
		}
		if(operation.equals("SearchByMobile")){
			 if (DataValidator.isNull(request.getParameter("mobileNumber"))) {
				request.setAttribute("errormessage", "Please insert a mobile number");
				return false;
			}	}
			
		return true;
	}

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
			List list= UserModel.getUserList();
			System.out.println(list);
			request.setAttribute("userList", list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoRecordFoundException e) {
			request.setAttribute("errormessage", "No Record Found");
			doGet(request, response);
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/UserListView.jsp");
		rd.forward(request, response);
		
			}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String operation = request.getParameter("submit");
			String name = request.getParameter("name");
			String mobileNumber= request.getParameter("mobileNumber");
			if (validate(request,operation)){
			
			
			if (operation.equals("SearchByName")) {
				try {
			List list=	UserModel.getUserListbyName(name);
					request.setAttribute("userList", list);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/UserListView.jsp");
					rd.forward(request, response);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No User with this name");
					doGet(request, response);
					e.printStackTrace();
		}}else if (operation.equals("SearchByMobile")) {
					try {
						System.out.println("in Search By Mobile");
				List list=	UserModel.getUserListbyMobile(mobileNumber);
						request.setAttribute("userList", list);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/UserListView.jsp");
						rd.forward(request, response);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoRecordFoundException e) {
						request.setAttribute("errormessage", "No User with this Mobile number");
						doGet(request, response);
						e.printStackTrace();}
					
			}	

	}
			else{
				doGet(request, response);
			}
		
}}

