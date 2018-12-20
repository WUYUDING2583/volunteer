package volunteer.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import volunteer.dao.ExportDAO;
import volunteer.po.Apply;

public class ExportService{
	
	private List applyList=new ArrayList<>();
	private ExportDAO dao=new ExportDAO();
	
	/*
	 * @下载活动招募情况表格
	 * 传入参数 Ano 活动编号
	 * 获取成功返回 excelFile
	 * 获取失败返回 null
	 */
	 public InputStream getExcelInputStream(String Ano) throws IOException {  
	        
	        InputStream excelFile;
	       System.out.println("下载活动招募情况表格:");
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
	         * 返回applyList
	         * 
	         */ 
	       applyList=dao.getApplyList(Ano);
	       // System.out.println("GETAPPLYLIST"+applyList.size());
	        /*for(int i=0;i<applyList.size();i++) {
	        	HSSFRow nextrow = sheet.createRow(i+1);
	            HSSFCell cell2 = nextrow.createCell(0);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getName());
	            cell2 = nextrow.createCell(1);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getSex());
	            cell2 = nextrow.createCell(2);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getNo());
	            cell2 = nextrow.createCell(3);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getCollege()+((Apply)applyList.get(i)).getUser().getCclass());
	            cell2 = nextrow.createCell(4);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getVno());
	            cell2 = nextrow.createCell(5);
	            cell2.setCellValue(((Apply) applyList.get(i)).getUser().getPhone());
	            cell2 = nextrow.createCell(6);
	            cell2.setCellValue(((Apply) applyList.get(i)).getPk().getAtime());
	            cell2 = nextrow.createCell(7);
	            cell2.setCellValue(((Apply) applyList.get(i)).getPk().getAjobstate());
	        }*/
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        workbook.write(out);
	        excelFile = new ByteArrayInputStream(out.toByteArray());
	        excelFile.close();
	       return excelFile;  
	   } 
}
