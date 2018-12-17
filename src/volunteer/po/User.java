package volunteer.po;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Sex;
	private String No;
	private String Cclass;
	public String getCclass() {
		return Cclass;
	}
	public void setCclass(String cclass) 
	{
		Cclass = cclass;
	}
	private String Vno;
	private String Phone;
	public Set<ManHour> getManhours() {
		return manhours;
	}
	public void setManhours(Set<ManHour> manhours) {
		this.manhours = manhours;
	}
	private Set<ManHour> manhours;
	private Set<Apply> applies;
	public Set<Apply> getApplies() {
		return applies;
	}
	public void setApplies(Set<Apply> applies) {
		this.applies = applies;
	}
	public Set<ManHour> getManhour() {
		return manhours;
	}
	public void setManhour(Set<ManHour> manhours) {
		this.manhours = manhours;
	}
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
}
