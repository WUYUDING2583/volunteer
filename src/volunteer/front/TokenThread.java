//package volunteer.front;
//
//import java.io.IOException;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class TokenThread implements Runnable{
//	 public static  String appid;
//	    public static  String secret;
//
//	public TokenThread() {
//		// TODO 自动生成的构造函数存根
//	}
//
//	@Override
//	public void run() 
//	{
//		while(true)
//		{    
//			try
//			{
//				getAccessToken getAT=new getAccessToken();
//				JSONObject access_token=new JSONObject(getAT.getAcessToken(appid, secret));
//				if(access_token!=null)
//				{
//					System.out.println("Access_Token获取成功:"+access_token.getString("access_token"));
//					int expires=access_token.getInt("expires_in");
//					Thread.sleep((expires-200) * 1000);
//				}
//				else
//					Thread.sleep(60*1000);
//				
//			}
//			catch (InterruptedException | JSONException | IOException e)
//			{
//				try
//				{
//					Thread.sleep(60*1000);
//				}
//				 catch (InterruptedException e1)
//				{
//					 e1.printStackTrace();
//				}
//				e.printStackTrace();
//			}
//		}
//		
//	}
//
//	public static String getAppid() {
//		return appid;
//	}
//
//	public static String getSecret() {
//		return secret;
//	}
//
//}
