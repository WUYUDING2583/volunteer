package volunteer.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import volunteer.dao.AdminDao;
import volunteer.modal.ActInfo;
import volunteer.modal.ActReq;

/**
 * Servlet implementation class showActivityServlet
 */
@WebServlet("/showActivityServlet")
public class showActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showActivity() {
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
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String Ano=new String(request.getParameter("Ano").getBytes("iso-8859-1"),"UTF-8");
		AdminDao dao=new AdminDao();
		ActInfo info=new ActInfo();
		ArrayList<ActReq> reqList=new ArrayList<ActReq>();
		info=dao.getActInfo(Ano);
		System.out.println(info.getArequest());
		reqList=dao.getActReq(Ano);
		Gson gson=new Gson();
		String json=gson.toJson(info)+"|"+gson.toJson(reqList);
		PrintWriter out=response.getWriter();
		out.println(json);
	}

}
