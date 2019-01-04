package volunteer.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONObject;

import volunteer.po.ActReqPK;
import volunteer.po.Apply;
import volunteer.po.ApplyPK;
import volunteer.po.User;
import volunteer.po.ViewApplyPK;

public interface IFrontService {
	public String getOpenId();
	public void setOpenId(String openid);
	public String isRegister(String openid);
	public String register(User user);
	public String viewActivity(String flag) throws ParseException;
	public List<String> getAtime(String Ano);
	public String getActivityDetail(ViewApplyPK pk);
	public String apply(Apply apply, String Address, String Adate, String formId) throws IOException, SQLException;
	public JSONObject returnIdentify(String No) throws IOException, SQLException;
	public String searchManHour(String No);
	public String cancelApply(ApplyPK pk);
	public String getAdate(String Ano);
	public String getApply(String No) throws ParseException;
	public String updateUserMess(User user);
	public String isApply(Apply apply);
	public String getUserInfo(String No);
	public int sendMessage(JSONObject identify, String Aname, String Address, String Adate, String formId);
	public int updateActReq(ActReqPK pkey, String flag);
}
