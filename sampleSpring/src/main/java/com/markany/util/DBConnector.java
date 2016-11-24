package com.markany.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
    
    private static Connection con; //프로그램에서 공동으로 사용할수있게 static으로 선언
    
    public static Connection getCon() { // con 객체는 private로 선언되어 있기 때문에, 직접 가져가지 못함 
		return con;
	}

	private DBConnector(){ //외부에서 생성하지 못하게 생성자 private으로 지정       
    }
    
    public static Connection getConnection(){
        
      
        try {

        	  if(con!=null){ //con이 null이 아니면 con이 참조하고있는 connection인스턴스를 리턴
        		  return con;
              }
        	
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.78:3306/pms","pms", "pms");
            
            System.out.println("connection ok");
            
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }catch(SQLException e){
        	e.printStackTrace();
        }
        
        return con;
    }
    
    public static void closeConnection(){
    	if (con != null){ 
    		try { 
    			con.close();
    			System.out.println("close connection ok");
    		} catch(SQLException ex) {
    			ex.printStackTrace();}
    	}
    }
    
    
}

