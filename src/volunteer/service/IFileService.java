package volunteer.service;

import java.io.File;

public interface IFileService {
	public boolean uploadFile(File file, String fileName, String path) throws Exception;
	
}
