package volunteer.service;

import volunteer.bean.Admin;

public class AdminService {
	
	//管理员登陆
	public boolean login(Admin admin) {
		if(admin.getAccount().equals(admin.getPassword()))
			return true;
		return false;
	}
	
	//修改密码
	public boolean alterPsw(Admin admin) {
		if(admin.getId().equals(admin.getPassword()))
			return true;
		return false;
	}
}
