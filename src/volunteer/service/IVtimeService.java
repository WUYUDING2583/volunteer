package volunteer.service;

import java.util.List;

import volunteer.po.ManHour;
import volunteer.po.ManHourPK;

public interface IVtimeService {
	public List vtimeSearch(String No);
	public String addVtime(ManHour manhour);
	public boolean alertVtime(ManHour manhour);
	public boolean vtimeDelete(ManHourPK pk);
	public List vtimeDetail(String Aname, String Adate);
}
