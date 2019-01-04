package volunteer.dao;

import volunteer.po.Admin;

public interface IAdminDAO {
	public Admin login(String account,String password);
	public String alterPsw(String id,String password);
}
