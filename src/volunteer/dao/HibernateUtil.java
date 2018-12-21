package volunteer.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	 private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;
	 private static String configFile = CONFIG_FILE_LOCATION;
	private int i=0;
	 static { try { configuration.configure(configFile); 
	  sessionFactory =
	  configuration.buildSessionFactory(); } catch (Exception e) {
	  System.err.println("%%%% Error Creating SessionFactory %%%%");
	 e.printStackTrace(); } }
	 
	/*static {

		try {

			configuration
					// 通过 setProperty 方法设置 Hibernate 的连接属性
					.setProperty("hibernate.connection.driver_class",
							"com.mysql.jdbc.Driver")
					.setProperty("hibernate.connection.url",
							"jdbc:mysql://39.106.193.194:3306/hibernatedb?useUnicode=true&amp;characterEncoding=UTF-8")
					.setProperty("hibernate.connection.username", "park")
					.setProperty("hibernate.connection.password", "park")
					.setProperty("hibernate.dialect",
							"org.hibernate.dialect.MySQLDialect")
					.setProperty("hibernate.hbm2ddl.auto", "create-drop")
					// 通过 addResource 方法添加映射文件
					//.setProperty("connection.autocommit", "true")
					.addResource("cn/edu/zjut/po/Customer.hbm.xml")
					.addResource("cn/edu/zjut/po/Item.hbm.xml");
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {e.printStackTrace();
		}

	}
*/
	private HibernateUtil() {
	}

	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void setConfigFile(String configFile) {
		HibernateUtil.configFile = configFile;
		sessionFactory = null;

	}

	public static Configuration getConfiguration() {
		return configuration;

	}

	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		System.out.print("SESSION111");
		if (session == null || !session.isOpen()) {
			System.out.print("SESSION222");
			if (sessionFactory == null) {
				System.out.print("SESSION333");
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
			System.out.print("SESSIONTHREAD:"+threadLocal.toString());
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

}
