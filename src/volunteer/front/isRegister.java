package volunteer.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import volunteer.dao.FrontDao;

@WebServlet("/isRegister")
public class isRegister extends HttpServlet 
{
	private static  String JSCODE="";
	private static final long serialVersionUID = 1L;
    public isRegister() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSCODE=request.getParameter("code");
		getOpenId getOI=new getOpenId();
		String openid=getOI.getOpenId(JSCODE);
		PrintWriter out=response.getWriter();
		FrontDao dao=new FrontDao();
		try {
			if(dao.isRegister(openid).equals("noexsist"))
			{out.print("No");}
			else 
				{out.print(dao.isRegister(openid));}
		} catch (SQLException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
