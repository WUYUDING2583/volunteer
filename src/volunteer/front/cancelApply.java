package volunteer.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.dao.FrontDao;

/**
 * Servlet implementation class cancelApply
 */
@WebServlet("/cancelApply")
public class cancelApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public cancelApply() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		request.setCharacterEncoding("UTF-8");
		String result="";
		String No=request.getParameter("No");
		String flag=request.getParameter("flag");
		FrontDao dao=new FrontDao();
		if(flag.equals("getApply"))
		{
			 try {
				result=dao.getApply(No);
			} catch (SQLException | ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(flag.equals("cancelApply"))
		{
			String Ano=request.getParameter("Ano");
			String Ajobstate=request.getParameter("Ajobstate");
			String Atime=request.getParameter("Atime");
			result=dao.cancelApply(No, Ano, Ajobstate, Atime);
			System.out.println("No: "+No+"Ano: "+Ano+"Ajobstate: "+Ajobstate+"Atime: "+Atime);
		}
		
		PrintWriter out=response.getWriter();
		System.out.println("CANCELAPPLY");
		System.out.println(result);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
