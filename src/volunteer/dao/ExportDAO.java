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
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from Apply as a where a.Ano='"+Ano+"'";
		Query queryObject = session.createQuery(hql);
		tran.commit();
		List result= queryObject.list();
		session.flush();
		session.close();
		return result;
	}
}
