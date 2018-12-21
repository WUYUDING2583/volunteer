package volunteer.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;

import volunteer.po.ActInfo;
import volunteer.po.ManHour;
import volunteer.po.ManHourPK;
import volunteer.po.User;
import volunteer.po.Vtime;
import volunteer.service.VtimeService;

public class VtimeAction {

	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";

	private ActInfo info;
	private Vtime vtime;
	private List manhourList;
	// private User manhour;
	private ManHour manhour;
	private ManHourPK pk;
	private InputStream inputStream;
	private String message;

	// 根据学号查询工时记录
	public String vtimeSearch() {
		String No = "";
		System.out.println("根据学号查询工时记录:");

		try {
			System.out.println("No:" + pk.getNo());
			No = pk.getNo();
			System.out.println("No:" + No);
		} catch (Exception e) {
			e.printStackTrace();
		}

		VtimeService vtimeSer = new VtimeService();
		manhourList = vtimeSer.vtimeSearch(No);
		Gson gson = new Gson();
		try {
			inputStream = new ByteArrayInputStream(gson.toJson(manhourList).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据活动日期名称添加工时记录
	public String addVtime() {
		System.out.println("根据活动日期名称添加工时记录");
		String Aname = pk.getAname();
		String Adate = pk.getAdate();
		String No = pk.getNo();
		System.out.println("ANAME:"+Aname+"  ADATE:"+Adate+"  No:"+No);
		if(manhour!=null)System.out.println("Aname");
		if(manhour.getAvtime()==0)System.out.println("SFSSFSFS");
		float vtime = manhour.getAvtime();
		System.out.println("Aname:" + Aname + "\nAdate:" + Adate + "\nNo:" + No + "\nvtime:" + vtime);
		VtimeService vtimeSer = new VtimeService();
		message = vtimeSer.addVtime(manhour);
		try {
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据活动名称日期学号，修改工时
	public String alterVtime() {
		System.out.println("根据活动名称日期学号，修改工时");
		String Aname = pk.getAname();
		String Adate = pk.getAdate();
		String No = pk.getNo();
		if(manhour!=null)System.out.println("ALTERVTIME");
		float vtime = manhour.getAvtime();
		// System.out.println("Aname:" + Aname + "\nAdate:" +
		// Adate+"\nNo:"+No+"\nvtime:"+vtime);
		VtimeService vtimeSer = new VtimeService();
		if (vtimeSer.alertVtime(manhour)) {
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
		System.out.println("根据活动名称日期学号删除工时记录");
		System.out.println("DELETE:"+pk.getAname()+"  "+pk.getAdate()+"  "+pk.getNo());
		
		float vtime = manhour.getAvtime();
		// System.out.println("Aname:" + Aname + "\nAdate:" + Adate+"\nNo:"+No);
		VtimeService vtimeSer = new VtimeService();
		if (vtimeSer.vtimeDelete(pk)) {
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
		System.out.println("根据活动名称日期返回该活动所有志愿者工时信息");
		VtimeService vtimeSer = new VtimeService();
		String Aname = pk.getAname();
		String Adate = pk.getAdate();
		manhourList = vtimeSer.vtimeDetail(Aname, Adate);
		Gson gson = new Gson();
		try {
			inputStream = new ByteArrayInputStream(gson.toJson(manhourList).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setManHour(ManHour manhour) {
		this.manhour = manhour;
	}

	public ManHour getManHour() {
		return manhour;
	}

	public void setUserList(List manhourList) {
		this.manhourList = manhourList;
	}

	public List getUserList() {
		return manhourList;
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

	public void setPk(ManHourPK pk) {
		this.pk = pk;
	}

	public ManHourPK getPk() {
		return pk;
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
