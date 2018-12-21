package volunteer.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import volunteer.service.ActivityService;
import volunteer.service.ExcelService;
import volunteer.service.FileService;

public class FileAction extends ActionSupport {

	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";
	
	private File file;
	private String fileFileName;
	//private String fileFileContentType;
	private InputStream inputStream;
	private String message = "你已成功上传文件";

	//上传工时表
	public String uploadVtime() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/Vtime");
		FileService fileSer = new FileService();
		ExcelService excelSer=new ExcelService();
		if (excelSer.uploadVtime(file)) { 
			message = "文件上传成功";
		}
		else
			message = "文件上传失败，请检查文件格式";
		System.out.println(message);
		inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		return SUCCESS;
	}
	
	//上传图片
	public String uploadImage() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/Images");
		FileService fileSer = new FileService();
		if (fileSer.uploadFile(file, fileFileName, path))
			message = "https://jsjzx.top/Volunteer/Images" + "/" + fileFileName;
		else
			message = "400";
		inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		return SUCCESS;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/*public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}*/

}
