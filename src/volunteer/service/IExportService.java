package volunteer.service;

import java.io.IOException;
import java.io.InputStream;

public interface IExportService {
	public InputStream getExcelInputStream(String Ano) throws IOException;
	
}
