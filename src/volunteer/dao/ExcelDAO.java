package volunteer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExcelDAO extends BaseHibernateDAO{

	public ExcelDAO() {
		// TODO 自动生成的构造函数存根
	}
	//添加工时信息
	public String addVtime(List vtimeList)
	{
		String result="fail";
		try
		{
			Session session=getSession();
			Transaction tran=session.beginTransaction();
			for(int i=0;i<vtimeList.size();i++)
			{
				session.save(vtimeList.get(i));
				if(i%100==0)
				{
					session.flush();
					session.clear();
				}
			}
			tran.commit();
			session.flush();
			session.clear();
			session.close();
			result="success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
