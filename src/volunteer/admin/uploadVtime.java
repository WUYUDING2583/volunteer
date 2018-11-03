//package volunteer.admin;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileUploadException;
//
//import com.google.gson.Gson;
//
//import volunteer.dao.AdminDao;
//import volunteer.modal.ActInfo;
//import volunteer.modal.Admin;
//
///**
// * Servlet implementation class uploadVtime
// */
//@WebServlet("/uploadVtimeServlet")
//public class uploadVtime extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public uploadVtime() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		AdminDao dao=new AdminDao();
//		ArrayList<ActInfo> infoList=new ArrayList<ActInfo>();
//		Admin admin=(Admin)request.getSession().getAttribute("Admin");
//		infoList=dao.getActInfoList(admin);
//		request.getSession().setAttribute("infoList", infoList);
//		response.sendRedirect("Admin/uploadVtime.jsp");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		AdminDao dao=new AdminDao();
//		String path = getServletContext().getRealPath("/") + File.separator;
//		PrintWriter out=response.getWriter();
//		int count=0;
//		try {
//			File file=dao.fileUpload(request, response, path);
//			count=dao.vtimeUpload(file);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			out.println("failure");
//		}
//		out.print(count);
//	}
//
//}
