package model;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import bean.InquiryBean;
import bean.UserBean;
import exception.DuplicateInquiryException;
import exception.NoRecordFoundException;

public class InquiryModel {
	public static Connection getConnected() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_iqs", "root", "root");
		return con;
	}

	public static int addInquiry(InquiryBean bean) throws ClassNotFoundException, SQLException, DuplicateInquiryException {
		boolean flag = isDuplicateInquiry(bean);
		int id = 0;
		if (flag == true) {
			System.out.println("inquiry exists");
			throw new DuplicateInquiryException("Inquiry already made");
			
		} else {

			Connection con = getConnected();
			PreparedStatement ps1 = con.prepareStatement("Select max(id) from iqs_inquiry");
			ResultSet rs1 = ps1.executeQuery();
			PreparedStatement ps = con.prepareStatement("insert into iqs_inquiry values(?,?,?,?,?,?,?,?,?)");
			rs1.next();
			id = rs1.getInt(1);
			ps.setInt(1, ++id);
			ps.setString(2, bean.getName());
			ps.setLong(3, bean.getContactNumber());
			ps.setString(4, bean.getPurpose());
			ps.setString(5, bean.getTime());
			ps.setString(6, bean.getReference());
			ps.setString(7, bean.getRemark());
			ps.setString(8, bean.getAddBy());
			ps.setString(9, bean.getDate());
			ps.executeUpdate();
			con.close();

		}
		return id;
	}

	public static boolean isDuplicateInquiry(InquiryBean bean) throws ClassNotFoundException, SQLException {
		System.out.println("inDuplicateInquiry method");
		Connection con = getConnected();
		PreparedStatement ps = con.prepareStatement("select * from iqs_inquiry where name=? and contactno=? and purpose=? and reference=?");
		ps.setString(1, bean.getName());
		ps.setLong(2, bean.getContactNumber());
		ps.setString(3, bean.getPurpose());
		ps.setString(4, bean.getReference());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			return true;
		} else {
			return false;
		}
	}

	public static void deleteInquiry(String name, long contactNumber) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		Connection con = getConnected();
		System.out.println(con);
		
		PreparedStatement ps = con.prepareStatement("delete from iqs_inquiry where name=? and contactno=?");
		ps.setString(1,name);
		ps.setLong(2, contactNumber);
		int deletedInq = ps.executeUpdate();
		System.out.println(deletedInq + "Records deleted successfully");
		if(deletedInq==0){
			throw new NoRecordFoundException("No Inquiry Found");
		
		}
	
		
		con.close();
	}

	public static void updateInquiry(InquiryBean bean) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		Connection con = getConnected();
		System.out.println(con);
		
		PreparedStatement ps = con.prepareStatement("update iqs_inquiry set name=?,contactno=?, purpose=?, reference=?, remark=? where id=?");
		ps.setString(1, bean.getName());
		ps.setLong(2, bean.getContactNumber());
		ps.setString(3, bean.getPurpose());
		
		ps.setString(4, bean.getReference());
		ps.setString(5, bean.getRemark());
		ps.setInt(6, bean.getId());
		int i = ps.executeUpdate();
		System.out.println(i + "Records updated successfully");
		if (i==0){
			throw new NoRecordFoundException("No Updatable Inquiry");
			
		}
		con.close();
	}
	public static InquiryBean findByPK(int id) throws ClassNotFoundException, SQLException {
		
		Connection con = getConnected();
		System.out.println(con);
		PreparedStatement ps = con.prepareStatement("select * from iqs_inquiry where id =?");

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
InquiryBean bean=new InquiryBean();

while (rs.next()) {
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setContactNumber(rs.getLong(3));
			bean.setPurpose(rs.getString(4));
			bean.setTime(rs.getString(5));
			bean.setReference(rs.getString(6));
			bean.setRemark(rs.getString(7));
			bean.setAddBy(rs.getString(8));
			bean.setDate(rs.getString(9));
			
		}
		con.close();
		return bean;
	}


	public static String getInquiry(int id, String user) throws ClassNotFoundException, SQLException {
		String inqStatus = null;
		Connection con = getConnected();
		System.out.println(con);
		PreparedStatement ps = con.prepareStatement("select * from iqs_inquiry where id =? and user=?");

		ps.setInt(1, id);
		ps.setString(2, user);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			inqStatus = rs.getString(6);
		}
		con.close();
		return inqStatus;
	}

	
	  public static List getInquiryList() throws ClassNotFoundException, SQLException, NoRecordFoundException {
	  
	  List list =new LinkedList(); 
	  StringBuffer sb= new StringBuffer("select * from iqs_inquiry where 1=1");
	  
	  String s=sb.toString(); 
	  Connection con = getConnected();
	  System.out.println(con); 
	  PreparedStatement ps = con.prepareStatement(s);
	  ResultSet rs = ps.executeQuery(); 
	  while (rs.next()) {
		  InquiryBean bean = new InquiryBean();
	  bean.setId(rs.getInt(1));
	  bean.setName(rs.getString(2)); 
	  bean.setContactNumber(rs.getLong(3));
	  bean.setPurpose(rs.getString(4));
	  bean.setTime(rs.getString(5));
	  bean.setReference(rs.getString(6)); 
	  bean.setRemark(rs.getString(7));
	  bean.setAddBy(rs.getString(8));
	 bean.setDate(rs.getString(9));
	 list.add(bean);
	  }
	  if(list.isEmpty()){
		  throw new NoRecordFoundException("No Inquiries found");
	  }
	 con.close();
	  return list; 
	  }  
	  public static List getInqListbyDate(String date) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		  
		  List list =new LinkedList(); 
		  Connection con = getConnected();
		   
		  PreparedStatement ps = con.prepareStatement("select * from iqs_inquiry where date like ?");
		  ps.setString(1,"%"+date+"%");
		  ResultSet rs = ps.executeQuery(); 
		  while (rs.next()) {
			  InquiryBean bean = new InquiryBean();
		  bean.setId(rs.getInt(1));
		  bean.setName(rs.getString(2)); 
		  bean.setContactNumber(rs.getLong(3));
		  bean.setPurpose(rs.getString(4));
		  bean.setTime(rs.getString(5));
		  bean.setReference(rs.getString(6)); 
		  bean.setRemark(rs.getString(7));
		  bean.setAddBy(rs.getString(8));
		 bean.setDate(rs.getString(9));
		 list.add(bean);
		  }
		  if(list.isEmpty()){
			  throw new NoRecordFoundException("No Inquiries found");
		  }
		 con.close();
		  return list; 
		  }
public static List getInqListbyName(String name) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		  
		  List list =new LinkedList(); 
		 Connection con = getConnected();
		  System.out.println(con); 
		  PreparedStatement ps = con.prepareStatement("select * from iqs_inquiry where name like ?");
		  ps.setString(1,"%"+name+"%");
		  ResultSet rs = ps.executeQuery(); 
		  while (rs.next()) {
			  InquiryBean bean = new InquiryBean();
		  bean.setId(rs.getInt(1));
		  System.out.println(bean.getId());
		  bean.setName(rs.getString(2)); 
		  bean.setContactNumber(rs.getLong(3));
		  bean.setPurpose(rs.getString(4));
		  bean.setTime(rs.getString(5));
		  bean.setReference(rs.getString(6)); 
		  bean.setRemark(rs.getString(7));
		  bean.setAddBy(rs.getString(8));
		 bean.setDate(rs.getString(9));
		 list.add(bean);
		  }
		  if(list.isEmpty()){
			  throw new NoRecordFoundException("No Inquiries found");
		  }
		 con.close();
		  return list; 
		  }
}