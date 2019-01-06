package volunteer.action;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import volunteer.service.ActivityService;
import volunteer.service.ExcelService;
import volunteer.service.FileService;
import volunteer.service.IActivityService;
import volunteer.service.IExcelService;
import volunteer.service.IFileService;

public class FileAction extends ActionSupport {

	private final static String SUCCESS = "success";
	private final static String FAIL = "fail";
	private final static long MAXFILESIZE=1000000;

	private File file;
	private String fileFileName;
	// private String fileFileContentType;
	private InputStream inputStream;
	private String message = "你已成功上传文件";
	private IFileService fileservice = null;
	private IExcelService excelservice = null;

	public void setFileservice(IFileService fileservice) {
		this.fileservice = fileservice;
	}

	public void setExcelservice(IExcelService excelservice) {
		this.excelservice = excelservice;
	}

	public IFileService getFileservice() {
		return fileservice;
	}

	public IExcelService getExcelservice() {
		return excelservice;
	}

	// 上传工时表
	public String uploadVtime() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/Vtime");
		// FileService fileSer = new FileService();
		// ExcelService excelSer=new ExcelService();
		if (excelservice.uploadVtime(file)) {
			message = "文件上传成功";
		} else
			message = "文件上传失败，请检查文件格式";
		System.out.println(message);
		inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		return SUCCESS;
	}

	// 上传图片
	public String uploadImage() throws Exception {
		String path = ServletActionContext.getRequest().getRealPath("/Images");
		// FileService fileSer = new FileService();
		if(checkImage()) {
			if(getFileSize()<MAXFILESIZE) {
				if (fileservice.uploadFile(file, fileFileName, path))
					message = "https://jsjzx.top/volunteer/Images" + "/" + fileFileName;
				else
					message = "400";
			}else
				message="450";
			
		}else
			message="500";
		inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
		return SUCCESS;
	}

	// 判断是否为图片
	public boolean checkImage() {
		try { // 通过ImageReader来解码这个file并返回一个BufferedImage对象
				// 如果找不到合适的ImageReader则会返回null，我们可以认为这不是图片文件
				// 或者在解析过程中报错，也返回false
			Image image = ImageIO.read(file);
			return image != null;
		} catch (IOException ex) {
			return false;
		}
	}
	/**
     * 获取文件长度
     * @param file
     */
    public long getFileSize() {
        if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            System.out.println("文件"+fileName+"的大小是："+file.length());
            return file.length();
        }
        return 0;
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

	/*
	 * public String getFileFileContentType() { return fileFileContentType; }
	 * 
	 * public void setFileFileContentType(String fileFileContentType) {
	 * this.fileFileContentType = fileFileContentType; }
	 */

}
