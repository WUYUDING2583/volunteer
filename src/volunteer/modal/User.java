package volunteer.modal;

import java.io.Serializable;

public class User implements Serializable{
	private String Name;
	private String Sex;
	private String No;
	private String Class;
	private String Vno;
	private String Phone;
	private String Ano;
	private String Atime;
	private String Ajobstate;
	private float vtime;
	private String Aname;//参加活动的名称
	private String Adate;//参加活动的日期
	private boolean leader;//领队
	private double lng;
	private double lat;
	private String college;//组织/学校
	private String signIn;//是否签到
	private String signOut;//是否签退
	public User() {}

	public void setSignOut(String signOut) {this.signOut=signOut;}
	public void setSignIn(String signIn) {this.signIn=signIn;}
	public void setCollege(String college) {this.college=college;}
	public void setLeader(boolean leader) {this.leader=leader;}
	public void setLng(double lng) {this.lng=lng;}
	public void setLat(double lat) {this.lat=lat;}
	public void setAname(String Aname) {this.Aname=Aname;}
	public void setAdate(String Adate) {this.Adate=Adate;}
	public void setVtime(float vtime) {this.vtime=vtime;}
	public void setAno(String Ano) {this.Ano =Ano;}
	public void setAtime(String Atime) {this.Atime=Atime;}
	public void setAjobstate(String Ajobstate) {this.Ajobstate =Ajobstate;}
	public void setVno(String Vno) {this.Vno=Vno;}
	public void setName(String Name) {this.Name=Name;}
	public void setSex(String Sex) {this.Sex=Sex;}
	public void setNo(String No) {this.No=No;}
	public void setClass(String Class) {this.Class=Class;}
	public void setPhone(String Phone) {this.Phone=Phone;}

	public String getSignOut() {return signOut;}
	public String getSignIn() {return signIn;}
	public String getCollege() {return college;}
	public boolean isLeader() {return leader;}
	public double getLng() {return lng;}
	public double getLat() {return lat;}
	public String getVno() {return Vno;}
	public String getName() {return Name;}
	public String getSex() {return Sex;}
	public String getNo() {return No;}
	public String getCclass() {return Class;}
	public String getPhone() {return Phone;}
	public String getAno() {return Ano;}
	public String getAtime() {return Atime;}
	public String getAjobstate() {return Ajobstate;}
	public float getVtime() {return vtime;}
	public String getAname() {return Aname;}
	public String getAdate() {return Adate;}
}
