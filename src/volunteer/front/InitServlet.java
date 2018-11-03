//package volunteer.front;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//
///**
// * Servlet implementation class InitServlet
// */
//@WebServlet( urlPatterns ={"/InitServlet"},initParams= {@WebInitParam(name="appId",value="wxe70a657861b2349d"),@WebInitParam(name="secret",value="b9364b42fb126408c8a8c3786e19fcb4")},loadOnStartup=0)
//public class InitServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public InitServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException 
//	{
//          this.init();
//		 TokenThread.appid = config.getInitParameter("appId");  
//         TokenThread.secret = config.getInitParameter("secret");    
//         System.out.println("weixin api appid:"+TokenThread.appid);    
//         System.out.println("weixin api appsecret:"+ TokenThread.secret);
//         // 未配置appid、secret时给出提示    
//         if ("".equals(TokenThread.appid) || "".equals(TokenThread.secret)) {    
//            System.out.println("appid and appsecret configuration error, please check carefully.");    
//         } else 
//         {    
//             // 启动定时获取access_token的线程    
//             new Thread(new TokenThread()).start();    
//         }   
//
//
//	}
//
//}
