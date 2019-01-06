package volunteer.interceptors;

import java.util.Map;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import volunteer.po.Admin;

public class AuthorityInterceptor extends AbstractInterceptor {
		
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("Authority Interceptor executed!");
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		Admin admin = (Admin) session.get("Admin");
		if (admin != null) {
			System.out.println("Pass the interceptor");
			return invocation.invoke();
		} else {
			ctx.put("tip", "您还没有登录，请输入用户名和密码登录系统");
			/*
			 * ctx.getApplication().put("", "");//application作用域 ctx.getSession().put("",
			 * "");//session作用域 ctx.put("", ""); //request作用域
			 */ 
			System.out.println("Do not pass interceptor");
			return Action.LOGIN;
		}
	}
}
