package volunteer.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import volunteer.po.Admin;
import volunteer.service.AdminService;
import volunteer.service.IActivityService;
import volunteer.service.IAdminService;

public class AdminAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	ServletContext application = ServletActionContext.getServletContext();
	HttpSession session = request.getSession();

	private Admin admin;
	private InputStream inputStream;
	private IAdminService adminservice;

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}

	public IAdminService getAdminservice() {
		return adminservice;
	}

	// 登陆
	public String login() {
		String account = admin.getAccount();
		String password = admin.getPassword();
		admin = adminservice.login(account, password);

		if (admin.getAccount().equals("wrong")) {
			this.addActionError("用户名或密码错误");
			return FAIL;
		} else {
			session.setAttribute("Admin", admin);
			return SUCCESS;
		}
	}

	// 修改密码
	public String alterPsw() throws UnsupportedEncodingException {

		// AdminService adminSer = new AdminService();
		String msg = "500";
		String id = admin.getId();
		String password = admin.getPassword();
		if (id.equals("") || password.equals("")) {
			msg = "400";
		} else if (adminservice.alterPsw(id, password)) {
			msg = "200";
		}
		inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
		return SUCCESS;
	}

	// 退出登录
	public String exit() {
		session.removeAttribute("Admin");
		return SUCCESS;
	}

	public Admin getAdmin() {
		return admin;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
