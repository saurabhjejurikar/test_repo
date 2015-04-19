package dbopertions;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import bean.DoctorIdBean;
public class DoctorId {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/doctordb";

	//  Database credentials
	static final String USER = "aayush";
	static final String PASS = "aayush";
	public static boolean add(String engName, String chinese, String contactNo, String url) {
		Connection conn = null;
		Statement stmt = null;
		boolean isSet=false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "INSERT INTO doctorId(EnglishName, ChineseName,Telephone, Url) " +
					"VALUES  ('"+engName+ "', '"+chinese+ "', '"+contactNo+ "', '"+url+ "')";
			if (stmt.executeUpdate(sql)!=0)
				isSet=true;
			
		}catch(SQLException se){
			//Handle errors for JDBC
			System.out.println(se.getMessage());
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		return isSet;

	}//end main  

	public static Set<DoctorIdBean> missedUrl() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Set<DoctorIdBean> set = new HashSet<DoctorIdBean>() ;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select * from doctorId where EnglishName  NOT IN (Select EnglishName from doctorDetails)";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				String eng= rs.getNString("EnglishName");
				String chn= rs.getNString("ChineseName");
				String tel= rs.getNString("Telephone");
				String url= rs.getNString("Url");
				DoctorIdBean bean = new DoctorIdBean(eng, chn, tel, url);
				set.add(bean);
			}
			
		}catch(SQLException se){
			//Handle errors for JDBC
			System.out.println(se.getMessage());
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		return set;

	}//end main

}//end FirstExample