package com.splitwise.dao.repository;

import static com.splitwise.constant.SpliwiseConstants.USER_TABLE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.splitwise.dao.MySQLTemplate;
import com.splitwise.dao.UserDao;
import com.splitwise.modal.User;

@Repository
public class UserRepository implements UserDao {

	@Autowired
	private MySQLTemplate mySQLTemplate;

	@Override
	public boolean addUser(User user) {
		return mySQLTemplate.insert(user, USER_TABLE, User.class);
	}

	@Override
	public User getUser(String email) {
		return mySQLTemplate.getRecordOnBasisOfId(new User(), email, USER_TABLE, User.class);
	}

	@Override
	public List<User> getAllUsers() {
		return mySQLTemplate.getAllRecords(USER_TABLE, User.class);
	}

	@Override
	public List<User> getAllUsersByGroupId(User user) {
		return mySQLTemplate.getAllRecordsByID(user, USER_TABLE, User.class);
	}

	@Override
	public boolean updateUser(User user) {
		return mySQLTemplate.updateRecord(user, USER_TABLE, User.class);
	}
}
