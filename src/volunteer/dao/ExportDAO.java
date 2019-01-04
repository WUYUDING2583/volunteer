package volunteer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import volunteer.po.Apply;
import volunteer.po.User;

public class ExportDAO extends BaseHibernateDAO implements IExportDAO{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public ExportDAO() {
		// TODO 自动生成的构造函数存根
	}
	public void getUser(Apply apply)
	{
		User result = null;
		try
		{
			
			Session session=getSession();
			Transaction tran=session.beginTransaction(); 
			String hql="from User as a where a.No='"+apply.getPk().getNo()+"'";
			Query queryObject = session.createQuery(hql).setMaxResults(30);
			result= (User) queryObject.list().get(0);
			tran.commit();
			session.flush();
			session.close();
			apply.setUser(result);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List getApplyList(String Ano)
	{
		List result = null;
		try
		{
			
			Session session=getSession();
			Transaction tran=session.beginTransaction(); 
			String hql="from Apply as a where a.pk.Ano='"+Ano+"'";
			Query queryObject = session.createQuery(hql).setMaxResults(30);
			
			result= queryObject.list();
			
			System.out.println("ANO"+Ano);
			System.out.println("EXPORTDAO:"+result.size());
			tran.commit();
			System.out.println("EXPORTDAO");
			session.flush();
			session.close();;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("ERROR");
			}
			
			return result;
	}
}
