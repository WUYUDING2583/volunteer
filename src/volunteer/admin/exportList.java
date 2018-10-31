package volunteer.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import volunteer.dao.AdminDao;
import volunteer.modal.User;


/**
 * Servlet implementation class exportListServlet
 */
@WebServlet("/exportListServlet")
public class exportList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exportList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setHeader("Content-Encoding", "gb2312");
		//response.setContentType("application/vnd.ms-excel;charset=gb2312");
		response.setContentType("application/application/vnd.ms-excel");
		String fileName=request.getParameter("fileName");
		String Ano=new String(request.getParameter("Ano").getBytes("iso-8859-1"),"UTF-8");
		response.setHeader("Content-disposition",
		    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		//PrintWriter out=response.getWriter();
		AdminDao dao=new AdminDao();
		ArrayList<User> list=dao.getApplyList(Ano);
		//新建excel报表
	    HSSFWorkbook excel = new HSSFWorkbook();
	    //添加一个sheet
	    HSSFSheet hssfSheet = excel.createSheet("名单");
	    //往excel表格创建一行，excel的行号是从0开始的
	    // 设置表头
	    HSSFRow firstRow = hssfSheet.createRow(0);
	    //创建单元格
        HSSFCell hssfCell = firstRow.createCell(0);
        hssfCell.setCellValue("姓名");
        hssfCell = firstRow.createCell(1);
        hssfCell.setCellValue("性别");
        hssfCell = firstRow.createCell(2);
        hssfCell.setCellValue("学号");
        hssfCell = firstRow.createCell(3);
        hssfCell.setCellValue("学院班级");
        hssfCell = firstRow.createCell(4);
        hssfCell.setCellValue("志愿者号");
        hssfCell = firstRow.createCell(5);
        hssfCell.setCellValue("电话");
        hssfCell = firstRow.createCell(6);
        hssfCell.setCellValue("时间段");
        hssfCell = firstRow.createCell(7);
        hssfCell.setCellValue("岗位");
        for(int row=0;row<list.size();row++) {
        	HSSFRow hssfRow = hssfSheet.createRow(row + 1);
        	HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(list.get(row).getName());
            cell = hssfRow.createCell(1);
            cell.setCellValue(list.get(row).getSex());
            cell = hssfRow.createCell(2);
            cell.setCellValue(list.get(row).getNo());
            cell = hssfRow.createCell(3);
            cell.setCellValue(list.get(row).getCclass());
            cell = hssfRow.createCell(4);
            cell.setCellValue(list.get(row).getVno());
            cell = hssfRow.createCell(5);
            cell.setCellValue(list.get(row).getPhone());
            cell = hssfRow.createCell(6);
            cell.setCellValue(list.get(row).getAtime());
            cell = hssfRow.createCell(7);
            cell.setCellValue(list.get(row).getAjobstate());
    	}
        OutputStream outputStream = response.getOutputStream();// 打开流
        excel.write(outputStream);// HSSFWorkbook写入流
        excel.close();// HSSFWorkbook关闭
        outputStream.flush();// 刷新流
        outputStream.close();// 关闭流
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

}
