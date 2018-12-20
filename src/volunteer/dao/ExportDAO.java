package volunteer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.Apply;

public class ExportDAO extends BaseHibernateDAO {

	public ExportDAO() {
		// TODO 自动生成的构造函数存根
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
		System.out.println("EXPORTDAO:"+result.size());
		tran.commit();
		System.out.println("EXPORTDAO");
		
		session.flush();
		session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("ERROR");
		}
		
		return result;
	}
}
