//package volunteer.admin;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import volunteer.dao.AdminDao;
//
///**
// * Servlet implementation class setStateServlet
// */
//@WebServlet("/setStateServlet")
//public class setState extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public setState() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		String Ano=new String(request.getParameter("Ano").getBytes("iso-8859-1"),"UTF-8");
//		AdminDao dao=new AdminDao();
//		PrintWriter out=response.getWriter();
//		int count=dao.setState(Ano);
//		out.print(count);
//	}
//
//}
