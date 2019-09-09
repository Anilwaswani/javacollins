package ctl;

import java.io.IOException;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import exception.NullValueException;
import model.UserModel;
import util.DataValidator;

public class LoginCtl extends HttpServlet {
	protected boolean validate(HttpServletRequest request, String submit) {

		if (submit.equals("Login")) {
			if (DataValidator.isNull(request.getParameter("userName"))) {

				request.setAttribute("errormessage", "Username cannot be left blank");
				return false;
			}
			if (DataValidator.hasWhiteSpace(request.getParameter("userName"))) {
				request.setAttribute("errormessage", "Please remove blank spaces from UserName");
				return false;
			}

			if (DataValidator.isNull(request.getParameter("password"))) {
				request.setAttribute("errormessage", "Please insert the password");
				return false;
			}

			else if (DataValidator.isPasswordLong(request.getParameter("password"))) {
				request.setAttribute("errormessage", "Password must be of at least six characters");
				return false;
			}

			if (DataValidator.hasWhiteSpace(request.getParameter("password"))) {
				request.setAttribute("errormessage", "Please remove blank spaces from password");
				return false;
			}
			return true;
		} else {
			return true;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		System.out.println(operation);
		if (operation == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/LoginView.jsp");
			rd.forward(request, response);

		}
		else if (operation.equals("Logout")) {
			request.setAttribute("operation", operation);
			HttpSession session = request.getSession(true);
			session.removeAttribute("user");
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/LoginView.jsp");
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");

		if (validate(request, operation)) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			System.out.println(userName);
			System.out.println(password);
			if (operation.equals("SignUp")) {
				request.setAttribute("operation", operation);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/UserView.jsp");
				rd.forward(request, response);

			} else if (operation.equals("Login")) {
				try {
					UserBean user = UserModel.isTrueUser(userName, password);

					if (user != null) {
						System.out.println("User found");
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						response.sendRedirect("/InquirySystem/InquiryListCtl");
					} else {
						request.setAttribute("errormessage", "Invalid userName or password");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/LoginView.jsp");
						rd.forward(request, response);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch bloc
					e.printStackTrace();
				}
			} else if (operation.equals("Logout")) {
				HttpSession session = request.getSession(false);
				session.setAttribute("user", null);

				doGet(request, response);
			}
		} else {
			RequestDispatcher rd=request.getRequestDispatcher("/jsp/LoginView.jsp");
			rd.forward(request, response);
		}
	}
}