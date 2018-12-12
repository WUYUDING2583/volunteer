package volunteer.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import volunteer.po.User;

public class ExportService{
	
	private List userList=new ArrayList<>();
	
	/*
	 * @下载活动招募情况表格
	 * 传入参数 Ano 活动编号
	 * 获取成功返回 excelFile
	 * 获取失败返回 null
	 */
	 public InputStream getExcelInputStream(String Ano) throws IOException {  
	        
	        InputStream excelFile;
	       
	       String[] title = {"姓名","性别","学号","学院班级","志愿者号","电话","时间段","岗位"};
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet();
	        HSSFRow row = sheet.createRow(0);
	        HSSFCell cell = null;
	        for (int i = 0; i < title.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellValue(title[i]);
	        }
	        /*
	         * 在此处通过Ano获取活动招募情况
	         * 返回userList
	         * 
	         */
	        for(int i=0;i<userList.size();i++) {
	        	HSSFRow nextrow = sheet.createRow(i+1);
	            HSSFCell cell2 = nextrow.createCell(0);
	            cell2.setCellValue(((User) userList.get(i)).getName());
	            cell2 = nextrow.createCell(1);
	            cell2.setCellValue(((User) userList.get(i)).getSex());
	            cell2 = nextrow.createCell(2);
	            cell2.setCellValue(((User) userList.get(i)).getNo());
	            cell2 = nextrow.createCell(3);
	            cell2.setCellValue(((User) userList.get(i)).getCollege()+((User)userList.get(i)).getCclass());
	            cell2 = nextrow.createCell(4);
	            cell2.setCellValue(((User) userList.get(i)).getVno());
	            cell2 = nextrow.createCell(5);
	            cell2.setCellValue(((User) userList.get(i)).getPhone());
	            cell2 = nextrow.createCell(6);
	            cell2.setCellValue(((User) userList.get(i)).getAtime());
	            cell2 = nextrow.createCell(7);
	            cell2.setCellValue(((User) userList.get(i)).getAjobstate());
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        
	        workbook.write(out);
	        
	        excelFile = new ByteArrayInputStream(out.toByteArray());
	        
	        excelFile.close();
	              
	       return excelFile;  
	   } 
}
