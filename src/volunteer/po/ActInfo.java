package volunteer.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActInfo extends ActBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private int state;//是否已经上传工时表
	private String publishName;
	private String Adate;
	public String getAdate() {
		return Adate;
	}

	public void setAdate(String adate) {
		Adate = adate;
	}
	private String college;
	private String org="浙江工业大学";
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	private double lng;//经度
	private double lat;//纬度
	private Set<ActReq> actreqs=new HashSet<ActReq>();
	public Set<ActReq> getActreqs() {
		return actreqs;
	}

	public void setActreqs(Set<ActReq> actreqs) {
		this.actreqs = actreqs;
	}

	public ActInfo() {}
	
	public void setLng(double lng) {this.lng=lng;}
	public void setLat(double lat) {this.lat=lat;}
	public void setState(int state) {this.state=state;}
	public void setPublishName(String publishName) {this.publishName=publishName;}
	public void setCollege(String college) {this.college=college;}
	
	public double getLng() {return lng;}
	public double getLat() {return lat;}
	public int getState() {return state;}
	public String getPublishName() {return publishName;}
	public String getCollege() {return college;}
	public  String toJson()
	{
		JSONObject json=new JSONObject();
		json.put("Ano", getAno());
		json.put("Adate", Adate.trim());
		json.put("Aname", getAname());
		json.put("Address", getAddress().trim());
		json.put("Arequest", getArequest().trim());
		json.put("publishTime", getPublishTime());
		json.put("publishName", publishName);
		json.put("college", college);
		//json.put("picture", getPicture());
		
		JSONArray actreq=new JSONArray();
		for(ActReq req : actreqs) {
			actreq.put(req.toJson());
		}
		//while(actreqs.iterator().hasNext())
			
		System.out.println(actreq.toString());
		json.put("ActReq", actreq);
		return json.toString();
		
	}
	
}
