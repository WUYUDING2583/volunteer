package volunteer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import volunteer.front.getOpenId;
import volunteer.po.Apply;
import volunteer.po.ApplyPK;
import volunteer.po.User;
import volunteer.service.FrontService;

public class FrontAction {

	private User user;
	public FrontAction() {
		// TODO 自动生成的构造函数存根
	}
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	FrontService front=new FrontService();
//判断是否注册过
	public void isRegister() throws IOException 
	{
		String code=request.getParameter("code");
		getOpenId getOI=new getOpenId();
		String openid=getOI.getOpenId(code);
		String result=front.isRegister(openid);
		out=response.getWriter();
		out.print(result);
	}
//注册
	public void insertUserMess() 
	{
		String Name=request.getParameter("Name");
		String Sex=request.getParameter("Sex");
		String No=request.getParameter("No");
		String Cclass=request.getParameter("Class");
		String Vno=request.getParameter("Vno");
		String Phone=request.getParameter("Phone");
		user=new User(Name,Sex,No,Cclass,Vno,Phone);
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result=front.register(user);
		if(result.equals("成功"))
			out.print("success");
		else 
			out.print("failed");
	}
//获取活动列表
	public void viewActivity() 
	{
		
		String flag=request.getParameter("flag");
		System.out.println("FLAG  "+flag);
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.print(front.viewActivity(flag));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
//获取活动详细情况
	/*
	public void viewApply() 
	{
		String Aname=request.getParameter("Aname");
		String Ano=request.getParameter("Ano");
		String Adeadline=request.getParameter("Adeadline");
		front.viewApply();
	}*/
//报名
	public void apply() 
	{
		String No=request.getParameter("No");
		String Ano=request.getParameter("Ano");
		String Atime=request.getParameter("Atime");
		String Ajobstate=request.getParameter("Ajobstate");
		String Aname=request.getParameter("Aname");
		String Address=request.getParameter("Address");
		String Adate=request.getParameter("Adate");
		String formId=request.getParameter("formId"); 
		ApplyPK pk=new ApplyPK(No,Ano,Atime,Ajobstate);
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Apply apply=new Apply(pk,Aname);
		try {
			out.println(front.apply(apply,Address,Adate,formId));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
//查询工时详情
	public void searchManHour() 
	{
		String No=request.getParameter("No");
		System.out.println(No);
		String result=front.searchManHour(No);
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
		
	}
//取消报名
	public void cancelApply() 
	{
		String result="";
		String No=request.getParameter("No");
		String flag=request.getParameter("flag");
		if(flag.equals("getApply"))
			{
				try {
					result=front.getApply(No);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(flag.equals("cancelApply"))
			{
				String Ano=request.getParameter("Ano");
				String Ajobstate=request.getParameter("Ajobstate");
				String Atime=request.getParameter("Atime");
				result=front.cancelApply(new ApplyPK(No,Ano,Ajobstate,Atime));
				System.out.println("No: "+No+"Ano: "+Ano+"Ajobstate: "+Ajobstate+"Atime: "+Atime);
			}
			try {
				PrintWriter out=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println(result);
	}
//更新用户信息
	public void updateUserMess() 
	{
		String Name=request.getParameter("Name");
		String Sex=request.getParameter("Sex");
		String No=request.getParameter("No");
		String Cclass=request.getParameter("Class");
		String Vno=request.getParameter("Vno");
		String Phone=request.getParameter("Phone");
		user=new User(Name,Sex,No,Cclass,Vno,Phone);
		try {
			out=response.getWriter();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		String result=front.updateUserMess(user);
		out.print(result);
	}
//获取用户信息
	public void userMess() 
	{
		String No=request.getParameter("No");
		try {
			out=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(front.getUserInfo(No));
	}
}
