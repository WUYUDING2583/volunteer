//package volunteer.front;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import volunteer.dao.FrontDao;
//
///**
// * Servlet implementation class apply
// */
//@WebServlet("/apply")
//public class apply extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public apply() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(request,response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		request.setCharacterEncoding("UTF-8");
//		String No=request.getParameter("No");
//		String Ano=request.getParameter("Ano");
//		String Atime=request.getParameter("Atime");
//		String Ajobstate=request.getParameter("Ajobstate");
//		String Aname=request.getParameter("Aname");
//		String Address=request.getParameter("Address");
//		String Adate=request.getParameter("Adate");
//		String formId=request.getParameter("formId");
//		FrontDao dao=new FrontDao();
//		String result = null;
//		try {
//			result = dao.apply(No, Ano,Aname,Ajobstate, Atime,Address,Adate,formId );
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		PrintWriter out=response.getWriter();
//		out.print(result);
//	}
//
//}
