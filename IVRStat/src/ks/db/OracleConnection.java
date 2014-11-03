package ks.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

/**
 * 	OracleConnection.java - класс создания соединения с БД
 *  @author sergey.voloshin
 *  @version 1.0
 */
public class OracleConnection {

	private static final String DB_PROPERTIES_FILE = "db.connection.properties";
	private static Properties properties = null;
	private static OracleConnection instance = null;

	public static OracleConnection getInstanse() {
		if (instance == null) {
			instance = new OracleConnection();
		}
		return instance;
	}

	private OracleConnection() {
		initProperties();
	}

	private void initProperties() {
		if (properties == null) {
			properties = new Properties();
			URL ivrstat = getClass().getClassLoader().getResource(DB_PROPERTIES_FILE);
			
			try {
				properties.load(new FileInputStream(ivrstat.getPath().replaceAll("%20", " ")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() throws SQLException {
		String username = properties.getProperty("connection.username");
		String password = properties.getProperty("connection.password");
		String databaseServer = properties.getProperty("connection.host");
		String listenerPort = properties.getProperty("connection.port");
		String oracleService = properties.getProperty("connection.service");
		String thinConn = "jdbc:oracle:thin:" + username + "/" + password
				+ "@//" + databaseServer + ":" + listenerPort + "/" + oracleService;
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(thinConn, username,password);
		conn.setAutoCommit(false);
		return conn;
	}

}
