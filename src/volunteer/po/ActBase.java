package volunteer.po;

public class ActBase {

	public ActBase() {
		// TODO 自动生成的构造函数存根
	}
	private String Ano;
	private String Adate;
	private String Aname;
	private String Address;
	private String Arequest;
	private String publishTime;
	private String picture;
	private String Adeadline;//报名截止时间
	private String Astate;//活动状态
	public String getAstate() {
		return Astate;
	}
	public void setAstate(String astate) {
		Astate = astate;
	}
	public String getAno() {
		return Ano;
	}
	public void setAno(String ano) {
		Ano = ano;
	}
	public String getAdate() {
		return Adate;
	}
	public void setAdate(String adate) {
		Adate = adate;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getArequest() {
		return Arequest;
	}
	public void setArequest(String arequest) {
		Arequest = arequest;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAdeadline() {
		return Adeadline;
	}
	public void setAdeadline(String adeadline) {
		Adeadline = adeadline;
	}
}
