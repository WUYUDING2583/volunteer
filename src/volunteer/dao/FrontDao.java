package volunteer.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.*;

import volunteer.front.getAccessToken;
import volunteer.modal.ActInfo;
import volunteer.modal.User;
import volunteer.modal.Vtime;

public class FrontDao extends BaseDao {
	private static String openid;

	public String getOpenId() {
		return openid;
	}

	public void setOpenId(String openid) {
		this.openid = openid;
	}

	// 获得所有志愿活动列表
	public ArrayList<ActInfo> getActInfoList(String flag) {
		String sql = "select Ano,Aname,Adate,Address,publishTime,Arequest,picture,Adeadline from ActivityInfo group by Ano,Aname,publishTime,Adate,Address,Arequest,picture,Adeadline";
		ArrayList<ActInfo> infoList = new ArrayList<ActInfo>();
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				ActInfo info = new ActInfo();
				String temp_str;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date = rst.getTimestamp("Adeadline").toString().trim();
				java.util.Date acttime = sdf.parse(date);
				java.util.Date now = new java.util.Date();
				temp_str = sdf.format(now);
				if (now.before(acttime)) {
					info.setAstate("正在报名");
				} else if (now.after(acttime)) {
					info.setAstate("报名截止");
				}
				// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制 ;
				if (flag.equals("new")) {
					if (info.getAstate().equals("正在报名")) {
						info.setAno(rst.getString("Ano").trim());
						info.setAname(rst.getString("Aname").trim());
						info.setAdate(rst.getString("Adate"));
						info.setPublishTime(rst.getString("publishTime"));
						info.setAddress(rst.getString("Address").trim());
						info.setArequest(rst.getString("Arequest").trim());
						info.setPicture(rst.getString("picture").trim());
						info.setAdeadline(date);
						infoList.add(info);
					}
				}
				if (flag.equals("history")) {
					if (info.getAstate().equals("报名截止")) {
						info.setAno(rst.getString("Ano").trim());
						info.setAname(rst.getString("Aname").trim());
						info.setAdate(rst.getString("Adate"));
						info.setPublishTime(rst.getString("publishTime"));
						info.setAddress(rst.getString("Address").trim());
						info.setArequest(rst.getString("Arequest").trim());
						info.setPicture(rst.getString("picture").trim());
						info.setAdeadline(date);
						infoList.add(info);
					}
				}
			}
			pstmt.close();
			conn.close();
			return infoList;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 存入用户标识
	/*
	 * public int setOpenId(String openid,String No) throws SQLException { String
	 * sql=" update Account set openid=? where No=?"; Connection conn =
	 * dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, openid); pstmt.setString(2,
	 * No); int result=pstmt.executeUpdate(); conn.close(); pstmt.close(); return
	 * result; }
	 */
	// 判断用户是否注册过
	public String isRegister(String openid) throws SQLException {
		FrontDao.openid = openid;
		String sql = "select No from Account where openid=?";
		Connection conn = dataSource.getConnection();
		String result;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, openid);
		JSONObject json = new JSONObject();
		ResultSet re = pstmt.executeQuery();
		if (re.next()) {
			json.put("No", re.getString(1));
			json.put("flag", "Yes");
			pstmt.close();
			conn.close();
			result = json.toString();
		} else {
			pstmt.close();
			conn.close();
			result = "noexsist";
		}
		return result;
	}
	//发送报名成功信息
	public int sendMessage(JSONObject identify,String Aname,String Address,String Adate,String formId)
	{
		 URL connect;
		 String access_token=identify.getString("access_token");
		 String openid=identify.getString("openid");
		 String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" +access_token ;
		 
		 StringBuffer data = new StringBuffer();  
        try {  
            connect = new URL(url);  
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();  
            connection.setRequestMethod("POST");  
            connection.setDoOutput(true); 
            connection.setDoInput(true);
            connection.setUseCaches(false);//post不能使用缓存
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            OutputStreamWriter paramout = new OutputStreamWriter(  
                    connection.getOutputStream(),"UTF-8"); 
            JSONObject paramsStr=new JSONObject();
            paramsStr.put("touser", openid);
            paramsStr.put("access_token", access_token);
            paramsStr.put("template_id", "IpSJHzkmkRonuZiQ0qKSQX4wJzTyNMLVWtNKDFAh6Zo");
            paramsStr.put("form_id", formId);
            paramsStr.put("page", "pages/more/alreadyApply");
            JSONObject dataP=new JSONObject();
            JSONObject keyword1=new JSONObject();
            keyword1.put("value", Aname);
            keyword1.put("color", "black");
            dataP.put("keyword1",keyword1);
            JSONObject keyword2=new JSONObject();
            keyword2.put("value", Address);
            keyword2.put("color", "black");
            dataP.put("keyword2", keyword2);
            JSONObject keyword3=new JSONObject();
            keyword3.put("value", Adate);
            keyword3.put("color", "black");
            dataP.put("keyword3", keyword3);
            paramsStr.put("data", dataP);
            paramsStr.put("emphasis_keyword" ,"keyword1.DATA");
            //拼接Post 请求的参数
            paramout.write(paramsStr.toString()); 
            System.out.println("Data:"+dataP.toString());
            System.out.println("ALL:"+paramsStr.toString());
            paramout.flush();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream(), "UTF-8"));  
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
        JSONObject o=new JSONObject(data.toString());
      return o.getInt("errcode"); 

	}
	// 返回用户openid及access_token
	public JSONObject returnIdentify(String No) throws IOException, SQLException {
		JSONObject json = new JSONObject();String access_token = null;
		BufferedReader buf = new BufferedReader(new FileReader("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\Volunteer\\access_token.txt"));
		while (buf.ready()) {
			 access_token = buf.readLine();
		}
		System.out.println("RETURN:"+access_token);
		buf.close();
		json.put("access_token", access_token);
		String sql = "select openid from Account where No=?";
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, No);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			json.put("openid", rst.getString("openid").trim());
		}
		conn.close();
		pstmt.close();
		rst.close();
		return json;
	}

	// 获取活动详细情况
	public String getActivityDetail(String Aname, String Ano, String Adeadline) {
		String sql = "select Aname,Adate,Address,Arequest from ActivityInfo where Aname=? and Adeadline=? and Ano=? ;";
		String sql1 = "select Atime from ActivityRequest where Ano=? group by Atime;";
		ArrayList<String> Atime = new ArrayList<String>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			JSONObject json2 = new JSONObject();

			Timestamp date = Timestamp.valueOf(Adeadline);
			java.util.Date endtime = sdf.parse(Adeadline);

			String nowtime = sdf.format(new java.util.Date());
			java.util.Date now = sdf.parse(nowtime);
			long time = endtime.getTime() - now.getTime();
			System.out.println("END:" + endtime);
			System.out.println("NOW:" + now);

			if (time > 0) {
				json2.put("time", time);
			} else {
				time = 0;
				json2.put("time", time);
			}
			System.out.println(time);
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt.setString(1, Aname);
			pstmt.setString(2, Adeadline);
			pstmt.setString(3, Ano);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				json2.put("Ano", Ano);
				json2.put("Aname", rst.getString("Aname").trim());
				json2.put("Address", rst.getString("Address").trim());
				json2.put("Adate", rst.getString("Adate".trim()));
				json2.put("Arequest", rst.getString("Arequest").trim());
				System.out.println("Adate:"+rst.getString("Adate".trim()));
			}
			pstmt1.setString(1, Ano);
			rst = pstmt1.executeQuery();
			while (rst.next()) {
				Atime.add(rst.getString("Atime").trim());
			}
			json2.put("Atime", Atime);
			for (int i = 0; i < Atime.size(); i++) {
				JSONArray json3 = new JSONArray();
				sql = "select Ajobstate,Ajobcount-DoneAccount from ActivityRequest where Ano=? and Atime=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, json2.getString("Ano"));
				pstmt.setString(2, Atime.get(i));
				System.out.println("ATIME  " + Atime.get(i));
				rst = pstmt.executeQuery();
				while (rst.next()) {
					JSONObject json = new JSONObject();
					System.out.println(Atime.get(i) + ":" + rst.getString("Ajobstate").trim());
					json.put("Ajobstate", rst.getString("Ajobstate").trim());
					json.put("surplus", rst.getString(2));
					json3.put(json);
					System.out.println("JSON3:" + json3.toString());
				}

				json2.put(Atime.get(i), json3);
			}
			pstmt.close();
			pstmt1.close();
			conn.close();
			System.out.println("JSON2");
			System.out.println(json2.toString());
			return json2.toString();
		} catch (SQLException | ParseException se) {
			se.printStackTrace();
			return null;
		}
	}
	public String isApply(String No,String Ano,String Aname,String Ajobstate,String Atime) throws SQLException
	{
		String sql="select isnull((select top(1) 1 from Apply where No=? and Ano=? and Aname=? and Ajobstate=? and Atime=?), 0)";
		String result="";
		int flag = 0;
		PreparedStatement pstmt=null;
		Connection conn=dataSource.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, No);
		pstmt.setString(2, Ano);
		pstmt.setString(3, Aname);
		pstmt.setString(4, Ajobstate);
		pstmt.setString(5, Atime);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next())
		{
			flag=rst.getInt(1);
		}
		if(flag==1)
		{
			result= "alreadyApply";
		}
		else
		{
			String sql1="select isnull((select top(1) 1 from Apply where No=? and Ano=? and Atime=?), 0)";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, No);
			pstmt.setString(2, Ano);
			pstmt.setString(3, Atime);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				flag=rst.getInt(1);
			}
			if(flag==1)
				result="anotherTime";
			else result="noApply";
		}
		pstmt.close();
		conn.close();
		rst.close();
		return result;
	}
	// 报名
	public String apply(String No, String Ano, String Aname, String Ajobstate, String Atime,String Address,String Adate,String formId) throws SQLException {
		User user = getUserInfo(No);
		System.out.println("NAME:" + user.getName());
		if (user.getName() == null) {
			return "noRegister";
		}
		String result=isApply(No,Ano,Aname,Ajobstate,Atime);
		if(result.equals("noApply"))
		{
			
			String sql = "insert into Apply values(?,?,?,?,?,'','',0,0);";
			int count = 0;
			System.out.println("No:" + No);
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, No);
				pstmt.setString(2, Ano);
				pstmt.setString(3, Aname);
				pstmt.setString(4, Ajobstate);
				pstmt.setString(5, Atime);
				count = pstmt.executeUpdate();
				System.out.println("COUNT:" + count);
				pstmt.close();
				if (count > 0) {
					sql = "update ActivityRequest  set DoneAccount=DoneAccount+1 where Ano=? and Atime=? and Ajobstate=?;";
					pstmt = conn.prepareStatement(sql);
					// 关闭自动提交，开启事务
					// conn.setAutoCommit(false);
					pstmt.setString(1, Ano);
					pstmt.setString(2, Atime);
					pstmt.setString(3, Ajobstate);
					count = pstmt.executeUpdate();
					JSONObject identify=returnIdentify(No);
					System.out.println("Aname:"+Aname);
					System.out.println("Address:"+Address);
					System.out.println("Adate:"+Adate);
					int resultOfS=sendMessage(identify,Aname,Address,Adate,formId);
					int n=0;
					while(resultOfS==40001)
					{	
					new getAccessToken().getAcessToken("wxe70a657861b2349d", "b9364b42fb126408c8a8c3786e19fcb4") ;
					 identify=returnIdentify(No);
					resultOfS=sendMessage(identify,Aname,Address,Adate,formId);
					System.out.println("sendMessage success:"+resultOfS+" N:"+n);
					n++;
					}
					System.out.println("sendMessage success:"+resultOfS+" N:"+n);
				}
				// 事务成功提交
				// conn.commit();
				// conn.setAutoCommit(true);
				System.out.println("COUNT1:" + count);
				return "success";
			} catch (SQLException | IOException se) {
				/*
				 * try { //事务失败回滚 conn.rollback(); } catch (SQLException e1) {
				 * e1.printStackTrace(); }
				 */
				se.printStackTrace();
				System.out.println("COUNT2:" + count);
				return "fail";
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else return result;
	}

	// 获取报名信息
	public String getApply(String No) throws SQLException, ParseException {
		String sql = "select Aname,Ajobstate,Atime,Ano from Apply where No=?";
		System.out.println("NO:" + No);
		JSONArray jsonarray = new JSONArray();
		Connection conn;
		String result = "";
		conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, No);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			String sql1 = "select Adate from ActivityInfo where Ano=?;";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rst.getString("Ano"));
			System.out.println("ANO:" + rst.getString("Ano"));
			ResultSet rst1 = pstmt.executeQuery();
			String date = "";
			if (rst1.next()) {
				date = rst1.getDate("Adate").toString().trim();
				rst1.close();
			}
			JSONObject json = new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date now = new java.util.Date();
			java.util.Date acttime = sdf.parse(date);
			if (now.before(acttime)) {
				json.put("sate", "未开始");
			} else if (now.after(acttime)) {
				json.put("sate", "已结束");
			}
			json.put("Aname", rst.getString("Aname").trim());
			json.put("Ajobstate", rst.getString("Ajobstate").trim());
			json.put("Atime", rst.getString("Atime").trim());
			json.put("Ano", rst.getString("Ano"));
			jsonarray.put(json);
		}
		pstmt.close();
		conn.close();
		rst.close();

		result = jsonarray.toString();
		return result;
	}

	// 取消报名
	public String cancelApply(String No, String Ano, String Ajobstate, String Atime) {

		String sql2 = "update ActivityRequest set DoneAccount=DoneAccount-1 where Ano=? and Atime=? and Ajobstate=?;";
		String sql3 = "delete from Apply where No=? and Ano=? and Ajobstate=? and Atime=?";
		String result = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, No);
			pstmt.setString(2, Ano);
			pstmt.setString(3, Ajobstate);
			pstmt.setString(4, Atime);
			if (pstmt.executeUpdate() > 0) {

				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, Ano);
				pstmt.setString(2, Atime);
				pstmt.setString(3, Ajobstate);
				if (pstmt.executeUpdate() > 0)
					result = "success cancel";
				else
					result = "cancel fail";
			}
			pstmt.close();

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}

	}

	// 用户注册
	public String register(User user) throws IOException {
		System.out.println("REGISTER");

		System.out.println("FSFSFSFF:" + getOpenId());
		String sql = "insert into Account(Name,Sex,No,Class,Vno,Phone,isLeader,openid) values(?,?,?,?,?,?,0,?);";
		int count = 0;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSex());
			pstmt.setString(3, user.getNo());
			pstmt.setString(4, user.getCclass());
			pstmt.setString(5, user.getVno());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, FrontDao.openid);
			System.out.println(user.getNo());
			count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			if (count > 0)
				return "成功";
			else
				return "不知为何失败";
		} catch (SQLException se) {
			se.printStackTrace();
			return se.getMessage();
		}
	}

	// 修改用户信息
	public int alterInfo(User user) {
		String sql = "update Account set Name=? , Sex=? , Class=? , Vno=? , Phone=?  where No=?";
		int count = 0;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSex());
			pstmt.setString(3, user.getCclass());
			pstmt.setString(4, user.getVno());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getNo());
			count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}

	// 返回用户信息
	public User getUserInfo(String No) {
		String sql = "select * from Account where No=?;";
		User user = new User();
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, No);
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				user.setNo(No);
				user.setSex(rst.getString("Sex").trim());
				user.setName(rst.getString("Name").trim());
				user.setClass(rst.getString("Class").trim());
				user.setVno(rst.getString("Vno").trim());
				user.setPhone(rst.getString("Phone").trim());
			}
			pstmt.close();
			conn.close();
			return user;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 返回用户工时信息
	public ArrayList<Vtime> getVtime(String No) {
		String sql = "select * from Vtime where No=?;";
		ArrayList<Vtime> list = new ArrayList<Vtime>();
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, No);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				Vtime v = new Vtime();
				v.setAname(rst.getString("Aname").trim());
				v.setAdate(rst.getDate("Adate").toString().trim());
				v.setAvtime(rst.getFloat("Avtime"));
				list.add(v);
			}
			sql = "select sum(Avtime) from Vtime where No=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, No);
			rst = pstmt.executeQuery();
			if (rst.next()) {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setAllvtime(rst.getFloat(1));
				}
			}
			pstmt.close();
			conn.close();
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
}
