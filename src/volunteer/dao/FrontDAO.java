package volunteer.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import volunteer.dao.BaseHibernateDAO;
import volunteer.po.ActInfo;
import volunteer.po.ActReqPK;
import volunteer.po.Apply;
import volunteer.po.ApplyPK;
import volunteer.po.User;
import volunteer.po.ViewApply;
import volunteer.po.ViewApplyPK;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class FrontDAO extends BaseHibernateDAO {
	private Log log = LogFactory.getLog(FrontDAO.class);
	public FrontDAO() {
	}
	//判断是否注册
	public List isRegister(String openid)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		log.debug("finding user instance by hql");
		JSONObject json = new JSONObject();
		List result = null;
		try {
			String queryString = "from User as user where openid='"+openid+"'";
			Query queryObject = session.createQuery(queryString);
			result= queryObject.list();
			System.out.println("DDDDD:"+openid);
			//System.out.println(new JSONObject(result.get(0)));
		} catch (RuntimeException re) {
			log.error("find by hql failed", re);
		}
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	//注册
	public String register(User user) 
	{
		log.debug("user register");
		String result=null;
		int count=0;
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		try {
			System.out.println("Save user");
			session.save(user);
			log.debug("save successful");
			count=1;
		} 
		catch (RuntimeException re) 
		{
			re.printStackTrace();
			log.error("save failed", re);
			result=re.getMessage();
			throw re;
		}
		tran.commit();
		session.flush();
		session.close();
		if(count>0)
			result="成功";
		else
			result="不知为何失败";
		return result;
	}
//获取活动列表
	public List viewActivity() 
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from ActBase as base";
		Query queryObject = session.createQuery(queryString);
		tran.commit();
		List result= queryObject.list();
		session.flush();
		session.close();
		return result;
	}
//获取活动时间段
	public List<String> getAtime(String Ano)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "select a.pk.Atime from ActReq a where a.pk.Ano='"+Ano+"'group by a.pk.Atime";
		Query queryObject = session.createQuery(queryString);
		List<String> atime=queryObject.list();
		tran.commit();
		session.flush();
		session.close();
		return atime;
	}
	//返回活动详情
	public ViewApply viewApply(ViewApplyPK pk) 
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		/*
		String queryString = "from ViewApply as a where a.pk="+pk;
		Query queryObject = getSession().createQuery(queryString);
		*/
		ViewApply result=(ViewApply)session.get(ViewApply.class, pk);
		tran.commit();
		//List list=queryObject.list();
		//list.get(0);
		session.flush();
		session.close();
		return result;
	}
	//返回活动岗位及剩余人数
	public List getAjobstate(String Ano,String Atime)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "select a.pk.Ajobstate,a.Ajobcount-a.DoneAccount from ActReq a where a.pk.Ano='"+Ano+"'and a.pk.Atime='"+Atime+"'";
		Query queryObject = session.createQuery(queryString);
		List result=queryObject.list();
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	//判断是否已经报名过
	public int isApply(Apply apply)
	{
		Session session=getSession();
		int result = 1;
		Transaction tran=session.beginTransaction();
		ApplyPK pk=apply.getPk();
		String queryString;
		queryString= "from Apply as a where a.pk.No='"+pk.getNo()+"' and a.pk.Ano='"+pk.getAno()+"' and a.pk.Atime='"+pk.getAtime()+"' and a.pk.Ajobstate='"+pk.getAjobstate()+"'";
		//List list=(List) session.get(Apply.class, apply.getPk());
		Query queryObject;
		queryObject= session.createQuery(queryString);
		List list=queryObject.list();
		if(list.size()==1);
		else
		{
			 queryString = "from Apply as a where a.pk.No='"+pk.getNo()+"' and a.pk.Ano='"+pk.getAno()+"' and a.pk.Atime='"+pk.getAtime()+"' ";
			 queryObject = session.createQuery(queryString);
			 list=queryObject.list();
			 if(list.size()>0)result+=list.size();
			 else result=0;
		}
		tran.commit();
		session.flush();
		session.close();
		System.out.println("ISAPPLY:"+result);
		return result;
	}
	//报名
	public String apply(Apply apply) 
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String result;
		try {
			System.out.println("Save apply");
			session.save(apply);
			log.debug("save successful");
			result="success";
		} 
		catch (RuntimeException re) 
		{
			re.printStackTrace();
			log.error("save failed", re);
			result=re.getMessage();
			throw re;
		}
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	//取消报名
	public int deleteApply(ApplyPK pk)
	{
		//String hql="DELETE Apply WHERE pk="+pk;
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		Apply a = (Apply)session.get(Apply.class, pk); 
		System.out.println("DELETE:"+a.getAname());
		//int result= session.createQuery(hql).executeUpdate();
		session.delete(a);
		tran.commit();
		session.flush();
		session.close();
		return 1;
	}
	//返回用户openid
	public String returnOpenid(String No)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from User as user where No="+No;
		Query queryObject = session.createQuery(queryString);
		User u=(User) queryObject.list().get(0);
		tran.commit();
		session.flush();
		session.close();
		return u.getOpenid();
	}
	//查询工时
	public List searchManHour(String No) 
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from ManHour as manhour where No="+No;
		Query queryObject = session.createQuery(queryString);
		tran.commit();
		List result=queryObject.list();
		session.flush();
		session.close();
		return result;
	}
	//获取活动时间
	public String getAdate(String Ano)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql = "SELECT a.Adate FROM ActBase a where a.Ano='"+Ano+"'";
		List list = session.createQuery(hql).list();
		tran.commit();
		session.flush();
		session.close();
		return list.get(0).toString();
	}
	//获取报名信息
	public List getApply(String No)
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String queryString = "from Apply as apply where No="+No;
		Query queryObject = session.createQuery(queryString);
		tran.commit();
		List result=queryObject.list();
		session.flush();
		session.close();
		return result;
		
	}
	//更新用户信息
	public int updateUserMess(User user) 
	{
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		String hql= "UPDATE User user SET user.Name='"+user.getName()+"',user.Sex='"+user.getSex()+"',user.Cclass='"+user.getCclass()+"',user.Vno='"+user.getVno()+"',user.Phone='"+user.getPhone()+"' WHERE user.No='"+user.getNo()+"'";
		int result= session.createQuery(hql).executeUpdate();
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
//获取用户信息
	public List getUserInfo(String No) 
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
//更新活动要求表中已报名人数
	public int updateActReq(ActReqPK pkey,String flag)
	{
		String hql="";
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		if(flag.equals("add"))
		{
			 hql= "UPDATE ActReq  SET DoneAccount = DoneAccount+1 WHERE pk.Ano='"+pkey.getAno()+"' and pk.Atime='"+pkey.getAtime()+"' and pk.Ajobstate='"+pkey.getAjobstate()+"'";
		}
		else if(flag.equals("down"))
		{
			hql= "UPDATE ActReq  SET DoneAccount = DoneAccount-1 WHERE pk.Ano='"+pkey.getAno()+"' and pk.Atime='"+pkey.getAtime()+"' and pk.Ajobstate='"+pkey.getAjobstate()+"'";
		}
       int result=getSession().createQuery(hql).executeUpdate();
       tran.commit();
		session.flush();
		session.close();
		return result;
	}
}
