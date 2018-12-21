//package volunteer.front;
//
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
//import com.google.gson.Gson;
//
//import volunteer.dao.FrontDao;
//import volunteer.modal.ActInfo;
//
///**
// * Servlet implementation class viewActivity
// */
//@WebServlet("/viewActivity")
//public class viewActivity extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public viewActivity() {
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
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		ArrayList<ActInfo> list=new ArrayList<ActInfo>();
//		FrontDao dao=new FrontDao();
//		String flag=request.getParameter("flag");
//		System.out.println("FLAG  "+flag);
//		list=dao.getActInfoList(flag);
//		Gson gson=new Gson();
//		String json=gson.toJson(list);
//		PrintWriter out=response.getWriter();
//		out.print(json);
//		System.out.println(json);
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
