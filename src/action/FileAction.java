package action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport {

	private File file;
	private String fileFileName;
	private String fileFileContentType;

	private String message = "你已成功上传文件";
	private InputStream inputStream;

	public String execute() throws Exception {

		String path = ServletActionContext.getRequest().getRealPath("/upload");

		try {
			File f = this.getFile();
			if (this.getFileFileName().endsWith(".exe")) {
				message = "对不起,你上传的文件格式不同意!!!";
				inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
				return ERROR;
			}
			FileInputStream inputStream = new FileInputStream(f);
			// 如果目录不存在则创建
			File uploadDir = new File(path);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			path += "/" + this.getFileFileName();
			FileOutputStream outputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			message = "对不起,文件上传失败了!!!!";
			inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
			return ERROR;
		}

		inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

}