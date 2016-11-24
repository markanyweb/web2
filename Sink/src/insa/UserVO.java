package insa;

import java.util.StringTokenizer;

public class UserVO
{
  private String Emplyr_Id;
  private String User_Nm;
  private String Dept_Code;
  private String Emplyr_Sttus_Code;
  private String Emplyr_Type;
  private String Login_ip;

  public UserVO()
  {
  }

  public UserVO(String emplyr_Id, String user_Nm, String dept_Code, String emplyr_Sttus_Code, String emplyr_Type)
  {
    this.Emplyr_Id = emplyr_Id.trim();
    this.User_Nm = user_Nm.trim();
    this.Dept_Code = dept_check(dept_Code);
    this.Emplyr_Sttus_Code = type2_convert(emplyr_Sttus_Code);
  //  this.Emplyr_Sttus_Code = emplyr_Sttus_Code;
    this.Emplyr_Type = type_convert(emplyr_Type);
  }

  public String dept_check(String dept_code)
  {
    if ((dept_code == null) || ("".equals(dept_code))) {
      dept_code = "XXXX";
    }

    return dept_code;
  }

  public String type2_convert(String emplyr_Sttus_Code) {
    if ("정규".equals(emplyr_Sttus_Code)) {
      return "P";
    }
    return "F";
  }

  public String type_convert(String emplyr_Type)
  {
   
    if ("1".equals(emplyr_Type)) {
      return "EMPLY01";
    }
    return "EMPLY03";
  }

  public String ip_convert(String ip)
  {
    StringTokenizer st = new StringTokenizer(ip, ".");
    String new_ip = "";
    int i = 0;

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken();

      if (temp.indexOf("0", 0) == 0)
      {
        temp = temp.replaceFirst("0", "");
      }

      new_ip = new_ip + temp;
      if (i < 3) {
        new_ip = new_ip + ".";
      }
      i++;
    }

    return new_ip;
  }

  public String getEmplyr_Id()
  {
    return this.Emplyr_Id;
  }

  public void setEmplyr_Id(String emplyr_Id) {
    this.Emplyr_Id = emplyr_Id;
  }

  public String getUser_Nm() {
    return this.User_Nm;
  }

  public void setUser_Nm(String user_Nm) {
    this.User_Nm = user_Nm;
  }

  public String getDept_Code() {
    return this.Dept_Code;
  }

  public void setDept_Code(String dept_Code) {
    this.Dept_Code = dept_Code;
  }

  public String getEmplyr_Sttus_Code() {
    return this.Emplyr_Sttus_Code;
  }

  public void setEmplyr_Sttus_Code(String emplyr_Sttus_Code) {
    this.Emplyr_Sttus_Code = emplyr_Sttus_Code;
  }

  public String getEmplyr_Type() {
    return this.Emplyr_Type;
  }

  public void setEmplyr_Type(String emplyr_Type) {
    this.Emplyr_Type = emplyr_Type;
  }

  public String getLogin_ip() {
    return this.Login_ip;
  }

  public void setLogin_ip(String login_ip) {
    this.Login_ip = login_ip;
  }
}