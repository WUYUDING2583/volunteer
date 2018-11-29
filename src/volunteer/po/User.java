package volunteer.po;

import java.io.Serializable;

public class User implements Serializable{
	private String Name;
	private String Sex;
	private String No;
	private String Cclass;
	public String getCclass() {
		return Cclass;
	}
	public void setCclass(String cclass) {
		Cclass = cclass;
	}
	private String Vno;
	private String Phone;
	private String Ano;
	private String Atime;
	private String Ajobstate;
	private float vtime;
	private String Aname;//参加活动的名称
	private String Adate;//参加活动的日期
	private int leader;//领队
	private String college;//组织/学校
	private String openid;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public User() {}
	public User(String Name,String Sex,String No,String Cclass,String Vno,String Phone)
	{
		this.Name=Name;
		this.Sex=Sex;
		this.No=No;
		this.Cclass=Cclass;
		this.Vno=Vno;
		this.Phone=Phone;
	}
	public void setCollege(String college) {this.college=college;}
	public void setLeader(int leader) {this.leader=leader;}
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
	
	public void setPhone(String Phone) {this.Phone=Phone;}
	public String getCollege() {return college;}
	
	public int getLeader() {
		return leader;
	}
	public String getVno() {return Vno;}
	public String getName() {return Name;}
	public String getSex() {return Sex;}
	public String getNo() {return No;}
	
	public String getPhone() {return Phone;}
	public String getAno() {return Ano;}
	public String getAtime() {return Atime;}
	public String getAjobstate() {return Ajobstate;}
	public float getVtime() {return vtime;}
	public String getAname() {return Aname;}
	public String getAdate() {return Adate;}
}
