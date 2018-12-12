package volunteer.action;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.commons.io.FileUtils;/*
import org.apache.poi.hssf.usermodel.XSSFCell;
import org.apache.poi.hssf.usermodel.XSSFRow;
import org.apache.poi.hssf.usermodel.XSSFSheet;
import org.apache.poi.hssf.usermodel.XSSFWorkbook;*/
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
 
/**
 * 用户上传file文件到服务端, 解析file文件
 * 
 * @author zhaoj
 * 
 */
public class testAction {
 
	private File file;// 必须与表单中的name属性一致
	private String fileFileName;// 必须是name属性+FileName
 
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
 
	public String execute() throws IOException {
 
		// 将客户端的文件上传到服务端
		String desPath = ServletActionContext.getServletContext().getRealPath(
				"/imags");
		File destFile = new File(desPath, fileFileName);
		FileUtils.copyFile(file, destFile);
 
		// 解析file
		// 得到一个file文件
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(destFile));
		// 得到第一张表
		XSSFSheet sheet = book.getSheetAt(0);
		// 遍历输出
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			// 得到行
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				// 得到一行中的单元格
				XSSFCell cell = row.getCell(j);
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
 
		return "success";
	}
}