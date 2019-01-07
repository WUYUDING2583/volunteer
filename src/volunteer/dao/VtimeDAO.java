package volunteer.dao;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import volunteer.po.Apply;
import volunteer.po.ManHour;
import volunteer.po.ManHourPK;
import volunteer.po.User;

public class VtimeDAO extends BaseHibernateDAO implements IVtimeDAO{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public VtimeDAO() {
		// TODO 自动生成的构造函数存根
	}
	private Log log = LogFactory.getLog(FrontDAO.class);
	//返回工时信息
	public List vtimeSearch(String No)
	{
		System.out.println("DDGD:"+No);
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from User as user where No="+No;
		Query queryObject = session.createQuery(queryString);
		
		List result=queryObject.list(); 
		
		/*User temp=(User) result.get(0);
		Set<ManHour> temp1=temp.getManhours();*/
		tran.commit();
		/*System.out.println("Dsgd:");
		System.out.println(temp.getName()+"   ");
		for(ManHour item:temp1)
		{
			System.out.println(item.getPk().getAname());
		}*/
		session.flush();
		session.close();
		return result;
	}
	//根据活动日期名称添加工时记录----用户补充缺漏的工时
	public ManHour addVtime(ManHour manhour)
	{
		Session session=getSession();
		ManHour a = null;
		try {
			Transaction tran=session.beginTransaction();
		System.out.println("DAO1:"+manhour.getPk().getNo());
		session.save(manhour);
		tran.commit();
		tran=session.beginTransaction();
		System.out.println("DAO2:"+manhour.getPk());
		a = (ManHour)session.get(ManHour.class, manhour.getPk());
		tran.commit();
		session.clear();
		session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return a;
	}
	//根据活动名称日期学号，修改工时
	public String alterVtime(ManHour manhour)
	{
		ManHourPK pk=manhour.getPk();
		//System.out.println("UPDATE VTIME:"+pk.getAdate());
		Session session=getSession();
		try
		{
			Transaction tran=session.beginTransaction();
		//String hql="UPDATE ManHour SET Avtime ="+ manhour.getAvtime()+" WHERE pk.No='"+pk.getNo()+"'and pk.Aname='"+pk.getAname()+"'and pk.Adate='"+pk.getAdate()+"'";
		//System.out.println("UPDATE VTIME:"+pk.getAdate());
		ManHour temp=(ManHour) session.get(ManHour.class, pk);
		temp.setAvtime(manhour.getAvtime());
		//System.out.println("UPDATE VTIME:"+pk.getAdate());
		session.save(temp);
		//session.createQuery(hql).executeUpdate();
		tran.commit();
		session.flush();
		session.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return "success";
	}
	//根据活动名称日期学号删除工时记录
	public String vtimeDelete(ManHourPK pk)
	{
		System.out.println("PK:"+pk.getAname());
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		ManHour temp=(ManHour) session.get(ManHour.class, pk);
		if(temp==null)System.out.println("NULLLLLLL");
		System.out.println("DELETE:"+temp.getPk().getAname());
		session.delete(temp);
		tran.commit();
		session.flush();
		session.close();
		return "success";
	}
	//根据活动名称活动日期返回活动所有工时信息
	public List vtimeDetail(String Aname, String Adate)
	{
		Session session=getSession();
		List result=null;
		try
		{
			Transaction tran=session.beginTransaction();
		String queryString = "from ManHour as manhour where manhour.pk.Aname='"+Aname+"' and manhour.pk.Adate='"+Adate+"'";
		Query queryObject = session.createQuery(queryString);
		 result=queryObject.list(); 
		tran.commit();
		session.flush();
		session.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		
		return result;
	}
public void getUser(ManHour manhour)
{
	String result = null;Session session = null;
	try
	{
		
		session=getSession();
		Transaction tran=session.beginTransaction(); 
		System.out.println("NOOO:"+manhour.getPk().getNo());
		String hql="select user.Name from User  user where user.No='"+manhour.getPk().getNo()+"'";
		Query queryObject = session.createQuery(hql).setMaxResults(30);
		List temp=queryObject.list();
		
		if(temp.size()>0)
		result= (String) queryObject.list().get(0);
		else result="";
		tran.commit();
		session.flush();
		session.close();
		manhour.setUsername(result);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		session.close();
	}
	}
}
