package volunteer.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import volunteer.po.Admin;
import volunteer.service.AdminService;

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

	// 登陆
	public String login() {
		AdminService adminSer = new AdminService();
		String account=admin.getAccount();
		String password=admin.getPassword();
		admin=adminSer.login(account, password);
		if(admin.getAccount().equals("wrong")) {
			session.setAttribute("wrongMsg", "用户名或密码错误");
			return FAIL;
		}
		else {
			session.setAttribute("Admin", admin);
			return SUCCESS;
		}
	}

	// 登陆校验
	public void validateLogin() {
		String account = admin.getAccount();
		String pwd = admin.getPassword();
		if (account == null || account.equals("")) {
			session.setAttribute("wrongMsg", "用户名不能为空");
			this.addFieldError("loginUser.account", "用户名不能为空");
		}
		if (pwd == null || pwd.equals("")) {
			session.setAttribute("wrongMsg", "密码不能为空");
			this.addFieldError("loginUser.account", "密码不能为空");
		}
	}

	// 修改密码
	public String alterPsw() throws UnsupportedEncodingException {
		AdminService adminSer = new AdminService();
		String msg = "500";
		String id=admin.getId();
		String password=admin.getPassword();
		if (adminSer.alterPsw(id, password)) {
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

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
