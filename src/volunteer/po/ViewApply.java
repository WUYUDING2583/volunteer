package volunteer.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ViewApply() {
		// TODO 自动生成的构造函数存根
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getAdate() {
		return Adate;
	}
	public void setAdate(String adate) {
		Adate = adate;
	}
	public String getArequest() {
		return Arequest;
	}
	public void setArequest(String arequest) {
		Arequest = arequest;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public List<String> getAtime() {
		return Atime;
	}
	public void setAtime(List<String> atime) {
		Atime = atime;
	}
	private ViewApplyPK pk;
	public ViewApply(ViewApplyPK pk, String address, String adate, String arequest, long time,
			ArrayList<String> atime) {
		super();
		this.pk = pk;
		Address = address;
		Adate = adate;
		Arequest = arequest;
		this.time = time;
		Atime = atime;
	}
	public ViewApplyPK getPk() {
		return pk;
	}
	public void setPk(ViewApplyPK pk) {
		this.pk = pk;
	}
	private String Address;
	private String Adate;
	private String Arequest;
	private long time;
	private List<String>Atime;
}
