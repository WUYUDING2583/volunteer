package volunteer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BaseHibernateDAO {
	private SessionFactory sessionFactory;
	public Session getSession() {
		 ctx=new FileSystemXmlApplicationContext("C:\\Users\\pc\\Desktop\\volunteer\\WebContent\\WEB-INF\\applicationContext.xml");
         sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
         if(sessionFactory!=null)System.out.println(sessionFactory);  
     return sessionFactory.openSession();
	}
	 private ApplicationContext ctx;
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
