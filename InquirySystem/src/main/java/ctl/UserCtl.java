package ctl;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.DatabaseMetaData;

import bean.UserBean;
import exception.DuplicateUserException;
import exception.DuplicateUserNameException;
import exception.NoRecordFoundException;
import model.UserModel;
import util.DataValidator;

@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })

public class UserCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected boolean validate(HttpServletRequest request, String operation) {
		//boolean pass = true;

		// String login = request.getParameter("login");
		// String dob = request.getParameter("dob");
		
		if(operation.equals("AddUser")||operation.equals("Remove User")||operation.equals("Edit User")||operation.equals("Edit Profile")){
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("errormessage", "Name cannot be blank");
			return false;
		}else if (DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("errormessage", "Enter Valid Name");
			return false;
		}
		}
		if(operation.equals("AddUser")||operation.equals("Remove User")||operation.equals("Edit User")||operation.equals("Edit Profile")){
			if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("errormessage", "Please insert a mobile number");
			return false;
		}else if (DataValidator.isMobile(request.getParameter("mobileNo"))) {
			request.setAttribute("errormessage", "Please insert a valid mobile number");
			return false;
		}
		}
		
		if(operation.equals("AddUser")||operation.equals("Edit User")){
		if (DataValidator.isNull(request.getParameter("role"))) {
			request.setAttribute("errormessage", "Role cannot be blank");
			return false;
		} else if (DataValidator.isRole(request.getParameter("role"))) {
			request.setAttribute("errormessage", "Please insert a valid Role");
			return false;
		}}
		// System.out.println("From validate method of userCtl after lastName"+
		// pass);

	
		if(operation.equals("AddUser")||operation.equals("Edit User")||operation.equals("Edit Profile")){
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("errormessage", "Please insert the password");
			return false;
		}else if (DataValidator.hasWhiteSpace(request.getParameter("password"))) {
			request.setAttribute("errormessage", "Please remove blank spaces from password");
			return false;
		}else if(DataValidator.isPasswordLong(request.getParameter("password"))){
			request.setAttribute("errormessage", "Password must be of at least six characters");
			return false;
		}
		} if(operation.equals("AddUser")||operation.equals("Edit User")||operation.equals("Edit Profile")){
		if (DataValidator.isNull(request.getParameter("userName"))) {

			request.setAttribute("errormessage", "Username cannot be left blank");
			return false;
		}
		if (DataValidator.hasWhiteSpace(request.getParameter("userName"))) {
			request.setAttribute("errormessage", "Please remove blank spaces from UserName");
			return false;
		}
		}
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if (id!=null){
			request.setAttribute("id", id);
			int id1=Integer.parseInt(id);
			UserBean bean; 
			try {
				bean= UserModel.findByPK(id1);
				request.setAttribute("id", id1);
				request.setAttribute("bean", bean);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		HttpSession session = request.getSession(true);
		String operation = request.getParameter("operation");
		UserBean user = (UserBean) session.getAttribute("user");
		request.setAttribute("user", user);
		System.out.println("Hello");
		System.out.println(operation);

		System.out.println("in doGet UserCtl");

		request.setAttribute("operation", operation);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/UserView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		System.out.println(operation);
		if (validate(request,operation )) {
			System.out.println(DataValidator.isNull(request.getParameter("name")));

			
			System.out.println("In UserCtl doPost method");
			System.out.println(operation);
			if (operation.equals("AddUser") || operation.equals("SignUp")) {
				try {
					UserBean bean = new UserBean();
					bean.setName(request.getParameter("name"));
					bean.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
					bean.setUserName(request.getParameter("userName"));
					if (operation.equals("Add User")) {
						bean.setRole(request.getParameter("role"));
					} else {
						bean.setRole("user");
					}
					System.out.println(bean.getName());
					try {
						bean.setPassword(request.getParameter("password"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(bean.getPassword());

					UserModel.addUser(bean);
					if (operation.equals("AddUser")) {
						request.setAttribute("successmessage", "User added successfully");
						System.out.println("in if");
						doGet(request, response);
					} else {

						request.setAttribute("successmessage", "Signup Successful!  Please Login to Continue");
						System.out.println("in else");
						RequestDispatcher rd= request.getRequestDispatcher("/jsp/LoginView.jsp");
						rd.forward(request, response);
							
						}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateUserException e) {
					request.setAttribute("operation", operation);
					request.setAttribute("errormessage", "User already exists");
					doGet(request, response);
				} catch (DuplicateUserNameException e) {
					request.setAttribute("operation", operation);
					request.setAttribute("errormessage",
							"This Username is not available, Please select a different one");
					doGet(request, response);
					e.printStackTrace();
				}
			} else if (operation.equals("Remove User")||operation.contains("RemoveUser")) {

				String name = request.getParameter("name");
				long mobileNo = Long.parseLong(request.getParameter("mobileNo"));

				try {
					UserModel.deleteUser(name, mobileNo);
					request.setAttribute("successmessage", "Record deleted successfully");
					doGet(request, response);
				} catch (ClassNotFoundException e) { // TODO Auto-generated
														// catch block
					e.printStackTrace();
				} catch (SQLException e) { // TODO Auto-generated catch block
											// e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No records found for such user");
					doGet(request, response);
					e.printStackTrace();
				}
			}

			else if (operation.equals("Edit User") || operation.equals("Edit Profile")||operation.contains("EditUser")) {
				try {
					String id = request.getParameter("id");
					System.out.println(id);
					if (id != null) {
						int id1= Integer.parseInt(id);
						UserBean bean = UserModel.findByPK(id1);
						System.out.println(bean.getName());
						UserModel.updateUser(bean);
						request.setAttribute("successmessage", "User profile edited");
						request.setAttribute("bean", bean);
						doGet(request, response);
					} else {
						UserBean bean = new UserBean();

						bean.setUserName(request.getParameter("userName"));
						bean.setPassword(request.getParameter("password"));
						bean.setName(request.getParameter("name"));
						bean.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
						if (operation.equals("Edit User")) {
							bean.setRole(request.getParameter("role"));
						} else {
							bean.setRole("user");
						}

						UserModel.updateUser(bean);
						request.setAttribute("successmessage", "User profile edited");
						doGet(request, response);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No such User Found");
					doGet(request, response);
					e.printStackTrace();
				}
			}

		}
		else{
			request.setAttribute("operation", operation);
			doGet(request, response);
		}
	}
}
