package volunteer.po;

import java.io.Serializable;

public class ManHourPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManHourPK() {
		// TODO 自动生成的构造函数存根
	}
	private String No;
	private String Aname;
	private String Adate;
	public ManHourPK(String no, String aname, String adate) {
		super();
		No = no;
		Aname = aname;
		Adate = adate;
	}
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getAdate() {
		return Adate;
	}
	public void setAdate(String adate) {
		Adate = adate;
	}
}
