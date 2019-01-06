package volunteer.action;

/*<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE validators PUBLIC
"-//Apache Struts//XWork Validator 1.0.2//EN"
"http://struts.apache.org/dtds/xwork-validator-1.0.2.dtd">
<validators>
	<field name="admin.id">
		<field-validator type="requiredstring">
			<param name="trim">true</param>
			<message>身份证号不能为空</message>
		</field-validator>
	</field>
	<field name="admin.password">
		<field-validator type="requiredstring">
			<param name="trim">true</param>
			<message>密码不能为空</message>
		</field-validator>
	</field>
</validators>
*/
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.opensymphony.xwork2.ActionSupport;

import volunteer.po.ActInfo;
import volunteer.po.ActReq;
import volunteer.po.ActReqPK;
import volunteer.po.Admin;
import volunteer.service.ActivityService;
import volunteer.service.FileService;
import volunteer.service.IActivityService;

public class ActivityAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";

	private String jsonData;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	ServletContext application = ServletActionContext.getServletContext();
	HttpSession session = request.getSession();

	private String message;
	private InputStream inputStream;
	private List infoList;
	private ActInfo info;
	private List reqList;
	private IActivityService activityservice = null;

	public void setActivityservice(IActivityService activityservice) {
		this.activityservice = activityservice;
	}

	public IActivityService getActivityservice() {
		return activityservice;
	}

	// 将活动状态设置为已上传工时表
	public String setState() {
		System.out.println("将活动状态设置为已上传工时表");
		// ActivityService actSer = new ActivityService();
		String Ano = info.getAno();
		if (activityservice.setState(Ano))
			return SUCCESS;
		else
			return FAIL;

	}

	// 获取某学院已上传工时活动列表
	public String endAndUp() {
		System.out.println("获取某学院已上传工时活动列表");
		Admin admin = (Admin) session.getAttribute("Admin");
		// ActivityService actSer = new ActivityService();
		String college = admin.getCollege();
		infoList = activityservice.endAndUp(college);
		return SUCCESS;
	}

	// 获取某学院已结束但未上传工时表的活动列表
	public String endNotUp() {
		System.out.println("获取某学院已结束但未上传工时表的活动列表");
		Admin admin = (Admin) session.getAttribute("Admin");
		// ActivityService actSer = new ActivityService();
		String college = admin.getCollege();
		infoList = activityservice.endNotUp(college);
		return SUCCESS;
	}

	// 根据活动编号删除活动
	public String deleteActivity() {

		String Ano = info.getAno();
		System.out.println("根据活动编号删除活动:" + Ano);
		// ActivityService actSer = new ActivityService();
		message = activityservice.deleteActivity(Ano);
		return SUCCESS;
	}

	// 根据活动编号查看活动具体信息
	public String showActivity() {
		System.out.println("根据活动编号查看活动具体信息");
		String Ano = info.getAno();
		// ActivityService actSer = new ActivityService();
		message = activityservice.getActInfo(Ano);
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("message:" + message);
		return SUCCESS;
	}

	// 查看所有活动
	public String myActivity() {
		System.out.println("查看所有活动");
		Admin admin = (Admin) session.getAttribute("Admin");
		// ActivityService actSer = new ActivityService();
		String college = admin.getCollege();
		infoList = activityservice.getActInfoList(college);
		return SUCCESS;
	}

	// 添加活动
	public String addActivity() {
		System.out.println("添加活动");

		boolean flag = true;
		message = convertToInfo();
		if (!message.equals("1")) {
			flag = false;
		}

		String Adate = info.getAdate();
		System.out.println("Adate:" + Adate);

		String Adeadline = info.getAdeadline();
		System.out.println("Adeadline:" + Adeadline);

		Set<ActReq> req = info.getActreqs();
		for (ActReq request : req) {
			System.out.println("Atime:" + request.getPk().getAtime().split("-")[0] + " "
					+ request.getPk().getAtime().split("-")[1]);
		}

		Date now = new Date();
		System.out.println(now);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tDateFormat = new SimpleDateFormat("HH:mm");
		try {
			Date adate = sDateFormat.parse(Adate);
			if (adate.before(now)) {
				message = "活动日期不能在今天之前";
				flag = false;
			}
			Date adeadline = sDateFormat.parse(Adeadline);
			if (adeadline.after(adate)) {
				message = "截止日期不能再活动日期之后";
				flag = false;
			}
			for (ActReq request : req) {
				Date start = tDateFormat.parse(request.getPk().getAtime().split("-")[0]);
				Date end = tDateFormat.parse(request.getPk().getAtime().split("-")[1]);
				if (start.after(end)) {
					message = "活动开始时间段不能再结束时间之后";
					flag = false;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			if (activityservice.addActivity(info))
				message = "活动添加成功";
			else
				message = "活动添加失败，系统错误";
		}
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String convertToInfo() {
		// ActivityService actSer = new ActivityService();
		ActInfo info=new ActInfo();
		String Ano = activityservice.getRandomString();
		Admin admin = (Admin) session.getAttribute("Admin");
		String college = admin.getCollege();
		Set<ActReq> reqList = new HashSet<ActReq>();
		info.setAno(Ano);
		String message = "1";
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		temp_str = sdf.format(dt);
		try {
			JsonParser parser = new JsonParser(); // 创建json解析器
			System.out.println("action" + jsonData);
			JsonObject object = (JsonObject) parser.parse(jsonData); // 创建JsonObject对象
			JsonArray array = object.get("activity").getAsJsonArray(); // 得到为json的数组
			for (int i = 0; i < array.size(); i++) {
				JsonObject subObject = array.get(i).getAsJsonObject();
				if (i == 0) {
					info.setAname(subObject.get("Aname").getAsString());
					info.setAdate(subObject.get("Adate").getAsString());
					info.setAddress(subObject.get("Address").getAsString());
					info.setArequest(subObject.get("Arequest").getAsString());
					info.setPublishTime(temp_str);
					info.setAdeadline(subObject.get("Adeadline").getAsString());
					info.setPicture(subObject.get("picture").getAsString());
					info.setCollege(admin.getCollege());
					info.setPublishName(admin.getAccount());
					info.setLat(subObject.get("lat").getAsDouble());
					info.setLng(subObject.get("lng").getAsDouble());
				}
				JsonArray arr = subObject.get("Ajob").getAsJsonArray(); // 得到为json的数组
				for (int j = 0; j < arr.size(); j++) {
					ActReq req = new ActReq();
					ActReqPK pk = new ActReqPK();
					pk.setAno(Ano);
					pk.setAtime(subObject.get("Atime").getAsString());
					JsonObject obj = arr.get(j).getAsJsonObject();
					pk.setAjobstate(obj.get("Ajobstate").getAsString());
					req.setPk(pk);
					try {
						req.setAjobcount(obj.get("Ajobcount").getAsInt());
					} catch (Exception e) {
						message = "需求人数必须为数字";
						e.printStackTrace();
						System.out.println(message);
					}
					reqList.add(req);
				}
				info.setActreqs(reqList);
				info.setCollege(college);
				setInfo(info);
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public void setInfo(ActInfo info) {
		this.info = info;
	}

	public ActInfo getInfo() {
		return info;
	}

	public void setReqList(List reqList) {
		this.reqList = reqList;
	}

	public List getReqList() {
		return reqList;
	}

	public void setInfoList(List infoList) {
		this.infoList = infoList;
	}

	public List getInfoList() {
		return infoList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getJsonData() {
		return jsonData;
	}
}
