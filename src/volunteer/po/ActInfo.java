package volunteer.po;

import java.io.Serializable;
import java.util.Set;

public class ActInfo extends ActBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private int state;//是否已经上传工时表
	private String publishName;
	private String college;
	private double lng;//经度
	private double lat;//纬度
	private Set<ActReq> actreqs;
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
}
