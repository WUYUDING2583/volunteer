package volunteer.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.ActInfo;
import volunteer.po.Admin;

public class AdminDAO extends BaseHibernateDAO{

	public AdminDAO() {	
	}
	//管理员登陆
	public Admin login(String account,String password)
	{
		Admin result=new Admin();
		result.setAccount("wrong");
		Session session=getSession();
		try
		{
			
			Transaction tran=session.beginTransaction();
			String hql="from Admin where account='"+account+"' and password='"+password+"'";
			Query queryObject = session.createQuery(hql);
			tran.commit();
			result=(Admin) queryObject.list().get(0);
			session.flush();
			session.clear();
			
		}
		catch(Exception e) {e.printStackTrace();}
		finally
		{
			HibernateUtil.closeSession();
		}
		return result;
	}
	//修改密码
	public String alterPsw(String id,String password)
	{
		
			String result="fail";
			Session session=getSession();
			try
			{	
				System.out.println("修改密码....");
				
				Transaction tran=session.beginTransaction();
				Admin temp=(Admin)session.load(Admin.class, id);
				temp.setPassword(password);
				System.out.println("Name:"+temp.getAccount());
				session.update(temp);
				tran.commit();
				result="success";
				session.flush();
				session.clear();
				
			}
			catch(Exception e)
			{
				System.out.println("更新密码出错");
				e.printStackTrace();
			}
			finally
			{
				HibernateUtil.closeSession();
			}
			
			return result;
	}
}
