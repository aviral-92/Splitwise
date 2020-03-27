package com.splitwise.dao;

import java.util.List;

import com.splitwise.modal.User;

public interface UserDao {

	public boolean addUser(User user);

	public User getUser(String email);

	public List<User> getAllUsers();

	public List<User> getAllUsersByGroupId(User user);

	public boolean updateUser(User user);
}
