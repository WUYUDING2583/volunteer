package volunteer.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.dao.AdminDao;

/**
 * Servlet implementation class addVtimeServlet
 */
@WebServlet("/addVtimeServlet")
public class addVtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addVtime() {
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
		request.setCharacterEncoding("UTF-8"); 
		String No=request.getParameter("No");
		float Avtime=Float.parseFloat(request.getParameter("Avtime"));
		String Aname=request.getParameter("Aname");
		String Adate=request.getParameter("Adate");
		String message=null;
		AdminDao dao=new AdminDao();
		message=dao.addVtime(Aname, Adate, No, Avtime);
		PrintWriter out=response.getWriter();
		out.print(message);
	}

}
