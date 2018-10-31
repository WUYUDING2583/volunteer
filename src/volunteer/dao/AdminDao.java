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
	
	private static final String LOGIN_WRONGMSG = "�û������������";
	 // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final String PICTURE_DIRECTORY = "picture";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	private XSSFCell Ttel;
	
	//�ļ��ϴ�������
		public File fileUpload(HttpServletRequest request,HttpServletResponse response,String path) throws FileUploadException {
			 // ����Ƿ�Ϊ��ý���ϴ�
	        if (!ServletFileUpload.isMultipartContent(request)) {
	            // ���������ֹͣ
	           
	            return null;
	        }
	 
	        // �����ϴ�����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // ������ʱ�洢Ŀ¼
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // ��������ļ��ϴ�ֵ
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	         
	        // �����������ֵ (�����ļ��ͱ�����)
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        // ���Ĵ���
	        upload.setHeaderEncoding("UTF-8"); 

	        // ������ʱ·�����洢�ϴ����ļ�
	        // ���·����Ե�ǰӦ�õ�Ŀ¼
	        String uploadPath = path+ UPLOAD_DIRECTORY;
	        String pa="www.jsjzx.top/Volunteer"+ UPLOAD_DIRECTORY;
	        File storeFile=null;
	         
	        // ���Ŀ¼�������򴴽�
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	 
	        try {
	            // ���������������ȡ�ļ�����
	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	 
	            if (formItems != null && formItems.size() > 0) {
	                // ����������
	                for (FileItem item : formItems) {
	                    // �����ڱ��е��ֶ�
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        pa+=File.separator + fileName;
	                        storeFile = new File(filePath);
	                        // �ڿ���̨����ļ����ϴ�·��
	                        System.out.println(filePath);
	                        // �����ļ���Ӳ��
	                        item.write(storeFile);
	                        request.getSession().setAttribute("uploadMessage",
	                            "�ļ��ϴ��ɹ�!");
	                    }
	                }
	            }
	            
	        } catch (Exception ex) {
	        	return null;
	        }
	        
	        return storeFile;
		}
		
		//��ʱ�����ϴ�
		public int vtimeUpload(File file) {
			XSSFCell No,Aname,Adate,vtime,count;
			XSSFRow row;
			//��ñ���Ϣ
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
		
	//ͼƬ�ϴ�������
	public String pictureUpload(HttpServletRequest request,HttpServletResponse response,String path) throws FileUploadException {
		 // ����Ƿ�Ϊ��ý���ϴ�
        if (!ServletFileUpload.isMultipartContent(request)) {
            // ���������ֹͣ
            return null;
        }
        String message=null;
        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // ��������ļ��ϴ�ֵ
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // �����������ֵ (�����ļ��ͱ�����)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // ���Ĵ���
        upload.setHeaderEncoding("UTF-8"); 
        // ������ʱ·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
        int length=path.length();
        String Path=path.substring(0, length-1);
        
        String temp_str="";   
	    java.util.Date dt = new java.util.Date();   
	    //����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    �������hh��ʾ12Сʱ��   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt); 
	    
        String uploadPath = Path+ UPLOAD_DIRECTORY + File.separator;
        String pa="https://www.jsjzx.top/Volunteer/"+ UPLOAD_DIRECTORY + "/";
        System.out.println(uploadPath);
        File storeFile=null;
         
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        uploadPath += temp_str;
        pa+= temp_str;
     // ���Ŀ¼�������򴴽�
        uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // ���������������ȡ�ļ�����
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // ����������
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + "/" + fileName;
                        pa+= "/" + fileName;
                        storeFile = new File(filePath);
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(pa);
                        message=pa;
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                    }
                }
            }
            
        } catch (Exception ex) {
        	return null;
        }
        
        return message;
	}
	//����10λ����ַ���
	public String getRandomString(){
	    //����һ���ַ�����A-Z��a-z��0-9����62λ��
	    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	    //��Random���������
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //����Ϊ����ѭ������
	        for(int i=0; i<10; ++i){
	          //����0-61������
	          int number=random.nextInt(62);
	          //������������ͨ��length�γ��ص�sb��
	          sb.append(str.charAt(number));
	        }
	        //�����ص��ַ�ת�����ַ���
	        return sb.toString();
	  }
	
	//��ӻ
	public String addActivity(ActInfo info,ArrayList<ActReq> reqList) {
		String sql="insert into ActivityInfo values(?,?,?,?,?,?,?,?,?,?,'�㽭��ҵ��ѧ',0,?,?);";
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
				message="��ӳɹ�";
			}
			else
				message="���ʧ��";
			pstmt.close();
			conn.close();
		}catch(SQLException se)
		{
			se.printStackTrace();
			message="���ʧ��";
		}
		return message;
	}
	
	//�������־Ը��б�
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
					info.setAstate("������");
				}
				else if(now.before(acttime)) {
					info.setAstate("δ��ʼ");
				}
				else if(now.after(acttime)) {
					info.setAstate("�ѽ���");
				}
				//����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    �������hh��ʾ12Сʱ�� ;
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
	
	//���һ��Ϣ
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
	
	//���һҪ��
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
	
	//ɾ���
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
	
	//���ػ��������
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
	
	//�����Ѿ��ϴ���ʱ
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
	
	//���ع�ʱ��Ϣ
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
	
	//ɾ����ʱ��¼
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
	
	//�޸Ĺ�ʱ��¼
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
	
	//��ӹ�ʱ��¼
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
	
	//����ѧ�Ų鹤ʱ
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
	
	//��½
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
	
	//�޸�����
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
	
	//��ȡǩ��ǩ�����
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
