package ctl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import bean.InquiryBean;
import bean.UserBean;
import exception.DuplicateInquiryException;
import exception.NoRecordFoundException;
import model.InquiryModel;
import util.DataValidator;

@WebServlet(name = "InquiryCtl", urlPatterns = { "/ctl/InquiryCtl" })
public class InquiryCtl extends HttpServlet {
	protected boolean validate(HttpServletRequest request, String operation) {

		if (operation.equals("Submit Inquiry") || operation.equals("Remove Inquiry")
				|| operation.equals("Edit Inquiry")) {
			if (DataValidator.isNull(request.getParameter("name"))) {
				request.setAttribute("errormessage", "Please insert the Name");
				return false;
			} else if (DataValidator.isName(request.getParameter("name"))) {
				request.setAttribute("errormessage", "Enter Valid Name");
				return false;
			}
		}
		if (operation.equals("Submit Inquiry") || operation.equals("Edit Inquiry")) {
			if (DataValidator.isNull(request.getParameter("purpose"))) {
				request.setAttribute("errormessage", "Please insert in purpose field");
				return false;
			}
		}
		if (operation.equals("Submit Inquiry") || operation.equals("Edit Inquiry")) {
			if (DataValidator.isNull(request.getParameter("reference"))) {
				request.setAttribute("errormessage", "Please insert in reference field");
				return false;
			}
		}
		if (operation.equals("Submit Inquiry") || operation.equals("Edit Inquiry")) {
			if (DataValidator.isNull(request.getParameter("remarks"))) {
				request.setAttribute("errormessage", "Please enter remarks");
				return false;
			}
		}
		if (operation.equals("Submit Inquiry") || operation.equals("Remove Inquiry")
				|| operation.equals("Edit Inquiry")) {
			if (DataValidator.isNull(request.getParameter("contactNumber"))) {
				request.setAttribute("errormessage", "Please insert a contact number");
				return false;
			} else if (DataValidator.isMobile(request.getParameter("contactNumber"))) {
				request.setAttribute("errormessage", "Please insert a valid contact number");
				return false;
			}
		}
		return true;
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (id != null) {
			int id1 = Integer.parseInt(id);
			System.out.println("id1=" + id1);

			InquiryBean bean;
			try {
				bean = InquiryModel.findByPK(id1);
				request.setAttribute("id", id1);
				request.setAttribute("inquirybean", bean);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(id);
		HttpSession session = request.getSession(true);
		String op = (String) request.getParameter("operation");
		UserBean user = (UserBean) session.getAttribute("user");

		System.out.println(user.getName());
		System.out.println("in doGet InquiryCtl");
		request.setAttribute("user", user);

		request.setAttribute("operation", op);
		// String operation= (String) request.getAttribute("operation");

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/InquiryView.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("submit");
		if (validate(request, operation)) {
			System.out.println("in doPost InquiryCtl");
			// String id = request.getParameter("id");

			// request.setAttribute("id", id);
			String submit = request.getParameter("submit");
			HttpSession session = request.getSession(true);
			/*
			 * if (session == null) { // No session present, you can create
			 * yourself session = request.getSession(true); System.out.println(
			 * "Session alive"); }
			 */

			InquiryBean bean = new InquiryBean();
			UserBean user = (UserBean) session.getAttribute("user");
			System.out.println(user.getName());
			if (submit.equals("Submit Inquiry")) {
				bean.setName(request.getParameter("name"));
				bean.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
				bean.setPurpose(request.getParameter("purpose"));
				bean.setReference(request.getParameter("reference"));
				bean.setRemark(request.getParameter("remarks"));
				bean.setTime(new SimpleDateFormat("h:mm a").format(new Date()));
				bean.setDate(new SimpleDateFormat("dd/MM/yy").format(new Date()));
				bean.setAddBy(user.getName());
				System.out.println(bean.getAddBy());
				try {
					int inqNo = InquiryModel.addInquiry(bean);
					request.setAttribute("successmessage", "Inquiry added successfully");
					doGet(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateInquiryException e) {
					request.setAttribute("errormessage", "Inquiry already made");
					doGet(request, response);
					e.printStackTrace();
				}
			} else if (submit.equals("Remove Inquiry")) {
				String name = request.getParameter("name");
				long contactNumber = Long.parseLong(request.getParameter("contactNumber"));
				String deleter = user.getName();
				try {
					InquiryModel.deleteInquiry(name, contactNumber);
					request.setAttribute("successmessage", "Record deleted successfully");
					response.sendRedirect("/InquirySystem/InquiryListCtl");

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No records found for such Inquiry");
					doGet(request, response);
					e.printStackTrace();
				}
			} else if (submit.equals("Edit Inquiry")) {
				bean.setId(Integer.parseInt(request.getParameter("id")));
				bean.setName(request.getParameter("name"));
				bean.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
				bean.setPurpose(request.getParameter("purpose"));
				bean.setReference(request.getParameter("reference"));
				bean.setRemark(request.getParameter("remarks"));
				try {
					InquiryModel.updateInquiry(bean);
					request.setAttribute("successmessage", "Inquiry Edited successfully");

					RequestDispatcher rd = request.getRequestDispatcher("/jsp/InquiryView.jsp");
					rd.forward(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No updatable inquiry");
					doGet(request, response);
					e.printStackTrace();
				}

			}
		} else {
			doGet(request, response);
		}

	}
}