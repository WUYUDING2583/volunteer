package volunteer.action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import volunteer.modal.ActInfo;

public class ActivityAction extends ActionSupport{
	
	private ActInfo info;
	
	public void setInfo(ActInfo info) {
		this.info=info;
	}
	
	public ActInfo getInfo() {
		return info;
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	//添加活动
	public String addActivity() {
		return SUCCESS;
	}
	
}
