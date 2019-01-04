package volunteer.dao;

import java.util.List;

import volunteer.po.ActInfo;

public interface IActivityDAO {
	public String addActivity(ActInfo info);
	public List getActInfoList(String college);
	public ActInfo getActInfo(String Ano);
	public String deleteActivity(String Ano);
	public List endAndUp(String college);
	public List endNotUp(String college);
	public String setState(String Ano);
}
