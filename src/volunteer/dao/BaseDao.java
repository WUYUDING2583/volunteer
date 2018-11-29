
/*package volunteer.dao;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
   protected DataSource dataSource;
   public BaseDao(){
   try{
       Context context = new InitialContext();
       dataSource = (DataSource)context.lookup("java:comp/env/jdbc/sampleDS");
   }catch(NamingException e){
       System.out.println("Exception:" + e);
   }
}
   public Connection getConnection()throws Exception{
       return dataSource.getConnection();
   }
}
*/