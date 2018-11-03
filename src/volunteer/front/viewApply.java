//package volunteer.front;
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
//import volunteer.dao.FrontDao;
//
///**
// * Servlet implementation class viewApply
// */
//@WebServlet("/viewApply")
//public class viewApply extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public viewApply() {
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
//		//doGet(request, response);
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		request.setCharacterEncoding("UTF-8");
//		String Aname=request.getParameter("Aname");
//		String Ano=request.getParameter("Ano");
//		String Adeadline=request.getParameter("Adeadline");
//		FrontDao dao=new FrontDao();
//		String json=dao.getActivityDetail(Aname,Ano, Adeadline);
//		PrintWriter out=response.getWriter();
//		out.print(json);
//	}
//
//}
