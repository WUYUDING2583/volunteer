package volunteer.action;

import volunteer.bean.Admin;
import volunteer.service.AdminService;

public class AdminAction {
	private Admin admin;
	public Admin getAdmin() {return admin;}
	public void setAdmin(Admin admin) {this.admin=admin;}
	//登陆
	public String excute() {
		AdminService adminSer=new AdminService();
		if(adminSer.login(admin))
			return "loginSuccess";
		return "loginFail";
	}
}
