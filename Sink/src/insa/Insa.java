package insa;

import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Insa
{
  public static void main(String[] ar)
  {
    Connection conn = null;
    Connection conn1 = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    String db_address = "";
    String db_name = "";
    String db_pass = "";

    String query = "";
    try
    {
      Class.forName("com.mysql.jdbc.Driver");

     db_address = "jdbc:mysql://10.10.1.24:3306/ALDER";
      db_name = "wdrmprod01";
      db_pass = "hkeldkfdpa";
      /*db_address = "jdbc:mysql://192.168.1.78:3306/pms";
      db_name = "markany";
      db_pass = "akzmdosl";*/
      conn = DriverManager.getConnection(db_address, db_name, db_pass);

    //  query = "SELECT EMPLYR_ID, USER_NM, DEPT_CODE, EMPLYR_TYPE , EMPLYR_STTUS_CODE FROM COMTNEMPLYRINFO";
      query = "SELECT USER_ID as EMPLYR_ID , USER_NAME as USER_NM, USER_DEPT as DEPT_CODE ,  USER_CUSTOM04 as EMPLYR_STTUS_CODE , USER_STATUS as EMPLYR_TYPE FROM USER_VIEW ";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);

      ArrayList userList = new ArrayList();

      while (rs.next()) {
        userList.add(new UserVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
      }

      rs.close();

      query = "SELECT CODE as DEPT_CODE, NAME as DEPT_NM, P_CODE as UPPER_DEPT_CODE FROM DEPT_VIEW";

      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);

      ArrayList deptList = new ArrayList();

      while (rs.next()) {
        deptList.add(new DeptVO(rs.getString(1), rs.getString(2), rs.getString(3)));
      }

      query = "SELECT USER_ID as EMPLYR_ID ,USER_AUTHALLOWIP as IP_ADDR   FROM USER_VIEW";

      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);

      ArrayList AuthList = new ArrayList();

      while (rs.next()) {
        AuthList.add(new AuthVO(rs.getString(1), rs.getString(2)));
      }

      rs.close();
      stmt.close();
      conn.close();
      System.out.println("MYSQL");

      Class.forName("oracle.jdbc.OracleDriver");

      db_address = "jdbc:oracle:thin:@";
      String db_ip= new PropertyManager().getValue("db.ip");
      String db_port = new PropertyManager().getValue("db.port");
      String db_sid = new PropertyManager().getValue("db.sid");
      db_name= new PropertyManager().getValue("db.id");
      db_pass = new PropertyManager().getValue("Globals.Password","pwd");
      db_address = db_address+db_ip+":"+db_port+":"+db_sid;
      conn1 = DriverManager.getConnection(db_address, db_name, db_pass);

      query = "delete COMTNEMPLYRINFO_TEMP_TEST";
      stmt = conn1.createStatement();
      rs = stmt.executeQuery(query);
      rs.close();

      query = "delete USR_DEPT_T_TEMP_TEST";
      stmt = conn1.createStatement();
      rs = stmt.executeQuery(query);
      rs.close();

      query = "delete REG_INFO_TEST";
      stmt = conn1.createStatement();
      rs = stmt.executeQuery(query);
      rs.close();

      System.out.println("ORACLE delete OK");

      query = "INSERT INTO COMTNEMPLYRINFO_TEMP_TEST(Emplyr_Id,User_Nm,Dept_Code,Emplyr_Sttus_Code,Emplyr_Type)VALUES(?,?,?,?,?)";
      pstmt = conn1.prepareStatement(query);

      for (int i = 0; i < userList.size(); i++) {
        UserVO uvo = new UserVO();
        uvo = (UserVO)userList.get(i);

        pstmt.setString(1, uvo.getEmplyr_Id());
        pstmt.setString(2, uvo.getUser_Nm());
        pstmt.setString(3, uvo.getDept_Code());
        pstmt.setString(4, uvo.getEmplyr_Sttus_Code());
        pstmt.setString(5, uvo.getEmplyr_Type());

        pstmt.executeUpdate();
      }

      query = "INSERT INTO USR_DEPT_T_TEMP_TEST(Dept_Code,Dept_Nm,UPPER_DEPT_CODE)VALUES(?,?,?)";
      pstmt = conn1.prepareStatement(query);

      for (int i = 0; i < deptList.size(); i++) {
        DeptVO dvo = new DeptVO();
        dvo = (DeptVO)deptList.get(i);

        pstmt.setString(1, dvo.getDept_Code());
        pstmt.setString(2, dvo.getDept_Nm());
        pstmt.setString(3, dvo.getUpper_Dept_Code());
        pstmt.executeUpdate();
      }

      query = "INSERT INTO REG_INFO_TEST (EMPLYR_ID,IP_ADDR)VALUES(?,?)";
      pstmt = conn1.prepareStatement(query);

      for (int i = 0; i < AuthList.size(); i++) {
        AuthVO aVO = new AuthVO();
        aVO = (AuthVO)AuthList.get(i);

        pstmt.setString(1, aVO.getEmplyr_Id());
        pstmt.setString(2, aVO.getIp_addr());

        pstmt.executeUpdate();
      }

      System.out.println("ORACLE INSERT OK");

      cs = conn1.prepareCall("{call USR_SYNC_USER_TEST()}");

      cs.execute();
      cs.close();
      cs = null;

      cs = conn1.prepareCall("{call USR_SYNC_DEPT_TEST()}");

      cs.execute();
    }
    catch (Exception e)
    {
      e.printStackTrace();

      if (cs != null) try { cs.close(); } catch (Exception localException1) {
        } if (rs != null) try { rs.close(); } catch (Exception localException2) {
        } if (pstmt != null) try { pstmt.close(); } catch (Exception localException3) {
        } if (stmt != null) try { stmt.close(); } catch (Exception localException4) {
        } if (conn != null) try { conn.close(); } catch (Exception localException5) {
        } if (conn1 != null) try { conn1.close();
        }
        catch (Exception localException6)
        {
        }


      if (cs != null) try { cs.close(); } catch (Exception localException7) {
        } if (rs != null) try { rs.close(); } catch (Exception localException8) {
        } if (pstmt != null) try { pstmt.close(); } catch (Exception localException9) {
        } if (stmt != null) try { stmt.close(); } catch (Exception localException10) {
        } if (conn != null) try { conn.close(); } catch (Exception localException11) {
        } if (conn1 != null) try { conn1.close();
        }
        catch (Exception localException12)
        {
        }
    }
    finally
    {
      if (cs != null) try { cs.close(); } catch (Exception localException13) {
        } if (rs != null) try { rs.close(); } catch (Exception localException14) {
        } if (pstmt != null) try { pstmt.close(); } catch (Exception localException15) {
        } if (stmt != null) try { stmt.close(); } catch (Exception localException16) {
        } if (conn != null) try { conn.close(); } catch (Exception localException17) {
        } if (conn1 != null) try { conn1.close();
        } catch (Exception localException18)
        {
        }
    }
    System.out.println("End");
  }
}