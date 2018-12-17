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
	private User user;
	private ManHourPK pk;
	private float Avtime;
	public ManHour(String no,String aname, String adate, float avtime) {
		super();
		pk=new ManHourPK(no,aname,adate);
		Avtime = avtime;
	}
	public ManHourPK getPk() {
		return pk;
	}
	public void setPk(ManHourPK pk) {
		this.pk = pk;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getAvtime() {
		return Avtime;
	}
	public void setAvtime(float avtime) {
		Avtime = avtime;
	}

}
