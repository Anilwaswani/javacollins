package model;

import java.sql.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import bean.InquiryBean;
import bean.UserBean;
import exception.DuplicateUserException;
import exception.DuplicateUserNameException;
import exception.NoRecordFoundException;

public class UserModel {
	public static Connection getConnected() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_iqs", "root", "root");
		return con;
	}

	public static boolean isDuplicateUser(UserBean bean) throws ClassNotFoundException, SQLException {
		System.out.println("inDuplicateUser method");
		Connection con = getConnected();
		PreparedStatement ps = con.prepareStatement("select * from user where username=? and pasword=? and name=? and mobileNo=? and role=?");
		ps.setString(1, bean.getUserName());
		ps.setString(2, bean.getPassword());
		ps.setString(3, bean.getName());
		ps.setLong(4, bean.getMobileNo());
		ps.setString(5, bean.getRole());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getString(2));
			return true;
		} else {
			return false;
		}
	}
	public static boolean isDuplicateUserName(UserBean bean) throws ClassNotFoundException, SQLException {
		System.out.println("inDuplicateUserName method");
		Connection con = getConnected();
		PreparedStatement ps = con.prepareStatement("select * from user where username=?");
		ps.setString(1, bean.getUserName());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		
			return true;
		} else {
			return false;
		}
	}

	public static void addUser(UserBean bean) throws ClassNotFoundException, SQLException, DuplicateUserException, DuplicateUserNameException {
		boolean flag = isDuplicateUser(bean);
		boolean existUserName=isDuplicateUserName(bean);
		if (flag == true) {
			System.out.println("User already exist");
			throw new DuplicateUserException("User already exists");
		} else if (existUserName == true) {
			System.out.println("UserName already exist");
			throw new DuplicateUserNameException("Please choose a different user name");
		}else if(flag == false & existUserName == false){
			int id;
			Connection con = getConnected();
			PreparedStatement ps1 = con.prepareStatement("Select max(id) from user");
			ResultSet rs1 = ps1.executeQuery();
			PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?,?)");
			rs1.next();
			id = rs1.getInt(1);
			ps.setInt(1, ++id);
			ps.setString(2, bean.getUserName());
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getName());
			ps.setLong(5, bean.getMobileNo());
			ps.setString(6, bean.getRole());
			ps.executeUpdate();
			con.close();
			System.out.println("user added successfully");
		}
	}

public static void deleteUser(String name,long mobileNo) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		Connection con = getConnected();
		PreparedStatement ps = con.prepareStatement("delete from user where name=? and mobileNo=?");
		ps.setString(1, name);
		ps.setLong(2, mobileNo);
		int deletants= ps.executeUpdate();
		if (deletants==0){
			throw new NoRecordFoundException("No Such user exists");
		}
		System.out.println(deletants+ "Records deleted successfully");
		con.close();
	}
	public static void updateUser(UserBean bean) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		Connection con = getConnected();
		
		System.out.println(con);
		PreparedStatement ps1= con.prepareStatement("update user set name=?,mobileNo=?, role=? where userName=? and pasword=?");
		ps1.setString(1, bean.getName());
		ps1.setLong(2, bean.getMobileNo());
		
			System.out.println(bean.getRole());
		ps1.setString(3, bean.getRole());
	ps1.setString(4, bean.getUserName());
		ps1.setString(5, bean.getPassword());
		int update=ps1.executeUpdate();
		if (update==0){
			throw new NoRecordFoundException("No Such User found");
		}
		System.out.println("User updated successfully");
		con.close();
	}

	public static UserBean findByPK(int id) throws ClassNotFoundException, SQLException {
		UserBean bean = new UserBean();
		Connection con = getConnected();
		System.out.println(con);
		PreparedStatement ps = con.prepareStatement("select * from user where id=?");
		ps.setInt(1, id);
		//ps.setString(1, userName);
		//ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean.setId(rs.getInt(1));
			bean.setUserName(rs.getString(2));
			bean.setPassword(rs.getString(3));
			bean.setName(rs.getString(4));
			bean.setMobileNo(rs.getLong(5));
			bean.setRole(rs.getString(6));
		}
		con.close();
		return bean;
	}
	public static List getUserList() throws ClassNotFoundException, SQLException, NoRecordFoundException {
		
		List list =new LinkedList();
		StringBuffer sb= new StringBuffer("select * from user where 1=1");
	
		String s=sb.toString();
		Connection con = getConnected();
		System.out.println(con);
		PreparedStatement ps = con.prepareStatement(s);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			UserBean bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setUserName(rs.getString(2));
			bean.setPassword(rs.getString(3));
			bean.setName(rs.getString(4));
			bean.setMobileNo(rs.getLong(5));
			bean.setRole(rs.getString(6));
			list.add(bean);
		}
		if (list.isEmpty()){
			throw new NoRecordFoundException("No User Found");
		}
		con.close();
		return list;
	}

	public static UserBean isTrueUser(String userName, String password) throws ClassNotFoundException, SQLException {
		UserBean bean = new UserBean();

		Connection conn = getConnected();
		PreparedStatement ps = conn.prepareStatement("select * from user where userName=?and pasword= ?");
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			bean.setId(rs.getInt(1));
			bean.setUserName(rs.getString(2));
			bean.setPassword(rs.getString(3));
			bean.setName(rs.getString(4));
			bean.setMobileNo(rs.getLong(5));
			bean.setRole(rs.getString(6));
			
			System.out.println(bean.getName());
			return bean;
		} else {
			return null;
		}
	}

	public static List getUserListbyName(String name) throws SQLException, ClassNotFoundException, NoRecordFoundException {
		List list =new LinkedList(); 
		  Connection con = getConnected();
		   
		  PreparedStatement ps = con.prepareStatement("select * from user where name like ?");
		  ps.setString(1,"%"+name+"%");
		  ResultSet rs = ps.executeQuery(); 
		  while (rs.next()) {
			  UserBean bean = new UserBean();
			  
		  bean.setId(rs.getInt(1));
		  bean.setUserName(rs.getString(2));
		  bean.setPassword(rs.getString(3));
		  bean.setName(rs.getString(4));
		  bean.setMobileNo(rs.getLong(5));
		  bean.setRole(rs.getString(6));
		 list.add(bean);
		  }
		  if(list.isEmpty()){
			  throw new NoRecordFoundException("No User Found with this name");
		  }
		 con.close();
		  return list; 
		 }

	public static List getUserListbyMobile(String mobileNumber) throws ClassNotFoundException, SQLException, NoRecordFoundException {
		List list =new LinkedList(); 
		  Connection con = getConnected();
		   
		  PreparedStatement ps = con.prepareStatement("select * from user where mobileNo like ?");
		  ps.setString(1,"%"+ mobileNumber + "%");
		  ResultSet rs = ps.executeQuery(); 
		  while (rs.next()) {
			  UserBean bean = new UserBean();
			  
		  bean.setId(rs.getInt(1));
		  bean.setUserName(rs.getString(2));
		  bean.setPassword(rs.getString(3));
		  bean.setName(rs.getString(4));
		  bean.setMobileNo(rs.getLong(5));
		  bean.setRole(rs.getString(6));
		 list.add(bean);
		  }
		  if(list.isEmpty()){
			  throw new NoRecordFoundException("No user found with this mobile number");
		  }
		 con.close();
		  return list; 
			}
}
