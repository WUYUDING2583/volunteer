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

import volunteer.dao.ExcelDAO;
import volunteer.dao.IExcelDAO;
import volunteer.po.ManHour;
import volunteer.po.User;

public class ExcelService implements IExcelService{

	/*
	 * @从上传的工时表中获取数据
	 * 传入参数 file 上传的文件数据流
	 * 从文件中获取的数据 list
	 * 将list中的数据存储至数据库
	 * 存储成功设置该活动的state为1并返回true
	 * 存储失败返回false
	 * 
	 */
	private IExcelDAO dao;
	public IExcelDAO getDao() {
		return dao;
	}
	public void setDao(ExcelDAO dao) {
		this.dao = dao;
	}
	public boolean uploadVtime(File file) {
		Cell No, Aname, Adate, vtime, count;
		Row row;
		Workbook wb0 = null;
		String message = null;
		List vtimeList = new ArrayList<ManHour>();
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
				ManHour manhour = new ManHour();
				row = sht0.getRow(i);
				No = row.getCell(5);
				No.setCellType(CellType.STRING);
				no = No.getStringCellValue();
				vtime = row.getCell(8);
				manhour.getPk().setNo(no);
				manhour.setAvtime((float) vtime.getNumericCellValue());
				manhour.getPk().setAname(Aname.getStringCellValue());
				manhour.getPk().setAdate(Adate.getStringCellValue());
				vtimeList.add(manhour);
			}
			/*
			 * 在此处将list中内容传入数据库
			 */
			if(dao.addVtime(vtimeList).equals("success"))
				return true;
			else return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
