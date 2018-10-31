package volunteer.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class getOpenId {

	
	
	public getOpenId() {
		// TODO 自动生成的构造函数存根
	}
	public String getOpenId(String JSCODE) throws IOException
	{
	System.out.println(JSCODE);
	String url="https://api.weixin.qq.com/sns/jscode2session?appid=wxe70a657861b2349d&secret=b9364b42fb126408c8a8c3786e19fcb4&"+"js_code="+JSCODE+"&grant_type=authorization_code";
	URL realURL=null;
	realURL=new URL(url);
	URLConnection connection=realURL.openConnection();
	
	connection.connect();BufferedReader in=null;
    in=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
	String result="";
	String temp="";
	while((temp=in.readLine())!=null)
	{
		result+=temp;
	}
	System.out.println(result);
	JSONObject json=new JSONObject(result);
	String openid=json.getString("openid");
	System.out.println(openid);
	return openid;
	}

}
