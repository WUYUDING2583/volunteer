package volunteer.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.dao.AdminDao;
import volunteer.modal.Admin;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_WRONGMSG = "用户名或密码错误";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		AdminDao dao=new AdminDao();
		Admin admin=new Admin();
		admin.setAccount(account);
		admin.setPassword(password);
		admin=dao.login(admin);
		if(admin.getAccount().equals(LOGIN_WRONGMSG)) {
			request.getSession().setAttribute("wrongMsg", LOGIN_WRONGMSG);
			response.sendRedirect("Admin/index.jsp");
			return;
		}
		request.getSession().setAttribute("Admin", admin);
		response.sendRedirect("Admin/main.jsp");
	}

}
