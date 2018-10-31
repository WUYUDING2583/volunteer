package volunteer.front;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class getAccessToken {

	public getAccessToken() {
		// TODO 自动生成的构造函数存根
	}
	
	public String getAcessToken(String appid,String secret) throws IOException
	{
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
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
		String access_token=json.getString("access_token");
		 BufferedWriter buf=new BufferedWriter(new FileWriter("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\Volunteer\\access_token.txt"));
		 buf.write(access_token);
		 buf.close();
		System.out.println("Acess_Token:"+access_token);
		System.out.println("expires_in:"+json.getInt("expires_in"));
		return json.toString();
	}
}
