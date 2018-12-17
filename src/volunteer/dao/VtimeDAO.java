package volunteer.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import volunteer.po.Apply;
import volunteer.po.ManHour;
import volunteer.po.ManHourPK;

public class VtimeDAO extends BaseHibernateDAO{

	public VtimeDAO() {
		// TODO 自动生成的构造函数存根
	}
	private Log log = LogFactory.getLog(FrontDAO.class);
	//返回工时信息
	public List vtimeSearch(String No)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from User as user where No="+No;
		Query queryObject = session.createQuery(queryString);
		tran.commit();
		List result=queryObject.list(); 
		session.flush();
		session.close();
		return result;
	}
	//根据活动日期名称添加工时记录----用户补充缺漏的工时
	public ManHour addVtime(ManHour manhour)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		session.save(manhour);
		tran.commit();
		tran=session.beginTransaction();
		ManHour a = (ManHour)session.get(ManHour.class, manhour.getPk());
		tran.commit();
		return a;
	}
	//根据活动名称日期学号，修改工时
	public String alterVtime(ManHour manhour)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		ManHour temp=(ManHour) session.load(ManHour.class, manhour.getPk());
		temp.setAvtime(manhour.getAvtime());
		session.save(temp);
		tran.commit();
		session.flush();
		session.close();
		return "success";
	}
	//根据活动名称日期学号删除工时记录
	public String vtimeDelete(ManHourPK pk)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		ManHour temp=(ManHour) session.load(ManHour.class, pk);
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
		Transaction tran=session.beginTransaction();
		String queryString = "from ManHour as manhour where manhour.pk.Aname='"+Aname+"' and manhour.pk.Adate='"+Adate+"'";
		Query queryObject = session.createQuery(queryString);
		tran.commit();
		List result=queryObject.list(); 
		return result;
	}

}
