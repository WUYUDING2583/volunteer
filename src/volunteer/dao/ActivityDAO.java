package volunteer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.ActInfo;
import volunteer.po.ManHour;

public class ActivityDAO extends BaseHibernateDAO {

	public ActivityDAO() {
		// TODO 自动生成的构造函数存根
	}
	//添加活动
	public String addActivity(ActInfo info)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String result=(String)session.save(info);
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	//获取该组织的所有活动信息
	public List getActInfoList(String college)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from ActInfo where college='"+college+"'";
		Query queryObject = session.createQuery(hql);
		tran.commit();
		List result= queryObject.list();
		
		session.flush();
		session.close();
		return result;
	}
	//获取活动信息
	public ActInfo getActInfo(String Ano)
	{
		System.out.println("GETACTINFOSDAO");
		
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from ActInfo as a where a.Ano='"+Ano+"'";
		Query queryObject=session.createQuery(hql);
		ActInfo result=(ActInfo)(queryObject.list().get(0));
		//ActInfo result=(ActInfo) session.get(ManHour.class, Ano);
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	//删除活动
	public String deleteActivity(String Ano)
	{
		String result="0";
		try
		{
			Session session=getSession();
			Transaction tran=session.beginTransaction();
			ActInfo temp=(ActInfo) session.load(ActInfo.class, Ano);
			System.out.println("DELETE:"+temp.getAname());
			session.delete(temp);
			tran.commit();
			result="1";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	//返回活动已经结束且已经上传工时表的活动
	public List endAndUp(String college)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from ActInfo where college='"+college+"' and state="+1;
		Query queryObject = session.createQuery(hql);
		tran.commit();
		List result= queryObject.list();
		session.flush();
		session.close();
		return result;
	}
	//返回活动已经结束且但尚未上传工时表的活动
	public List endNotUp(String college)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql="from ActInfo where college='"+college+"' and state="+0;
		Query queryObject = session.createQuery(hql);
		tran.commit();
		List result= queryObject.list();
		session.flush();
		session.close();
		return result;
	}
	//将活动状态设置为已上传工时表
	public String setState(String Ano)
	{
		String result="fail";
		Session session=getSession();
		try
		{
			
			Transaction tran=session.beginTransaction();
			ActInfo temp=(ActInfo)session.load(ActInfo.class, Ano);
			temp.setState(1);
			session.update(temp);
			result="success";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
		return result;
	}
}
