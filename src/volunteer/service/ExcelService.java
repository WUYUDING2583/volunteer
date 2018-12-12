package volunteer.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import volunteer.po.User;

public class ExcelService {

	/*
	 * @从上传的工时表中获取数据
	 * 传入参数 file 上传的文件数据流
	 * 从文件中获取的数据 list
	 * 将list中的数据存储至数据库
	 * 存储成功返回true
	 * 存储失败返回false
	 */
	public boolean uploadVtime(File file) {
		Cell No, Aname, Adate, vtime, count;
		Row row;
		Workbook wb0 = null;
		String message = null;
		List list = new ArrayList<User>();
		try {
			FileInputStream fs = new FileInputStream(file);
			wb0 =  WorkbookFactory.create(fs);
			Sheet sht0 = wb0.getSheetAt(0);
			System.out.println("123123");
			row = sht0.getRow(2);
			Aname = row.getCell(5);
			row = sht0.getRow(5);
			Adate = row.getCell(5);
			DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
			java.util.Date date = format.parse(Adate.getStringCellValue());
			System.out.println(date);
			row = sht0.getRow(6);
			count = row.getCell(5);
			Date date1 = new Date(date.getTime());
			int length = (int) count.getNumericCellValue();
			String no = null;
			for (int i = 11; i < (length + 11); i++) {
				User user = new User();
				row = sht0.getRow(i);
				No = row.getCell(5);
				No.setCellType(CellType.STRING);
				no = No.getStringCellValue();
				vtime = row.getCell(8);
				user.setNo(no);
				user.setVtime((float) vtime.getNumericCellValue());
				list.add(user);
			}
			/*
			 * 在此处将list中内容传入数据库
			 */
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
