//package volunteer.admin;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import volunteer.dao.AdminDao;
//import volunteer.modal.ActInfo;
//import volunteer.modal.Admin;
//
///**
// * Servlet implementation class myActivityServlet
// */
//@WebServlet("/myActivityServlet")
//public class myActivity extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public myActivity() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		AdminDao dao=new AdminDao();
//		ArrayList<ActInfo> infoList=new ArrayList<ActInfo>();
//		Admin admin=(Admin)request.getSession().getAttribute("Admin");
//		infoList=dao.getActInfoList(admin);
//		request.getSession().setAttribute("infoList", infoList);
//		response.sendRedirect("Admin/myActivity.jsp");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
