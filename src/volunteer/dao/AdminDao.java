package volunteer.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import volunteer.modal.ActInfo;
import volunteer.modal.ActReq;
import volunteer.modal.Admin;
import volunteer.modal.User;


public class AdminDao extends BaseDao{
	
	private static final String LOGIN_WRONGMSG = "用户名或密码错误";
	 // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final String PICTURE_DIRECTORY = "picture";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	private XSSFCell Ttel;
	
	//文件上传处理方法
		public File fileUpload(HttpServletRequest request,HttpServletResponse response,String path) throws FileUploadException {
			 // 检测是否为多媒体上传
	        if (!ServletFileUpload.isMultipartContent(request)) {
	            // 如果不是则停止
	           
	            return null;
	        }
	 
	        // 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // 设置最大文件上传值
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	         
	        // 设置最大请求值 (包含文件和表单数据)
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        // 中文处理
	        upload.setHeaderEncoding("UTF-8"); 

	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        String uploadPath = path+ UPLOAD_DIRECTORY;
	        String pa="www.jsjzx.top/Volunteer"+ UPLOAD_DIRECTORY;
	        File storeFile=null;
	         
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	 
	        try {
	            // 解析请求的内容提取文件数据
	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	 
	            if (formItems != null && formItems.size() > 0) {
	                // 迭代表单数据
	                for (FileItem item : formItems) {
	                    // 处理不在表单中的字段
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        pa+=File.separator + fileName;
	                        storeFile = new File(filePath);
	                        // 在控制台输出文件的上传路径
	                        System.out.println(filePath);
	                        // 保存文件到硬盘
	                        item.write(storeFile);
	                        request.getSession().setAttribute("uploadMessage",
	                            "文件上传成功!");
	                    }
	                }
	            }
	            
	        } catch (Exception ex) {
	        	return null;
	        }
	        
	        return storeFile;
		}
		
		//工时数据上传
		public int vtimeUpload(File file) {
			XSSFCell No,Aname,Adate,vtime,count;
			XSSFRow row;
			//获得表单信息
			XSSFWorkbook wb0=null;
			String message=null;
			ArrayList<User> list=new ArrayList<User>();
			try {
				FileInputStream fs = new FileInputStream(file);
				wb0 = new XSSFWorkbook(fs);
				XSSFSheet sht0 = wb0.getSheetAt(0);
				row=sht0.getRow(2);
				Aname=row.getCell(5);
				row=sht0.getRow(5);
				Adate=row.getCell(5);
				DateFormat format = new SimpleDateFormat("yyyy.MM.dd");        
				java.util.Date date = format.parse(Adate.getStringCellValue());  
				System.out.println(date);
				row=sht0.getRow(6);
				count=row.getCell(5);
				Date date1=new Date(date.getTime());
				int length=(int) count.getNumericCellValue();
				String no=null;
				for(int i=11;i<(length+11);i++)
				{
					User user=new User();
					row=sht0.getRow(i);
					No=row.getCell(5);
					No.setCellType(CellType.STRING);
					no=No.getStringCellValue();
					vtime=row.getCell(8);
					user.setNo(no);
					user.setVtime((float)vtime.getNumericCellValue());
					list.add(user);
				}
				String sql="insert into Vtime values(?,?,?,?)";
				Connection conn=getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql);
				int coun=0;
				for(int i=0;i<list.size();i++)
				{
					pstmt.setString(1, list.get(i).getNo().trim());
					pstmt.setString(2, Aname.getStringCellValue().trim());
					pstmt.setDate(3, date1);
					pstmt.setFloat(4, list.get(i).getVtime());
					coun+=pstmt.executeUpdate();
				}
				fs.close();
				file.delete();
				pstmt.close();
				conn.close();
				return coun;
			}catch(SQLException se)
			{
				se.printStackTrace();
				return 0;
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		
	//图片上传处理方法
	public String pictureUpload(HttpServletRequest request,HttpServletResponse response,String path) throws FileUploadException {
		 // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            return null;
        }
        String message=null;
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        int length=path.length();
        String Path=path.substring(0, length-1);
        
        String temp_str="";   
	    java.util.Date dt = new java.util.Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt); 
	    
        String uploadPath = Path+ UPLOAD_DIRECTORY + File.separator;
        String pa="https://www.jsjzx.top/Volunteer/"+ UPLOAD_DIRECTORY + "/";
        System.out.println(uploadPath);
        File storeFile=null;
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        uploadPath += temp_str;
        pa+= temp_str;
     // 如果目录不存在则创建
        uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + "/" + fileName;
                        pa+= "/" + fileName;
                        storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(pa);
                        message=pa;
                        // 保存文件到硬盘
                        item.write(storeFile);
                    }
                }
            }
            
        } catch (Exception ex) {
        	return null;
        }
        
        return message;
	}
	//产生10位随机字符串
	public String getRandomString(){
	    //定义一个字符串（A-Z，a-z，0-9）即62位；
	    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	    //由Random生成随机数
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //长度为几就循环几次
	        for(int i=0; i<10; ++i){
	          //产生0-61的数字
	          int number=random.nextInt(62);
	          //将产生的数字通过length次承载到sb中
	          sb.append(str.charAt(number));
	        }
	        //将承载的字符转换成字符串
	        return sb.toString();
	  }
	
	//添加活动
	public String addActivity(ActInfo info,ArrayList<ActReq> reqList) {
		String sql="insert into ActivityInfo values(?,?,?,?,?,?,?,?,?,?,'浙江工业大学',0,?,?);";
		String message=null;
		int result=0;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, info.getAno());
			Timestamp dat=Timestamp.valueOf(info.getAdeadline());
			pstmt.setTimestamp(2, dat);
			Date date=Date.valueOf(info.getAdate());
			pstmt.setDate(3, date);
			pstmt.setString(4, info.getAname());
			pstmt.setString(5, info.getAddress());
			pstmt.setString(6, info.getArequest());
			pstmt.setString(7, info.getPublishTime());
			pstmt.setString(8, info.getPublishName());
			pstmt.setString(9, info.getPicture());
			pstmt.setString(10, info.getCollege());
			pstmt.setDouble(11, info.getLng());
			pstmt.setDouble(12, info.getLat());
			result+=pstmt.executeUpdate();
			for(int i=0;i<reqList.size();i++) {
				sql="insert into ActivityRequest values(?,?,?,?,0);";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, info.getAno());
				pstmt.setString(2, reqList.get(i).getAtime());
				pstmt.setString(3, reqList.get(i).getAjobstate());
				pstmt.setInt(4, reqList.get(i).getAjobcount());
				result+=pstmt.executeUpdate();
			}
			if(result>0) {
				message="添加成功";
			}
			else
				message="添加失败";
			pstmt.close();
			conn.close();
		}catch(SQLException se)
		{
			se.printStackTrace();
			message="添加失败";
		}
		return message;
	}
	
	//获得所有志愿活动列表
	public ArrayList<ActInfo> getActInfoList(Admin admin){
		String sql="select * from ActivityInfo where college=?";
		ArrayList<ActInfo> infoList=new ArrayList<ActInfo>();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin.getCollege());
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {  
				ActInfo info=new ActInfo();
				String temp_str="";  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date=rst.getDate("Adate").toString().trim();
				java.util.Date acttime=sdf.parse(date);
				java.util.Date now = new java.util.Date(); 
				temp_str=sdf.format(now); 
				if(date.equals(temp_str)) {
					info.setAstate("进行中");
				}
				else if(now.before(acttime)) {
					info.setAstate("未开始");
				}
				else if(now.after(acttime)) {
					info.setAstate("已结束");
				}
				//最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制 ;
				info.setState(rst.getInt("state"));
				info.setAno(rst.getString("Ano").trim());
				info.setAname(rst.getString("Aname").trim());
				info.setAdate(rst.getDate("Adate").toString().trim());
				info.setAddress(rst.getString("Address").trim());
				info.setArequest(rst.getString("Arequest").trim());
				info.setPicture(rst.getString("picture").trim());
				info.setAdeadline(rst.getDate("Adeadline").toString().trim());
				info.setPublishName(rst.getString("publishName").trim());
				info.setPublishTime(rst.getDate("publishTime").toString().trim()+" "+rst.getTime("publishTime").toString().trim());
				infoList.add(info);
			}
			pstmt.close();
			conn.close();
			return infoList;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查找活动信息
	public ActInfo getActInfo(String Ano) {
		ActInfo info=new ActInfo();
		String sql="select * from ActivityInfo where Ano=?";
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			ResultSet rst=pstmt.executeQuery();
			if(rst.next()) {  
				info.setAno(Ano);
				info.setAddress(rst.getString("Address").trim());
				info.setAdate(rst.getDate("Adate").toString().trim());
				info.setAname(rst.getString("Aname").trim());
				info.setArequest(rst.getString("Arequest").trim());
				info.setCollege(rst.getString("college").trim());
				info.setPicture(rst.getString("picture").trim());
				info.setPublishName(rst.getString("publishName").trim());
				info.setPublishTime(rst.getDate("publishTime").toString().trim()+" "+rst.getTime("publishTime").toString().trim());
			}
			pstmt.close();
			conn.close();
			return info;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//查找活动要求
	public ArrayList<ActReq> getActReq(String Ano){
		ArrayList<ActReq> reqList=new ArrayList<ActReq>();
		String sql="select * from ActivityRequest where Ano=?";
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {  
				ActReq req=new ActReq();
				req.setAjobcount(rst.getInt("Ajobcount"));
				req.setAjobstate(rst.getString("Ajobstate").trim());
				req.setAno(Ano);
				req.setAtime(rst.getString("Atime").trim());
				req.setDoneAccount(rst.getInt("DoneAccount"));
				reqList.add(req);
			}
			pstmt.close();
			conn.close();
			return reqList;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//删除活动
	public int deleteActivity(String Ano) {
		String sql="delete from ActivityInfo where Ano=?;delete from ActivityRequest where Ano=?;delete from Apply where Ano=?;";
		int count=0;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			pstmt.setString(2, Ano);
			pstmt.setString(3, Ano);
			count=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return 0;
		} 
	}
	
	//返回活动报名名单
	public ArrayList<User> getApplyList(String Ano){
		String sql="select * from Apply where Ano=?;";
		ArrayList<User> applyList=new ArrayList<User>();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {
				User user=new User();
				user.setNo(rst.getString("No").trim());
				user.setAno(rst.getString("Ano").trim());
				user.setAtime(rst.getString("Atime").trim());
				user.setAjobstate(rst.getString("Ajobstate").trim());
				applyList.add(user);
			}
			for(int i=0;i<applyList.size();i++) {
				sql="select * from Account where No=?;";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, applyList.get(i).getNo());
				rst=pstmt.executeQuery();
				if(rst.next()) {
					applyList.get(i).setName(rst.getString("Name").trim());
					applyList.get(i).setSex(rst.getString("Sex").trim());
					applyList.get(i).setClass(rst.getString("Class").trim());
					applyList.get(i).setVno(rst.getString("Vno").trim());
					applyList.get(i).setPhone(rst.getString("Phone").trim());
				}
			}
			pstmt.close();
			conn.close();
			return applyList;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//设置已经上传工时
	public int setState(String Ano) {
		String sql="update ActivityInfo set state=1 where Ano=?;";
		int count=0;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			count=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return 0;
		} 
	}
	
	//返回工时信息
	public ArrayList<User> getVtimeInfo(String Aname,String Adate){
		String sql="select * from Vtime where Aname=? and Adate=?;";
		ArrayList<User> list=new ArrayList<User>();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Aname);
			Date date=Date.valueOf(Adate);
			pstmt.setDate(2, date);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {
				User user=new User();
				user.setNo(rst.getString("No").trim());
				user.setAname(rst.getString("Aname").trim());
				user.setAdate(rst.getDate("Adate").toString());
				user.setVtime(rst.getFloat("Avtime"));
				list.add(user);
			}
			for(int i=0;i<list.size();i++) {
				sql="select Name from Account where No=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, list.get(i).getNo());
				rst=pstmt.executeQuery();
				if(rst.next())
					list.get(i).setName(rst.getString("Name").trim());
			}
			pstmt.close();
			conn.close();
			return list;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//删除工时记录
	public int deleteVtime(String Aname,String Adate,String No) {
		String sql="delete from Vtime where Aname=? and Adate=? and No=?;";
		int count=0;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Aname);
			Date date=Date.valueOf(Adate);
			pstmt.setDate(2, date);
			pstmt.setString(3, No);
			count=pstmt.executeUpdate();
			System.out.println(count);
			pstmt.close();
			conn.close();
			return count;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return 0;
		} 
	}
	
	//修改工时记录
	public int alterVtime(String Aname,String Adate,String No,float vtime) {
		String sql="update Vtime set Avtime=? where Aname=? and Adate=? and No=?;";
		int count=0;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setFloat(1, vtime);
			pstmt.setString(2, Aname);
			Date date=Date.valueOf(Adate);
			pstmt.setDate(3, date);
			pstmt.setString(4, No);
			count=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return 0;
		} 
	}
	
	//添加工时记录
	public String addVtime(String Aname,String Adate,String No,float Avtime) {
		String sql="insert into Vtime values(?,?,?,?);";
		int count=0;
		String Name=null;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, No);
			pstmt.setString(2, Aname);
			Date date=Date.valueOf(Adate);
			pstmt.setDate(3, date);
			pstmt.setFloat(4, Avtime);
			count=pstmt.executeUpdate();
			if(count>0) {
				sql="select Name from Account where No=?;";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, No);
				ResultSet rst=pstmt.executeQuery();
				if(rst.next()) {
					Name=rst.getString("Name").trim();
				}
			}
			pstmt.close();
			conn.close();
			return Name;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return "no";
		} 
	}
	
	//根据学号查工时
	public ArrayList<User> VtimeSearch(String No){
		String sql="select * from Vtime where No=?;";
		ArrayList<User> list=new ArrayList<User>();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, No);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {
				User user=new User();
				user.setAname(rst.getString("Aname").trim());
				user.setAdate(rst.getDate("Adate").toString());
				user.setVtime(rst.getFloat("Avtime"));
				list.add(user);
			}
			sql="select Name from Account where No=?;";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, No);
			rst=pstmt.executeQuery();
			if(rst.next()) {
				for(int i=0;i<list.size();i++) {
					list.get(i).setName(rst.getString("Name").trim());
				}
			}
			pstmt.close();
			conn.close();
			return list;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//登陆
	public Admin login(Admin admin) {
		String sql="select * from Admin where account=?;";
		Admin ad=new Admin();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin.getAccount());
			ResultSet rst=pstmt.executeQuery();
			ad.setAccount(LOGIN_WRONGMSG);
			while(rst.next()) {
				String password=rst.getString("password").trim();
				if(password.equals(admin.getPassword())) {
					ad.setAccount(rst.getString("account").trim());
					ad.setCollege(rst.getString("college").trim());
					ad.setId(rst.getString("id").trim());
				}
				else {
					ad.setAccount(LOGIN_WRONGMSG);
				}
			}
			pstmt.close();
			conn.close();
			return ad;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
	
	//修改密码
	public String changePsw(Admin admin) {
		String sql="update Admin set password=? where id=?;";
		String message=null;
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin.getPassword());
			pstmt.setString(2, admin.getId());
			if(pstmt.executeUpdate()>0)
				message="ok";
			else
				message="no";
			pstmt.close();
			conn.close();
			return message;
		}catch(SQLException se)
		{
			message="no";
			se.printStackTrace();
			return message;
		} 
	}
	
	//获取签到签退情况
	public ArrayList<User> getSign(String Ano){
		String sql="select * from Apply where Ano=?;";
		ArrayList<User> list=new ArrayList<User>();
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()) {
				User user=new User();
				user.setNo(rst.getString("No").trim());
				user.setAtime(rst.getString("Atime").trim());
				user.setAjobstate(rst.getString("Ajobstate").trim());
				user.setSignIn(rst.getDate("signIn").toString());
				user.setSignOut(rst.getDate("signOut").toString());
				user.setLat(rst.getFloat("lat"));
				user.setLng(rst.getFloat("lng"));
				list.add(user);
			}
			sql="select Name from Account where No=?;";
			for(int i=0;i<list.size();i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, list.get(i).getNo());
				rst=pstmt.executeQuery();
				if(rst.next()) {
					list.get(i).setName(rst.getString("Name").trim());
				}
			}
			pstmt.close();
			conn.close();
			return list;
		}catch(SQLException se)
		{
			se.printStackTrace();
			return null;
		} 
	}
}
