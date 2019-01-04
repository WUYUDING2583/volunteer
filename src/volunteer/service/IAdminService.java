package volunteer.service;

import volunteer.po.Admin;

public interface IAdminService {
	public Admin login(String account,String password);
	public boolean alterPsw(String id,String password);
	
}
