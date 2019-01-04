package volunteer.dao;

import java.util.List;

import volunteer.po.ManHour;
import volunteer.po.ManHourPK;

public interface IVtimeDAO {
	public List vtimeSearch(String No);
	public ManHour addVtime(ManHour manhour);
	public String alterVtime(ManHour manhour);
	public String vtimeDelete(ManHourPK pk);
	public List vtimeDetail(String Aname, String Adate);
	public void getUser(ManHour manhour);
	
}
