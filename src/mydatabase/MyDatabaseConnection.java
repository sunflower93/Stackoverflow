package mydatabase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class MyDatabaseConnection {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mysotest";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "";
	private Connection conn = null;

	public MyDatabaseConnection() throws Exception {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
