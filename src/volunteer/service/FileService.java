package volunteer.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

public class FileService implements IFileService{
	
	/*
	 * @上传文件存储至服务器
	 * 本service无需链接Hibernate
	 */
	public boolean uploadFile(File file, String fileName, String path) throws Exception {

		try {
			FileInputStream inputStream = new FileInputStream(file);
			// 如果目录不存在则创建
			File uploadDir = new File(path);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			path += "/" + fileName;
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
			return false;
		}

		return true;
	}
}
