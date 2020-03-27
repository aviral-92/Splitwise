package com.splitwise.config;

import static com.splitwise.constant.SpliwiseConstants.CONNECTION_URL;
import static com.splitwise.constant.SpliwiseConstants.CREATE_DATABASE;
import static com.splitwise.constant.SpliwiseConstants.DATABASE_NAME;
import static com.splitwise.constant.SpliwiseConstants.JDBC_DRIVER;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SplitwiseConfig {

	@Bean
	@Qualifier("dataSource")
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		Connection con = getConnection();
		try {
			DatabaseMetaData metaData = con.getMetaData();
			dataSource.setUrl(metaData.getURL() + "/" + DATABASE_NAME);
			dataSource.setUsername("root");
			dataSource.setPassword("root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	private Connection getConnection() {

		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(CONNECTION_URL, "root", "root");
			ResultSet rs = con.getMetaData().getCatalogs();
			boolean isSuccess = isDatabaseExist(rs);
			if (!isSuccess) {
				isSuccess = createDatabase(con);
				if (!isSuccess) {
					// try again to create database one more time.
					return getConnection();
				}
			}
			rs.close();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean createDatabase(Connection con) {

		boolean isSuccess = false;
		try {
			PreparedStatement ps = con.prepareStatement(CREATE_DATABASE);
			isSuccess = ps.execute();
			if (isSuccess) {
				System.out.println("Successfully created Database: " + DATABASE_NAME);
			} else {
				System.err.println("Unable to create Database: " + DATABASE_NAME);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	private boolean isDatabaseExist(ResultSet rs) {

		try {
			while (rs.next()) {
				String databaseName = rs.getString(1);
				if (databaseName.equals(DATABASE_NAME)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Bean
	public ExecutorService executorService() {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(10));
		threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return threadPoolExecutor;
	}
}
