//package volunteer.front;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import volunteer.dao.FrontDao;
//import volunteer.modal.User;
//
///**
// * Servlet implementation class insertUserMess
// */
//@WebServlet("/insertUserMess")
//public class insertUserMess extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public insertUserMess() {
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
//		request.setCharacterEncoding("UTF-8");
//		String Name=request.getParameter("Name");
//		String Sex=request.getParameter("Sex");
//		String No=request.getParameter("No");
//		String Class=request.getParameter("Class");
//		String Vno=request.getParameter("Vno");
//		String Phone=request.getParameter("Phone");
//		
//		System.out.println("TEST");
//		System.out.println(request.getParameter("No"));
//		User user=new User();
//		user.setName(Name);
//		user.setSex(Sex);
//		user.setNo(No);
//		user.setClass(Class);
//		user.setVno(Vno);
//		user.setPhone(Phone);
//		FrontDao dao=new FrontDao();
//		String flag=dao.register(user);
//		PrintWriter out=response.getWriter();
//		if(flag.equals("成功"))out.print("success");
//		else out.print("failed");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request,response);
//	}
//
//}
