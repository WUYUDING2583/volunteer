package volunteer.service;

import volunteer.dao.AdminDAO;
import volunteer.po.Admin;

public class AdminService {
	
	private Admin admin=new Admin();
	private AdminDAO dao=new AdminDAO();
	
	/*
	 * @管理员登陆
	 * 传入参数 account 用户名
	 * 		 password 密码
	 * 用户名密码匹配成功
	 * 返回方法内格式数据
	 * 匹配失败
	 * admin.setAccount("wrong");
	 */
	public Admin login(String account,String password) {
		admin=dao.login(account,password);
		return admin;
	}
	
	/*
	 * @修改密码
	 * 传入参数 id 身份证号
	 * 		 password 修改后的密码
	 * 修改成功返回true
	 * 修改失败返回false
	*/
	public boolean alterPsw(String id,String password) {
		if(dao.alterPsw(id,password).equals("success"))
		return true;
		else return false;
	}
}
