package volunteer.modal;

import java.io.Serializable;

public class Vtime implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4208335001425955600L;
	private String No;
	private String Name;
	private String Aname;
	private String Adate;
	private float Avtime;
	private float Allvtime;
	
	public Vtime() {}
	
	public void setNo(String No) {this.No=No;}
	public void setName(String Name) {this.Name=Name;}
	public void setAname(String Aname) {this.Aname=Aname;}
	public void setAdate(String Adate) {this.Adate=Adate;}
	public void setAvtime(float Avtime) {this.Avtime=Avtime;}
	public void setAllvtime(float Allvtime) {this.Allvtime=Allvtime;}
	
	public String getNo() {return No;}
	public String getName() {return Name;}
	public String getAname() {return Aname;}
	public String getAdate() {return Adate;}
	public float getAvtime() {return Avtime;}
	public float getAllvtime() {return Allvtime;}
}
