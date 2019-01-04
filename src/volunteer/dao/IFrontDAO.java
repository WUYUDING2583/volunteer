package volunteer.dao;

import java.util.List;

import volunteer.po.ActReqPK;
import volunteer.po.Apply;
import volunteer.po.ApplyPK;
import volunteer.po.User;
import volunteer.po.ViewApply;
import volunteer.po.ViewApplyPK;

public interface IFrontDAO {
	public List isRegister(String openid);
	public String register(User user) ;
	public List viewActivity() ;
	public List<String> getAtime(String Ano);
	public ViewApply viewApply(ViewApplyPK pk);
	public List getAjobstate(String Ano,String Atime);
	public int isApply(Apply apply);
	public String apply(Apply apply);
	public int deleteApply(ApplyPK pk);
	public String returnOpenid(String No);
	public List searchManHour(String No);
	public String getAdate(String Ano);
	public List getApply(String No);
	public int updateUserMess(User user);
	public List getUserInfo(String No);
	public int updateActReq(ActReqPK pkey,String flag);
	
}
