package com.markany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.markany.util.DBConnector;

public class SampleDao {
	
	public String selectDataResult(String param) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="";
	    
	    try {
	    	//DBConnector asdf = new DBConnector(); db커넥션의 경우 싱글톤디자인패턴사용하여, 외부에서 생성하지 못하게함 
	    	
	    	pstmt = DBConnector.getCon().prepareStatement("SELECT * FROM t_issue WHERE C_ISSUE_SQ = ?");
	    	pstmt.setString(1,param);
	    	
	    	rs = pstmt.executeQuery();
	    	
		    if (rs.next()) {
		    	result = rs.getString("C_REG_USER_ID");
		    }else{
		    	result = "NO RESULT";
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		    if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		    
		}
	    return result;
	    
	}
	
	
}
