package dbopertions;

import java.sql.*;
public class DoctorDetails {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/doctordb";

	//  Database credentials
	static final String USER = "aayush";
	static final String PASS = "aayush";
	
	public static boolean recordExists( String engName, String chineseName) throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
	    // select the number of rows in the table
	    Statement stmt = null;
	    ResultSet rs = null;
	    int rowCount = -1;
	    try {
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT COUNT(*) FROM doctorDetails WHERE EnglishName= '" + engName+ "' and ChineseName='"+ chineseName +"'");
	      // get the number of rows from the result set
	      rs.next();
	      rowCount = rs.getInt(1);
	    } finally {
	      try {
			rs.close();
			  stmt.close();
			  conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    if (rowCount==1){
	    	return true;
	    }
	    return false;
	  }
	
	
	public static boolean add(String engName, String chinese, String genderStr, String emailStr,
			String qualificationsStr, String registeredQualStr,
			String districtStr, String practiceTypeStr, String consultHrsStr,
			String medicalServiceAvailStr, String othermedicalServiceStr,
			String emergencyStr, String feesStr, String languageStr,
			String afflHospitalStr, String telephoneStr, String faxStr,
			String mobStr, String emailStr2){
		Connection conn = null;
		Statement stmt = null;
		boolean isSet=false;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "INSERT INTO doctorDetails(EnglishName,"
					+ "ChineseName,"
					+ "Gender,"
					+ "Qualifications,"
					+ "RegsteredQualifications, "
					+ "District, "
					+ "TypeofPractice,"
					+ "ConsultationHours,"
					+ "MedicalServiceAvailable, "
					+ "OtherMedicalServiceAvailable,"
					+ "EmergencyServiceAvailable,"
					+ "ConsultationFee,"
					+ "Language,"
					+ "AffiliatedHosp,"
					+ "Telephone,"
					+ "Fax,"
					+ "Mobile,"
					+ "Email"
					+ ") " +
					"VALUES  ('"+engName+ "', '"+chinese+ "', '"+genderStr+ "','"+qualificationsStr+ "','"+registeredQualStr	+ "','"+districtStr+ "','"+practiceTypeStr+ "','"+consultHrsStr+ "','"+medicalServiceAvailStr+ "','"+othermedicalServiceStr+ "','"+emergencyStr+ "','"+feesStr+ "','"+languageStr+ "','"+afflHospitalStr+ "','"+telephoneStr+ "','"+faxStr+ "','"+mobStr+ "','"+emailStr+ "')";
			
			System.out.println(sql);
			if (stmt.executeUpdate(sql)!=0)
				isSet=true;
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
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



}//end FirstExample