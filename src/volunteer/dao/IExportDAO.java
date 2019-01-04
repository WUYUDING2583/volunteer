package volunteer.dao;

import java.util.List;

import volunteer.po.Apply;

public interface IExportDAO {
	public List getApplyList(String Ano);

	public void getUser(Apply apply);
}
