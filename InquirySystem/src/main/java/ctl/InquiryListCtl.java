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
import util.DataValidator;
@WebServlet(name = "InquiryListCtl", urlPatterns = { "/ctl/InquiryListCtl" })
/**
 * Servlet implementation class InquiryListCtl
 */
public class InquiryListCtl extends HttpServlet {
	protected boolean validate(HttpServletRequest request, String operation) throws ParseException {
		if(operation.equals("SearchByName")){
		 if (DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("errormessage", "Enter Valid Name");
		return false;
		}
		}
		if(operation.equals("SearchByDate")){
		 if (DataValidator.isDate(request.getParameter("date"))) {
				request.setAttribute("errormessage", "Enter Valid Date");
		return false;
			}
			}
			
		return true;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InquiryModel model = new InquiryModel();
		try {
			List list = model.getInquiryList();
			System.out.println(list);
			request.setAttribute("inquiryList", list);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/InquiryListView.jsp");
			rd.forward(request, response);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoRecordFoundException e) {
			request.setAttribute("errormessage", "No Inquiries found");
			
			e.printStackTrace();
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation= request.getParameter("submit");
		try {
			if (validate(request,operation)){
			System.out.println("In doPost InquiryListCtl");
			String submit = request.getParameter("submit");
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			 
			if (submit.equals("SearchByDate")) {
				try {
			List list=	InquiryModel.getInqListbyDate(date);
					request.setAttribute("inquiryList", list);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/InquiryListView.jsp");
					rd.forward(request, response);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No inquiries found");
					doGet(request, response);
					e.printStackTrace();
				}
			}
			if (submit.equals("SearchByName")) {
				try {
					System.out.println(name);
					List list= InquiryModel.getInqListbyName(name);
					request.setAttribute("inquiryList", list);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/InquiryListView.jsp");
					rd.forward(request, response);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoRecordFoundException e) {
					request.setAttribute("errormessage", "No inquiries found");
					doGet(request, response);
					e.printStackTrace();
				}
			}
			}else{
				doGet(request, response);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
