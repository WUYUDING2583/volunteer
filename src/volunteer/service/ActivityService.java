package volunteer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

import volunteer.dao.ActivityDAO;
import volunteer.dao.IActivityDAO;
import volunteer.po.ActInfo;
import volunteer.po.ActReq;
import volunteer.po.ActReqPK;
import volunteer.po.Admin;

public class ActivityService implements IActivityService{

	private List list = new ArrayList<>();
	private ActInfo info = new ActInfo();
	private List reqList = new ArrayList<>();
	private IActivityDAO dao ;



	public IActivityDAO getDao() {
		return dao;
	}

	public void setDao(IActivityDAO dao) {
		this.dao = dao;
	}

	/*
	 * @产生10位随机字符串
	 */
	public String getRandomString() {
		// 定义一个字符串（A-Z，a-z，0-9）即62位；
		String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		// 由Random生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 长度为几就循环几次
		for (int i = 0; i < 10; ++i) {
			// 产生0-61的数字
			int number = random.nextInt(62);
			// 将产生的数字通过length次承载到sb中
			sb.append(str.charAt(number));
		}
		// 将承载的字符转换成字符串
		return sb.toString();
	}

	/*
	 * @添加活动 传入参数info 活动基本信息 reqlist 活动要求列表 college 学院 添加成功返回true 添加失败返回false
	 */
	public boolean addActivity(ActInfo info) {
		System.out.println("添加活动service");
		if(dao.addActivity(info)!=null)
			return true;
		else return false;
	}

	/*
	 * @查找college的所有活动 传入参数 college 所属学院 按照方法内部的格式返回
	 */
	public List getActInfoList(String college) {

		list = dao.getActInfoList(college);
		List temp = dao.endNotUp(college);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date now = new java.util.Date();
		System.out.println("查找college的所有活动:" + ((ActInfo) list.get(0)).getAname());
		for (int i = 0; i < list.size(); i++) {
			info = (ActInfo) list.get(i);
			String date = info.getAdate().trim();
			java.util.Date acttime;
			try {
				acttime = sdf.parse(date);
				if (now.after(acttime)) {
					info.setAstate("已结束");
				} else if (now.before(acttime)) {
					info.setAstate("未开始");
				} else
					info.setAstate("正在进行");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	/*
	 * @根据活动编号查找活动信息 传入参数 Ano 活动编号 按照方法内部的格式返回
	 */
	public String getActInfo(String Ano) {
		System.out.println("GETACTINFOSERVICE:" + Ano);
		info = dao.getActInfo(Ano);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = info.getAdate().trim();
		java.util.Date acttime = null;
		try {
			acttime = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		info.setAdate(sdf.format(acttime));
		System.out.println("已招：" + info.getActreqs().iterator().next().getDoneAccount());
		System.out.println("TESTSSSS:" + info.getAname());
		Gson gson = new Gson();
		// JSONObject json=new JSONObject(info);
		// json.put("act", info);
		System.out.println("JSONSTRING:" + info.getActreqs().size());
		System.out.println("JSONSSS:" + info.toJson());
		return info.toJson();
	}

	/*
	 * @根据活动编号查找活动要求 传入参数 Ano 活动编号 按照方法内部的格式返回
	 */
	public String getActReq(String Ano) {
		for (int i = 0; i < 2; i++) {
			ActReq req = new ActReq();
			ActReqPK pk = new ActReqPK();
			pk.setAjobstate(i + "");
			pk.setAno(Ano);
			pk.setAtime("asdfa");
			req.setPk(pk);
			req.setAjobcount(i);
			req.setDoneAccount(i);
			reqList.add(req);
		}
		Gson gson = new Gson();
		return gson.toJson(reqList);
	}

	/*
	 * @根据活动编号删除活动 删除成功返回1 删除失败返回0
	 */
	public String deleteActivity(String Ano) {
		System.out.println("根据活动编号删除活动");
		return dao.deleteActivity(Ano);
	}

	/*
	 * @返回活动已经结束且已经上传工时表的活动 传入参数：college 学院 按照方法内部的格式返回
	 * 
	 */
	public List endAndUp(String college) {
		list.clear();
		list = dao.endAndUp(college);
		System.out.println("返回活动已经结束且已经上传工时表的活动:" + list.size());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			info = (ActInfo) list.get(i);
			String date = info.getAdate().trim();
			java.util.Date acttime = null;
			try {
				acttime = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			info.setAdate(sdf.format(acttime));
		}
		return list;
	}

	/*
	 * @获取某学院已结束但未上传工时表的活动列表 传入参数：college 学院 按照方法内部的格式返回
	 */
	public List endNotUp(String college) {
		list.clear();
		List temp = dao.endNotUp(college);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date now = new java.util.Date();
		for (int i = 0; i < temp.size(); i++) {
			info = (ActInfo) temp.get(i);

			String date = info.getAdate().trim();
			java.util.Date acttime = null;
			try {

				acttime = sdf.parse(date);

				if (now.after(acttime)) {
					list.add(info);
					info.setAdate(sdf.format(acttime));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*
	 * @将活动状态设置为已上传工时表 传入参数Ano 活动编号 设置成功返回true 设置失败返回false
	 */
	public boolean setState(String Ano) {
		if (dao.setState(Ano).equals("success"))
			return true;
		else
			return false;
	}
}
