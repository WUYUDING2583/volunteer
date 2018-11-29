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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class FrontDAO extends BaseHibernateDAO {
	private Log log = LogFactory.getLog(FrontDAO.class);
	public FrontDAO() {
		// TODO 自动生成的构造函数存根
	}
	//判断是否注册
	public List isRegister(String openid)
	{
		log.debug("finding user instance by hql");
		JSONObject json = new JSONObject();
		List result = null;
		try {
			String queryString = "from User as user where openid="+openid;
			Query queryObject = getSession().createQuery(queryString);
			result= queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by hql failed", re);
		}
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
		String queryString = "from ActBase as base";
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}
//获取活动时间段
	public List<String> getAtime(String Ano)
	{
		String queryString = "select a.Atime from ActReq a where a.Ano="+Ano;
		Query queryObject = getSession().createQuery(queryString);
		List<String> atime=queryObject.list();
		return atime;
	}
	public void viewApply() 
	{

	}
	//判断是否已经报名过
	public List isApply(Apply apply)
	{
		String queryString = "from Apply as a where a.pk="+apply.getPk();
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
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
		String hql="DELETE Apply WHERE pk="+pk;
		return getSession().createQuery(hql).executeUpdate();
	}
	//返回用户openid
	public String returnOpenid(String No)
	{
		String queryString = "from User as user where No="+No;
		Query queryObject = getSession().createQuery(queryString);
		User u=(User) queryObject.list().get(0);
		return u.getOpenid();
	}
	public List searchManHour(String No) 
	{
		String queryString = "from ManHour as manhour where No="+No;
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}
	
	public String getAdate(String Ano)
	{
		String hql = "SELECT info.Adate FROM ActInfo info where info.Ano="+Ano;
		List list = getSession().createQuery(hql).list();
		return list.get(0).toString();
	}
	public List getApply(String No)
	{
		String queryString = "from Apply as apply where No="+No;
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
		
	}
	public int updateUserMess(User user) 
	{
		String hql= "UPDATE User SET Name="+user.getName()+",Sex="+user.getSex()+",Cclass="+user.getCclass()+",Vno="+user.getVno()+",Phone="+user.getPhone()+" WHERE No="+user.getNo();
		return getSession().createQuery(hql).executeUpdate();
	}
//获取用户信息
	public List getUserInfo(String No) 
	{
		
		String queryString = "from User as user where No="+No;
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
		
	}
//更新活动要求表中已报名人数
	public int updateActReq(ActReqPK pkey,String flag)
	{
		String hql="";
		if(flag.equals("add"))
		{
			 hql= "UPDATE ActReq SET DoneAccount = DoneAccount+1 WHERE pk="+pkey;
		}
		else if(flag.equals("down"))
		{
			 hql = "UPDATE ActReq SET DoneAccount = DoneAccount-1 WHERE pk="+pkey;
		}
        return getSession().createQuery(hql).executeUpdate();
	}
}
