package volunteer.po;

import java.io.Serializable;

public class ManHour implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManHour() {
		// TODO 自动生成的构造函数存根
	}
	private String No;
	private String Aname;
	private String Adate;
	private float Avtime;
	public ManHour(String no, String aname, String adate, float avtime) {
		super();
		No = no;
		Aname = aname;
		Adate = adate;
		Avtime = avtime;
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
	public float getAvtime() {
		return Avtime;
	}
	public void setAvtime(float avtime) {
		Avtime = avtime;
	}

}
