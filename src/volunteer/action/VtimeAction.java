package volunteer.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;

import volunteer.po.ActInfo;
import volunteer.po.User;
import volunteer.po.Vtime;
import volunteer.service.VtimeService;

public class VtimeAction {

	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";

	private ActInfo info;
	private Vtime vtime;
	private List userList;
	private User user;
	private InputStream inputStream;
	private String message;

	//根据学号查询工时记录
	public String vtimeSearch() {
		String No=user.getNo();
		System.out.println("No:"+No);
		VtimeService vtimeSer = new VtimeService();
		userList=vtimeSer.vtimeSearch(No);
		Gson gson=new Gson();
		try {
			inputStream = new ByteArrayInputStream(gson.toJson(userList).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据活动日期名称添加工时记录
	public String addVtime() {
		String Aname = user.getAname();
		String Adate = user.getAdate();
		String No = user.getNo();
		float vtime=user.getVtime();
		System.out.println("Aname:" + Aname + "\nAdate:" + Adate+"\nNo:"+No+"\nvtime:"+vtime);
		VtimeService vtimeSer = new VtimeService();
		message =vtimeSer.addVtime(Aname, Adate, No, vtime);
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//根据活动名称日期学号，修改工时
	public String alterVtime() {
		String Aname = user.getAname();
		String Adate = user.getAdate();
		String No = user.getNo();
		float vtime=user.getVtime();
		//System.out.println("Aname:" + Aname + "\nAdate:" + Adate+"\nNo:"+No+"\nvtime:"+vtime);
		VtimeService vtimeSer = new VtimeService();
		if (vtimeSer.alertVtime(Aname, Adate, No, vtime)) {
			message = "修改成功";
		} else
			message = "0";
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
		
	}
	// 根据活动名称日期学号删除工时记录
	public String vtimeDelete() {
		String Aname = user.getAname();
		String Adate = user.getAdate();
		String No = user.getNo();
		//System.out.println("Aname:" + Aname + "\nAdate:" + Adate+"\nNo:"+No);
		VtimeService vtimeSer = new VtimeService();
		if (vtimeSer.vtimeDelete(Aname, Adate, No)) {
			message = "删除成功";
		} else
			message = "0";
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据活动名称日期返回该活动所有志愿者工时信息
	public String vtimeDetail() {
		VtimeService vtimeSer = new VtimeService();
		String Aname = user.getAname();
		String Adate = user.getAdate();
		userList = vtimeSer.vtimeDetail(Aname, Adate);
		Gson gson = new Gson();
		try {
			inputStream = new ByteArrayInputStream(gson.toJson(userList).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List getUserList() {
		return userList;
	}

	public void setInfo(ActInfo info) {
		this.info = info;
	}

	public ActInfo getInfo() {
		return info;
	}

	public void setVtime(Vtime vtime) {
		this.vtime = vtime;
	}

	public Vtime getVtime() {
		return vtime;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
