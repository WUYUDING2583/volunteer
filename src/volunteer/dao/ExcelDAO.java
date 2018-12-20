package volunteer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.ManHour;

public class ExcelDAO extends BaseHibernateDAO{

	public ExcelDAO() {
		// TODO 自动生成的构造函数存根
	}
	
	//添加工时信息
	public String addVtime(List vtimeList)
	{
		String result="fail";boolean result1=false;
		ManHour temp=(ManHour)vtimeList.get(0);
		try
		{
			Session session=getSession();
			Transaction tran=session.beginTransaction();
			for(int i=0;i<vtimeList.size();i++)
			{
				ManHour manhour=(ManHour)vtimeList.get(i);
				session.save(manhour);
				result1=true;
				if(i%100==0)
				{
					session.flush();
					session.clear();
				}
			}
			if(result1==true)
			{
				String hql= "UPDATE ActInfo act SET act.state="+1 +"where act.Aname='"+temp.getPk().getAname()+"' and act.Adate='"+temp.getPk().getAdate()+"'";
				int a=session.createQuery(hql).executeUpdate();
				System.out.println("更新工时上传状态："+a);
				result="success";
			}
			tran.commit();
			session.flush();
			session.clear();
			session.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
