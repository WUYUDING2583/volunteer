package volunteer.service;

import java.util.List;

import volunteer.po.ActInfo;

public interface IActivityService {
	public String getRandomString();
	public boolean addActivity(ActInfo info);
	public List getActInfoList(String college);
	public String getActInfo(String Ano);
	public String getActReq(String Ano);
	public String deleteActivity(String Ano);
	public List endAndUp(String college);
	public List endNotUp(String college);
	public boolean setState(String Ano);
}
