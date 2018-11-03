package volunteer.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import volunteer.dao.AdminDao;
import volunteer.modal.ActInfo;
import volunteer.modal.ActReq;
import volunteer.modal.Admin;


/**
 * Servlet implementation class addActivityServlet
 */
@WebServlet("/addActivityServlet")
public class addActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String data=request.getParameter("data");
		ActInfo info=new ActInfo();
		AdminDao dao=new AdminDao();
		String Ano=dao.getRandomString();
		Admin admin=(Admin)request.getSession().getAttribute("Admin");
		ArrayList<ActReq> reqList=new ArrayList<ActReq>();
		info.setAno(Ano);
		String message=null;
	    String temp_str="";   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	    temp_str=sdf.format(dt); 
	    String picturePath=(String) request.getSession().getAttribute("picturePath");
		try {
			JsonParser parser =new JsonParser();  //创建json解析器
			JsonObject object=(JsonObject) parser.parse(data);  //创建JsonObject对象
			JsonArray array=object.get("activity").getAsJsonArray();    //得到为json的数组
			for(int i=0;i<array.size();i++){
                JsonObject subObject=array.get(i).getAsJsonObject();
                if(i==0) {
                	info.setAname(subObject.get("Aname").getAsString());
                    info.setAdate(subObject.get("Adate").getAsString());
                    info.setAddress(subObject.get("Address").getAsString());
                    info.setArequest(subObject.get("Arequest").getAsString());
                    info.setPublishTime(temp_str);
                    info.setAdeadline(subObject.get("Adeadline").getAsString());
                    info.setPicture(new String(picturePath.getBytes("iso-8859-1"),"UTF-8"));
                    info.setCollege(admin.getCollege());
                    info.setPublishName(admin.getAccount());
                    info.setLat(subObject.get("lat").getAsDouble());
                    info.setLng(subObject.get("lng").getAsDouble());
                }
                JsonArray arr=subObject.get("Ajob").getAsJsonArray();    //得到为json的数组
                for(int j=0;j<arr.size();j++) {
    				ActReq req=new ActReq();
    				req.setAno(Ano);
                    req.setAtime(subObject.get("Atime").getAsString());
                	JsonObject obj=arr.get(j).getAsJsonObject();
                	req.setAjobstate(obj.get("Ajobstate").getAsString());
                	req.setAjobcount(obj.get("Ajobcount").getAsInt());
                    reqList.add(req);
                }
            }
            message=dao.addActivity(info, reqList);
            PrintWriter out=response.getWriter();
            out.println(message);
		}catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } 
		request.getSession().removeAttribute("picturePath");
	}

}
