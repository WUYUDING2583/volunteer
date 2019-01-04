package volunteer.service;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import volunteer.dao.AdminDAO;
import volunteer.dao.IAdminDAO;
import volunteer.po.Admin;

public class AdminService implements IAdminService{
//	public AdminService() {
//	}
	
	private Admin admin;
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	private IAdminDAO dao;
	


	public IAdminDAO getDao() {
		return dao;
	}

	public void setDao(IAdminDAO dao) {
		this.dao = dao;
	}

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
		System.out.println(admin.getAccount());
		System.out.println("account:"+admin.getAccount());
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
