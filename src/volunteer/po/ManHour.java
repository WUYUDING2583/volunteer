package volunteer.po;

import java.io.Serializable;

public class ManHour implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManHour() {
		pk=new ManHourPK();
	}
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private ManHourPK pk;
	private float Avtime;
	public ManHour(String no,String aname, String adate, float Avtime) {
		super();
		this.pk=new ManHourPK(no,aname,adate);
		this.Avtime = Avtime;
	}
	public ManHourPK getPk() {
		return pk;
	}
	public void setPk(ManHourPK pk) {
		this.pk = pk;
	}
	
	public float getAvtime() {
		return Avtime;
	}
	public void setAvtime(float Avtime) {
		this.Avtime = Avtime;
	}

}
