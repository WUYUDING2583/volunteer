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
//import com.google.gson.Gson;
//
//import volunteer.dao.AdminDao;
//import volunteer.modal.Admin;
//
///**
// * Servlet implementation class changePsw
// */
//@WebServlet("/changePsw")
//public class changePsw extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public changePsw() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		Admin admin=(Admin)request.getSession().getAttribute("Admin");
//		AdminDao dao=new AdminDao();
//		PrintWriter out=response.getWriter();
//		Gson gson=new Gson();
//		String id=request.getParameter("id");
//		String password=request.getParameter("password");
//		String data=null;
//		if(!id.equals(admin.getId())) {
//			admin.setAccount("500");
//		}
//		else {
//			admin.setPassword(password);
//			if(!dao.changePsw(admin).equals("ok")) {
//				admin.setAccount("500");
//			}
//		}
//		data=gson.toJson(admin);
//		out.println(data);
//	}
//
//}
