package insa;

import java.util.StringTokenizer;

public class AuthVO
{
  private String Emplyr_Id;
  private String Ip_addr;

  public AuthVO()
  {
  }

  public AuthVO(String emplyr_Id, String ip_addr)
  {
    this.Emplyr_Id = emplyr_Id;
    this.Ip_addr = ip_convert(ip_addr);
  }

  public String ip_convert(String ip) {
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
  public String getEmplyr_Id() {
    return this.Emplyr_Id;
  }
  public void setEmplyr_Id(String emplyr_Id) {
    this.Emplyr_Id = emplyr_Id;
  }
  public String getIp_addr() {
    return this.Ip_addr;
  }
  public void setIp_addr(String ip_addr) {
    this.Ip_addr = ip_addr;
  }
}