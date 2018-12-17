package volunteer.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.ActInfo;
import volunteer.po.Admin;

public class AdminDAO extends BaseHibernateDAO{

	public AdminDAO() {
		// TODO 自动生成的构造函数存根
	}
	//管理员登陆
	public Admin login(String account,String password)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from Admin where account='"+account+"' and password='"+password+"'";
		Query queryObject = session.createQuery(hql);
		tran.commit();
		Admin result=(Admin) queryObject.list().get(0);
		return result;
	}
	//修改密码
	public String alterPsw(String id,String password)
	{
		
			String result="fail";
			try
			{
				Session session=getSession();
				Transaction tran=session.beginTransaction();
				Admin temp=(Admin)session.load(Admin.class, id);
				temp.setPassword(password);
				session.update(temp);
				result="success";
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return result;
	}
}
