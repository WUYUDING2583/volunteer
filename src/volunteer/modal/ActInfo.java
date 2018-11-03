package volunteer.modal;

import java.io.Serializable;

public class ActInfo implements Serializable{
	private String Ano;
	private String Adate;
	private String Aname;
	private String Address;
	private String Arequest;
	private String publishTime;
	private String picture;
	private int state;//是否已经上传工时表
	private String publishName;
	private String college;
	private String Astate;//活动状态
	private String Adeadline;//报名截止时间
	private double lng;//经度
	private double lat;//纬度
	
	public ActInfo() {}
	
	public void setLng(double lng) {this.lng=lng;}
	public void setLat(double lat) {this.lat=lat;}
	public void setAdeadline(String Adeadline) {this.Adeadline=Adeadline;}
	public void setAno(String Ano) {this.Ano=Ano;}
	public void setAdate(String Adate) {this.Adate=Adate;}
	public void setAname(String Aname) {this.Aname=Aname;}
	public void setAddress(String Address) {this.Address=Address;}
	public void setArequest(String Arequest) {this.Arequest=Arequest;}
	public void setPublishTime(String publishTime) {this.publishTime=publishTime;}
	public void setPicture(String picture) {this.picture=picture;}
	public void setState(int state) {this.state=state;}
	public void setPublishName(String publishName) {this.publishName=publishName;}
	public void setCollege(String college) {this.college=college;}
	public void setAstate(String Astate) {this.Astate=Astate;}
	
	public double getLng() {return lng;}
	public double getLat() {return lat;}
	public String getAno() {return Ano;}
	public String getAdate() {return Adate;}
	public String getAname() {return Aname;}
	public String getAddress() {return Address;}
	public String getArequest() {return Arequest;}
	public String getPublishTime() {return publishTime;}
	public String getPicture() {return picture;}
	public int getState() {return state;}
	public String getPublishName() {return publishName;}
	public String getCollege() {return college;}
	public String getAstate() {return Astate;}
	public String getAdeadline() {return Adeadline;}
}
