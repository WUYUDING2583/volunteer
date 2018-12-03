package volunteer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import volunteer.dao.FrontDAO;
import volunteer.front.getAccessToken;
import volunteer.po.ActBase;
import volunteer.po.ActInfo;
import volunteer.po.ActReqPK;
import volunteer.po.Apply;
import volunteer.po.ApplyPK;
import volunteer.po.ManHour;
import volunteer.po.User;
import volunteer.po.ViewApply;
import volunteer.po.ViewApplyPK;
import volunteer.po.Vtime;

public class FrontService {
	private static String openid;

	public String getOpenId() {
		return openid;
	}

	public void setOpenId(String openid) {
		this.openid = openid;
	}

	public FrontService() {
		// TODO 自动生成的构造函数存根
	}

	FrontDAO dao = new FrontDAO();

	// 判断是否已经注册
	public String isRegister(String openid) {
		String result = null;
		setOpenId(openid);
		JSONObject json = new JSONObject();
		try {
			User u = null;
			List list = dao.isRegister(FrontService.openid);
			if (list != null)
				u = (User) list.get(0);
			if (u != null) {
				json.put("No", u.getNo());
				json.put("flag", "Yes");
				result = json.toString();
			} else {
				result = "No";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isRegisterResult:" + result);
		return result;

	}

	// 注册
	public String register(User user) {
		user.setOpenid(FrontService.openid);
		user.setLeader(0);
		return dao.register(user);
	}

	// 获取活动列表
	public String viewActivity(String flag) throws ParseException {
		List list = dao.viewActivity();
		ActBase info;
		ArrayList<ActBase> infolist = new ArrayList<ActBase>();
		for (int i = 0; i < list.size(); i++) {
			String temp_str;
			info = (ActBase) list.get(i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = info.getAdeadline().trim();
			java.util.Date acttime = sdf.parse(date);
			java.util.Date now = new java.util.Date();
			temp_str = sdf.format(now);
			if (now.before(acttime)) {
				info.setAstate("正在报名");
			} else if (now.after(acttime)) {
				info.setAstate("报名截止");
			}
			if (flag.equals("new")) {
				if (info.getAstate().equals("正在报名")) {
					infolist.add(info);
				}
			}
			if (flag.equals("history")) {
				if (info.getAstate().equals("报名截止")) {
					infolist.add(info);
				}
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(infolist);
		if (infolist.isEmpty())
			System.out.println("AAAAAAAA:" + result);
		return result;
	}

	// 获取活动时间段
	public List<String> getAtime(String Ano) {
		return dao.getAtime(Ano);
	}

	public String getActivityDetail(ViewApplyPK pk) {
		ViewApply view = dao.viewApply(pk);
		String resultstring = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			java.util.Date endtime = sdf.parse(pk.getAdeadline());
			String nowtime = sdf.format(new java.util.Date());
			java.util.Date now;
			now = sdf.parse(nowtime);
			long time = endtime.getTime() - now.getTime();
			System.out.println("END:" + endtime);
			System.out.println("NOW:" + now);
			if (time > 0) {
				view.setTime(time);
			} else {
				view.setTime(0);
			}
			List<String> Atime=dao.getAtime(pk.getAno());
			view.setAtime(Atime);
			List temp;
			
			JSONObject result = new JSONObject(view);
			for(int i=0;i<Atime.size();i++)
			{ 
				JSONArray json1=new JSONArray();
				temp=dao.getAjobstate(pk.getAno(),Atime.get(i));
				Iterator it = temp.iterator();
		        while(it.hasNext()){
		            Object[] results = (Object[]) it.next();
		            JSONObject json = new JSONObject();
					json.put("Ajobstate", results[0]);
					json.put("surplus", results[1]);
					json1.put(json);
					System.out.println("JSON1:" + json1.toString());
		            System.out.println(results[0]+","+results[1]);
		        }
				result.put(Atime.get(i), json1);
				System.out.println("RESULT:"+result.toString());
			}
			resultstring=result.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultstring;
	}

	// 报名
	public String apply(Apply apply, String Address, String Adate, String formId) throws IOException, SQLException {
		String isUser = getUserInfo(apply.getPk().getNo());
		if (isUser == null) {
			return "noRegister";
		}
		String result = isApply(apply);
		if (result.equals("noApply")) {
			result = dao.apply(apply);
			if (result.equals("success")) {
				System.out.println("报名成功");
				updateActReq(
						new ActReqPK(apply.getPk().getAno(), apply.getPk().getAtime(), apply.getPk().getAjobstate()),
						"add");
				JSONObject identify = returnIdentify(apply.getPk().getNo());
				System.out.println("Aname:" + apply.getAname());
				System.out.println("Address:" + Address);
				System.out.println("Adate:" + Adate);
				
				int resultOfS = sendMessage(identify, apply.getAname(), Address, Adate, formId);// 返回信息发送状态码
				int n = 0;// 发送次数
				while (resultOfS == 40001)// 若发生错误则重新发送信息
				{
					new getAccessToken().getAcessToken("wxe70a657861b2349d", "b9364b42fb126408c8a8c3786e19fcb4");
					identify = returnIdentify(apply.getPk().getNo());
					resultOfS = sendMessage(identify, apply.getAname(), Address, Adate, formId);
					System.out.println("sendMessage success:" + resultOfS + " N:" + n);
					n++;
				}
				System.out.println("sendMessage success:" + resultOfS + " N:" + n);
				
			}
			return "success";
		} else
			return result;
	}

	// 返回用户openid及access_token
	public JSONObject returnIdentify(String No) throws IOException, SQLException {
		JSONObject json = new JSONObject();
		String access_token = null;
		BufferedReader buf = new BufferedReader(new FileReader(
				"C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\Volunteer\\access_token.txt"));
		while (buf.ready()) {
			access_token = buf.readLine();
		}
		System.out.println("RETURN:" + access_token);
		buf.close();
		json.put("access_token", access_token);
		String openid = dao.returnOpenid(No);
		json.put("openid", openid);
		return json;
	}

	// 查找工时信息
	public String searchManHour(String No) {
		List list = dao.searchManHour(No);
		if (list != null) {
			Vtime vtime = new Vtime();
			ManHour manhour;
			float all = 0;
			for (int i = 0; i < list.size(); i++) {
				manhour = (ManHour) list.get(i);
				all += manhour.getAvtime();
			}
			vtime.setManhour(list);
			vtime.setAllvtime(all);
			JSONObject result = new JSONObject(vtime);
			return result.toString();
		} else
			return "无工时信息";
	}

	// 取消报名
	public String cancelApply(ApplyPK pk) {
		int count = dao.deleteApply(pk);
		String result = "";
		if (count > 0) {
			if (updateActReq(new ActReqPK(pk.getAno(), pk.getAtime(), pk.getAjobstate()), "down") > 0)
				result = "success cancel";
			else
				result = "cancel fail";
		} else
			result = "error";
		System.out.println("CANCELAPPLYSERVICE:"+result);
		return result;
	}

	// 获取活动日期
	public String getAdate(String Ano) {
		return dao.getAdate(Ano);
	}

	// 获取报名信息
	public String getApply(String No) throws ParseException {
		List list = dao.getApply(No);
		JSONArray jsonarray = new JSONArray();
		String date = "";
		for (int i = 0; i < list.size(); i++) {
			Apply a = (Apply) list.get(i);
			date = getAdate(a.getPk().getAno());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date now = new java.util.Date();
			java.util.Date acttime = sdf.parse(date);
			if (now.before(acttime)) {
				a.setState("未开始");
			} else if (now.after(acttime)) {
				a.setState("已结束");
			}
			JSONObject json = new JSONObject(a);
			jsonarray.put(json);
		}
		return jsonarray.toString();
	}

	// 更新用户信息
	public String updateUserMess(User user) {
		String result;
		if (dao.updateUserMess(user) > 0)
			result = "更新成功";
		else
			result = "更新失败";
		System.out.println("UPDATE:" + result);
		return result;
	}

	// 判断是否已报名
	public String isApply(Apply apply) {
		int result = dao.isApply(apply);
		if (result == 0)
			return "noApply";
		else if (result == 1)
			return "alreadyApply";
		else
			return "anotherTime";

	}

	// 获取用户信息
	public String getUserInfo(String No) {
		List list = dao.getUserInfo(No);
		User u = (User) list.get(0);
		String result;
		System.out.println(u.getName());
		if (u != null) {
			JSONObject json = new JSONObject(u);
			result = json.toString();
		} else
			result = "无用户信息";
		return result;
	}

	// 发送报名成功信息
	public int sendMessage(JSONObject identify, String Aname, String Address, String Adate, String formId) {
		URL connect;
		String access_token = identify.getString("access_token");
		String openid = identify.getString("openid");
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + access_token;

		StringBuffer data = new StringBuffer();
		try {
			connect = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);// post不能使用缓存
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			JSONObject paramsStr = new JSONObject();
			paramsStr.put("touser", openid);
			paramsStr.put("access_token", access_token);
			paramsStr.put("template_id", "IpSJHzkmkRonuZiQ0qKSQX4wJzTyNMLVWtNKDFAh6Zo");
			paramsStr.put("form_id", formId);
			paramsStr.put("page", "pages/more/alreadyApply");
			JSONObject dataP = new JSONObject();
			JSONObject keyword1 = new JSONObject();
			keyword1.put("value", Aname);
			keyword1.put("color", "black");
			dataP.put("keyword1", keyword1);
			JSONObject keyword2 = new JSONObject();
			keyword2.put("value", Address);
			keyword2.put("color", "black");
			dataP.put("keyword2", keyword2);
			JSONObject keyword3 = new JSONObject();
			keyword3.put("value", Adate);
			keyword3.put("color", "black");
			dataP.put("keyword3", keyword3);
			paramsStr.put("data", dataP);
			paramsStr.put("emphasis_keyword", "keyword1.DATA");
			// 拼接Post 请求的参数
			paramout.write(paramsStr.toString());
			System.out.println("Data:" + dataP.toString());
			System.out.println("ALL:" + paramsStr.toString());
			paramout.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}

			paramout.close();
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject o = new JSONObject(data.toString());
		return o.getInt("errcode");
	}

	// 更新已报名人数
	public int updateActReq(ActReqPK pkey, String flag) {
		int result=dao.updateActReq(pkey, flag);
		if ( result> 0)
			System.out.println("更新已报名人数成功");
		return result;
	}
}
