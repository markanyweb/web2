package insa;

public class DeptVO
{
  private String Dept_Code;
  private String Dept_Nm;
  private String Upper_Dept_Code;

  public DeptVO()
  {
  }

  public DeptVO(String dept_Code, String dept_Nm, String upper_Dept_Code)
  {
    this.Dept_Code = dept_Code;
    this.Dept_Nm = dept_Nm;
    this.Upper_Dept_Code = upper_Dept_Code;
  }
  public String getDept_Code() {
    return this.Dept_Code;
  }
  public void setDept_Code(String dept_Code) {
    this.Dept_Code = dept_Code;
  }
  public String getDept_Nm() {
    return this.Dept_Nm;
  }
  public void setDept_Nm(String dept_Nm) {
    this.Dept_Nm = dept_Nm;
  }
  public String getUpper_Dept_Code() {
    return this.Upper_Dept_Code;
  }
  public void setUpper_Dept_Code(String upper_Dept_Code) {
    this.Upper_Dept_Code = upper_Dept_Code;
  }
}