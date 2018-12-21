package volunteer.po;

import java.io.Serializable;

public class Admin  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;
	private String password;
	private String college;
	private String id;
	private String org;//组织
	
	public Admin() {}
	
	public String getOrg() {return org;}
	public String getId() {return id;}
	public String getAccount() {return account;}
	public String getPassword() {return password;}
	public String getCollege() {return college;}
	
	public void setOrg(String org) {this.org=org;}
	public void setId(String id) {this.id=id;}
	public void setAccount(String account) {this.account=account;}
	public void setPassword(String password) {this.password=password;}
	public void setCollege(String college) {this.college=college;}
}
