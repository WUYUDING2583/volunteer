package volunteer.service;

import java.util.ArrayList;
import java.util.List;

import volunteer.po.ManHour;
import volunteer.po.User;

public class VtimeService {
	private List userList = new ArrayList<>();
	
	/*
	 * @根据学号查询该学号所有工时记录
	 * 传入参数 No 学号
	 * 返回该学号所有的工时记录
	 * 数据格式按照方法内
	 */
	public List vtimeSearch(String No) {
		for (int i = 0; i < 4; i++) {
			User user = new User();
			user.setName("No" + i);
			user.setVtime(i + 5);
			user.setAdate("2018-12-10");
			user.setAname("略略略");
			userList.add(user);
		}
		return userList;
	}

	/*
	 * @根据活动日期名称添加工时记录
	 * 用户补充缺漏的工时
	 * 前提：该学号已在平台注册
	 * 传入参数 Aname 活动名称
	 * 		 Adate 活动日期
	 * 		 No    学号
	 * 		 vtime 工时数
	 * 添加成功返回该学号所有人的姓名
	 * 添加失败 返回 "no"
	 */
	public String addVtime(String Aname, String Adate, String No, float vtime) {
		return "Dominic";
	}

	/*
	 * @根据活动名称日期学号，修改工时
	 * 传入参数 Aname 活动名称
	 * 		 Adate 活动日期
	 * 		 No    学号
	 * 		 vtime 工时数
	 * 修改成功返回true
	 * 修改失败返回false
	 */
	public boolean alertVtime(String Aname, String Adate, String No, float vtime) {
		return true;
	}

	/*
	 * @根据活动名称日期学号删除工时记录
	 * 传入参数 Aname 活动名称
	 * 		 Adate 活动日期
	 * 		 No    学号
	 * 删除成功返回 true
	 * 删除失败返回false
	 */
	public boolean vtimeDelete(String Aname, String Adate, String No) {
		return true;
	}

	/*
	 * @根据活动名称活动日期返回活动所有工时信息
	 * 传入参数 Aname 活动名称
	 * 		 Adate 活动日期
	 * 查找成功 返回数据按照方法内格式
	 * 查找失败返回null
	 */
	public List vtimeDetail(String Aname, String Adate) {
		for (int i = 0; i < 4; i++) {
			User user = new User();
			user.setName("No" + i);
			user.setNo("20161621123" + i);
			user.setVtime(i + 5);
			user.setAdate(Adate);
			user.setAname(Aname);
			userList.add(user);
		}
		return userList;
	}
}
