package com.splitwise.constant;

import java.util.HashMap;
import java.util.Map;

import com.splitwise.modal.Country;
import com.splitwise.modal.Group;
import com.splitwise.modal.Login;
import com.splitwise.modal.Owes;
import com.splitwise.modal.Transactions;
import com.splitwise.modal.User;

public class SpliwiseConstants {

	public static final String DATABASE_NAME = "splitwise";
	public static final String CREATE_DATABASE = "create database " + DATABASE_NAME;
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";

	public static final String COUNTRY_TABLE = "country";
	public static final String USER_TABLE = "user";
	public static final String GROUP_TABLE = "groups";
	public static final String LOGIN_TABLE = "login";
	public static final String TRANSACTION_TABLE = "transactions";
	public static final String OWES_TABLE = "owes";

	@SuppressWarnings({ "rawtypes", "serial" })
	public static final Map<Class, String> SPLITWISE_TABLES = new HashMap<Class, String>() {
		{
			put(User.class, USER_TABLE);
			put(Group.class, GROUP_TABLE);
			put(Login.class, LOGIN_TABLE);
			put(Transactions.class, TRANSACTION_TABLE);
			put(Country.class, COUNTRY_TABLE);
			put(Owes.class, OWES_TABLE);
		}
	};

	@SuppressWarnings({ "rawtypes", "serial" })
	public static final Map<Class, Object> GET_SPLITWISE_OBJECT = new HashMap<Class, Object>() {
		{
			put(User.class, new User());
			put(Group.class, new Group());
			put(Transactions.class, new Transactions());
			put(Country.class, new Country());
			put(Owes.class, new Owes());
		}
	};
}
