package volunteer.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.dao.AdminDao;
import volunteer.modal.ActInfo;
import volunteer.modal.Admin;

/**
 * Servlet implementation class alterVtimeServlet
 */
@WebServlet("/alterVtimeServlet")
public class alterVtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alterVtime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminDao dao=new AdminDao();
		ArrayList<ActInfo> infoList=new ArrayList<ActInfo>();
		Admin admin=(Admin)request.getSession().getAttribute("Admin");
		infoList=dao.getActInfoList(admin);
		request.getSession().setAttribute("infoList", infoList);
		response.sendRedirect("Admin/alterVtime.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		request.setCharacterEncoding("UTF-8"); 
		String Aname=request.getParameter("Aname");
		String Adate=request.getParameter("Adate");
		String No=request.getParameter("No");
		float vtime=Float.parseFloat(request.getParameter("vtime"));
		String message=null;
		AdminDao dao=new AdminDao();
		if(dao.alterVtime(Aname, Adate, No, vtime)>0)
			message="ok";
		else
			message="no";
		PrintWriter out=response.getWriter();
		out.print(message);
	}

}
